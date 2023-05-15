package com.wao.fenix.projetoz.generico.recursos

import android.content.Context
import android.content.res.AssetManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wao.fenix.projetoz.generico.recursos.LaodInicial.Companion.loadInicial
import com.wao.fenix.projetoz.menu.TartarugaCorrida
import kotlinx.coroutines.*

class Load():TartarugaCorrida(){
    lateinit var  tut:TartarugaCorrida



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getTut(context : Context, asset : AssetManager){
        CoroutineScope(Dispatchers.Default).launch()
        {
            val tuti = async {
                loadInicial(context, asset)

            }.await()
            tut=tuti
        }
    }

//    class MainFactoryT(private  val repository: MainRepository):TartarugaCorrida.Factory{
//        override fun <T : TartarugaCorrida?> create(modelClass: Class<T>): T {
//            return Load(repository) as T
//        }
//    }
}
