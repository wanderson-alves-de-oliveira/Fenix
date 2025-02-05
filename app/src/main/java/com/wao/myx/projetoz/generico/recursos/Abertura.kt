package com.wao.myx.projetoz.generico.recursos


import android.app.Application

class Abertura : Application(){

    lateinit var appOpenAdManager: AppOpenAdManager

    override fun onCreate() {
        super.onCreate()


        //   WorkManager.initialize(this, config)
        appOpenAdManager = AppOpenAdManager(this)
        appOpenAdManager.loadAd()  // Carrega o anúncio de abertura
    }



}
