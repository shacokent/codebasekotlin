package com.dataoceanai.malllibrary.net.callback

import android.os.Handler
import android.util.Log
import com.dataoceanai.malllibrary.global.GlobalKeys
import com.dataoceanai.malllibrary.global.Mall
import com.dataoceanai.malllibrary.ui.loader.MallLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestCallBacks (
    private val request: IRequest?,
    private val success: ISuccess?,
    private val failure: IFailure?,
    private val error: IError?,
    private val complete: IComplete?,
    private val position:Int?
        ):Callback<String>{
    override fun onResponse(call: Call<String>, response: Response<String>) {
        if(response.isSuccessful){
            if(call.isExecuted){
                if(success!=null){
                    if(response.body()!=null){
                        success.onSuccess(response.body()!!)
                    }
                }
            }
        }else{
            error?.onError(response.code(),response.message())
        }

        onRequestFinish()
    }

    private fun onRequestFinish(){

        //实际项目要去除，模拟网络请求延迟
        val delayed = Mall.getConfiguration<Long>(GlobalKeys.LOADER_DELAYED)
        if(position!=null){
            HANDER.postDelayed({MallLoader.stopLoding()},delayed)
        }
        //实际项目打开注释
//        if(position!=null){
//            HANDER.post { MallLoader.stopLoding() }
//        }
    }

    private val TAG = "RequestCallBacks"
    override fun onFailure(call: Call<String>, t: Throwable) {
        Log.d(TAG, "onFailure: $t")
        failure?.onFailure(t)
        request?.onRequestEnd()
    }

    companion object{
        private val HANDER = Mall.getConfiguration<Handler>(GlobalKeys.HANDLER)
    }
}