package com.dataoceanai.mallApp

import android.app.Application
import com.dataoceanai.malllibrary.global.Mall

class mallApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Mall.init(this).withLoaderDelayed(2000).withApiHost("http://class.imooc.com/mock/api/").configure()
    }
}