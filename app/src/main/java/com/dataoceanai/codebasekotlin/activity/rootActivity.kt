package com.dataoceanai.codebasekotlin.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.codebasekotlin.fragments.MallMainFragment
import com.dataoceanai.codebasekotlin.fragments.TestFragment
import com.dataoceanai.malllibrary.activities.ProxyActivity
import com.dataoceanai.malllibrary.fragments.MallFragment
import com.dataoceanai.malllibrary.global.GlobalKeys
import com.dataoceanai.malllibrary.global.Mall
import com.dataoceanai.malllibrary.net.RestClient
import com.dataoceanai.malllibrary.net.callback.IError
import com.dataoceanai.malllibrary.net.callback.IFailure
import com.dataoceanai.malllibrary.net.callback.ISuccess

//class rootActivity : AppCompatActivity() {
//    private val TAG = "rootActivity"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_root)
//        //通过Mall获取配置
//        Mall.getConfiguration<Context>(GlobalKeys.APPLOCATION_CONTEXT)
//        //调用网络请求
////        RestClient.builder().url("http://class.imooc.com").params("","").success(object :ISuccess{
//        RestClient.builder().url("index.php").loader(this@rootActivity).success(object :ISuccess{
//            override fun onSuccess(response: String) {
//                Log.d(TAG, "onSuccess: $response")
//            }
//        }).failure(object :IFailure{
//            override fun onFailure(t: Throwable) {
//                Log.d(TAG, "onFailure:$t")
//            }
//        }).error(object:IError{
//            override fun onError(code: Int, msg: String) {
//                Log.d(TAG, "onError:$code---msg:$msg")
//            }
//        }).build().get()
//    }
//}

class rootActivity : ProxyActivity() {
    override fun setRootFragment(): MallFragment {
//        return TestFragment()
        return MallMainFragment()
    }
}