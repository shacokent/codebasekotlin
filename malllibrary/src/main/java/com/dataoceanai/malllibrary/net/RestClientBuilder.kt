package com.dataoceanai.malllibrary.net

import android.content.Context
import android.util.Log
import com.dataoceanai.malllibrary.net.callback.*
import java.util.WeakHashMap

/**
 * 构建RestClient并初始化参数和回调
 */
class RestClientBuilder(
    private var url:String? = null,
    private var request:IRequest? = null,
    private var success:ISuccess? = null,
    private var failure:IFailure? = null,
    private var error:IError? = null,
    private var complete:IComplete? = null,
    private var context:Context? = null,
    private var position:Int? = null
) {
    private val mParams = WeakHashMap<String,Any>()
    fun url(url:String):RestClientBuilder{
        this.url = url
        Log.d("RestClientBuilder", "onSuccess: $url")
        return this
    }

    fun params(key:String,value:Any):RestClientBuilder{
        mParams[key] = value
        return this
    }

    fun params(params:WeakHashMap<String,Any>):RestClientBuilder{
        mParams.putAll(params)
        return this
    }

    fun onRequest(iRequest:IRequest):RestClientBuilder{
        this.request = iRequest
        return this
    }

    fun success(iSuccess:ISuccess):RestClientBuilder{
        this.success = iSuccess
        return this
    }

    fun failure(iFailure:IFailure):RestClientBuilder{
        this.failure = iFailure
        return this
    }

    fun complete(iComplete:IComplete):RestClientBuilder{
        this.complete = iComplete
        return this
    }

    fun error(iError:IError):RestClientBuilder{
        this.error = iError
        return this
    }

    fun loader(context: Context?,position:Int):RestClientBuilder{
        this.context = context
        this.position = position
        return this
    }

    fun loader(context: Context?):RestClientBuilder{
        this.context = context
        this.position = 9
        return this
    }

    fun build():RestClient{
        return RestClient(url,mParams,request,success,failure,error,complete,context,position)
    }
}