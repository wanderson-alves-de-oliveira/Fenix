package com.wao.myx.projetoz.generico.recursos


import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.appopen.AppOpenAd
import java.util.Date

class AppOpenAdManager(private val application: Application) : Application.ActivityLifecycleCallbacks {

    private var appOpenAd: AppOpenAd? = null
    private var loadTime: Long = 0
    private val adUnitId = "ca-app-pub-1070048556704742/9220386764"  // ID de teste do AdMob

    init {
        application.registerActivityLifecycleCallbacks(this)
        MobileAds.initialize(application) {}  // Inicializa o AdMob SDK
    }

    // Carrega o anúncio
    fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        AppOpenAd.load(application, adUnitId, adRequest, object : AppOpenAd.AppOpenAdLoadCallback() {
            override fun onAdLoaded(ad: AppOpenAd) {
                appOpenAd = ad
                loadTime = Date().time
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                appOpenAd = null
            }
        })
    }

    // Verifica se o anúncio está carregado e ainda válido (1 hora)
    private fun isAdAvailable(): Boolean {
        return appOpenAd != null && (Date().time - loadTime) < 3600000 // 1 hora em milissegundos
    }

    // Exibe o anúncio se estiver disponível
    fun showAdIfAvailable(activity: Activity) {
        if (isAdAvailable()) {
            appOpenAd?.apply {
                fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        appOpenAd = null  // Limpa o anúncio após ser fechado
                        loadAd()  // Recarrega o anúncio para uso futuro
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: com.google.android.gms.ads.AdError) {
                        loadAd()
                    }
                }
                show(activity)
            }
        } else {
            loadAd()  // Carrega se não estiver disponível
        }
    }

// Eventos de ciclo de vida para exibir o anúncio ao
// Eventos de ciclo de vida para exibir o anúncio ao iniciar a Activity
override fun onActivityStarted(activity: Activity) {
    showAdIfAvailable(activity)
}

    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityDestroyed(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
}
