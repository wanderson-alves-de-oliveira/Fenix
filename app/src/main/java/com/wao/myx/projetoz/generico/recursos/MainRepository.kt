package com.wao.myx.projetoz.generico.recursos

import android.content.Context
import android.content.res.AssetManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.wao.myx.projetoz.menu.TartarugaCorrida
import kotlinx.coroutines.*

class MainRepository {



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    suspend fun getTut(context : Context, asset : AssetManager) {
        return  withContext(Dispatchers.Default)
        {
            val tuti:TartarugaCorrida = async {
                LaodInicial.loadInicial(context, asset)

            }.await()

        }
    }
}