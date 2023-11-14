package com.dataoceanai.malllibrary.global

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.dataoceanai.malllibrary.util.storage.MemoryStore

object Mall {
    val configurator: Configurator
    get() = Configurator.instance

    fun init(context: Context) :Configurator{
        MemoryStore.instance.addData(GlobalKeys.APPLOCATION_CONTEXT,context.applicationContext)
        //初始化AndroidUtilCode工具类
        Utils.init(context as Application?)
        return Configurator.instance
    }

    fun <T> getConfiguration(key:String):T{
        return configurator.getConfiguration(key)
    }

    fun <T> getConfiguration(key:Enum<GlobalKeys>):T{
        return getConfiguration(key.name)
    }
}