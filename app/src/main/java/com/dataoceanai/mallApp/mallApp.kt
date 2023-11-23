package com.dataoceanai.mallApp

import android.app.Application
import com.dataoceanai.malllibrary.global.Mall
import com.joanzapata.iconify.fonts.FontAwesomeModule

class mallApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Mall.init(this)
            .withIcon(FontAwesomeModule())
            .withLoaderDelayed(2000)
            .withApiHost("http://class.imooc.com/mock/api/")
            .configure()
    }
}