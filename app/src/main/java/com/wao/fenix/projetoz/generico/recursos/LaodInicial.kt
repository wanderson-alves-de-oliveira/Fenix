package com.wao.fenix.projetoz.generico.recursos

import android.content.Context
import android.content.res.AssetManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.wao.fenix.projetoz.dao.BDNave
import com.wao.fenix.projetoz.menu.TartarugaCorrida
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LaodInicial {


    companion object {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        suspend fun loadInicial(context: Context, asset: AssetManager): TartarugaCorrida {

            return  withContext(Dispatchers.Main)
              {

                  val BDN = BDNave(context)
                   val nivelNave = BDN.buscar(1)
                      TartarugaCorrida(context, asset, 1, true, true,nivelNave)

             }


        }
    }




}