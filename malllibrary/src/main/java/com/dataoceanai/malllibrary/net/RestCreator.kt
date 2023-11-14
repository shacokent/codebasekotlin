package com.dataoceanai.malllibrary.net

import com.dataoceanai.malllibrary.global.GlobalKeys
import com.dataoceanai.malllibrary.global.Mall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 创建Retrofit的各个实例
 */
object RestCreator {
    /**
     * 构建我们的okHttp
     */
    private object OkHttpHolder {
        private const val TIME_OUT = 60
        private val BUILDER = OkHttpClient.Builder()
        internal val OK_HTTP_CLIENT = BUILDER
            .connectTimeout(TIME_OUT.toLong(),TimeUnit.SECONDS)
            .build()
    }

    private object RetorfitHolder {
        //从全局配置中取出baseurl
        private val BASE_URL = Mall.getConfiguration<String>(GlobalKeys.API_HOST)
        internal val RETORFIT_CLIENT = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpHolder.OK_HTTP_CLIENT)
            .addConverterFactory(ScalarsConverterFactory.create()).build()
    }

    private object RestServiceHolder{
        internal val REST_SERVICE = RetorfitHolder.RETORFIT_CLIENT.create(RestService::class.java)
    }

    val restService:RestService
    get() = RestServiceHolder.REST_SERVICE
}