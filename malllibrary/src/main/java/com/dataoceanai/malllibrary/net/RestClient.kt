package com.dataoceanai.malllibrary.net

import android.content.Context
import com.dataoceanai.malllibrary.net.callback.*
import com.dataoceanai.malllibrary.ui.loader.MallLoader
import retrofit2.Call
import retrofit2.Callback
import java.util.WeakHashMap

/**
 * 在所有依赖malllibrary的APP中对外暴漏直接使用的客户端
 * 用建造者模式构建
 */

class RestClient internal constructor(
    private val url:String?,
    private val params:WeakHashMap<String,Any>?,
    private val request: IRequest?,
    private val success: ISuccess?,
    private val failure: IFailure?,
    private val error: IError?,
    private val complete: IComplete?,
    private val context: Context?,
    private val position:Int?
){
    companion object{
        fun builder():RestClientBuilder{
            return RestClientBuilder()
        }
    }

    private fun request(method:HttpMethod){
        val service = RestCreator.restService
        val call: Call<String>?
        request?.onRequestStart()

        if(position!=null){
            MallLoader.showLoading(context,position)
        }

        call = when(method){
            HttpMethod.GET -> service.get(url, params)
            HttpMethod.POST -> service.post(url, params)
            HttpMethod.PUT -> service.put(url, params)
            HttpMethod.DELETE -> service.delete(url, params)
            HttpMethod.UPLOAD -> TODO()
            HttpMethod.DOWNLOAD -> TODO()
        }
        call.enqueue(requestCallback)
    }

    private val requestCallback:Callback<String>
    get() = RequestCallBacks(request,success,failure,error,complete,position)

    fun get(){
        request(HttpMethod.GET)
    }

    fun post(){
        request(HttpMethod.POST)
    }

    fun put(){
        request(HttpMethod.PUT)
    }

    fun delete(){
        request(HttpMethod.DELETE)
    }


}