package com.dataoceanai.codebasekotlin.fragments.index

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.malllibrary.fragments.bottom.BottomItemFragment
import com.dataoceanai.malllibrary.net.RestClient
import com.dataoceanai.malllibrary.net.callback.IError
import com.dataoceanai.malllibrary.net.callback.IFailure
import com.dataoceanai.malllibrary.net.callback.ISuccess
import com.dataoceanai.malllibrary.ui.recycler.MultipleRecyclerAdapter
import com.mall.library.ui.recycler.BaseDecoration

class indexFragment : BottomItemFragment() {
    private val TAG = "indexFragment"
    private lateinit var mRecyclerView:RecyclerView
    override fun setLayout(): Any {
        return R.layout.fragment_index
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mRecyclerView = findView(R.id.iv_index)
    }

    //就是多行的加载数据和UI
    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
    }

    private fun initRecyclerView(){
        val manager = GridLayoutManager(context,4)
        mRecyclerView.layoutManager = manager
        mRecyclerView.addItemDecoration(BaseDecoration.create(Color.LTGRAY,5))
    }

    private fun initData(){
        RestClient.builder().url("index.php").loader(context).success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d(TAG, "onSuccess: $response")
                    val adapter = MultipleRecyclerAdapter()
                }
            }).failure(object : IFailure {
                override fun onFailure(t: Throwable) {
                    Log.d(TAG, "onFailure:$t")
                }
            }).error(object: IError {
                override fun onError(code: Int, msg: String) {
                    Log.d(TAG, "onError:$code---msg:$msg")
                }
            }).build().get()
    }
}