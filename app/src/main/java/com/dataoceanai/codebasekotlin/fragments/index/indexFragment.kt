package com.dataoceanai.codebasekotlin.fragments.index

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.malllibrary.fragments.bottom.BottomItemFragment
import com.dataoceanai.malllibrary.net.RestClient
import com.dataoceanai.malllibrary.net.callback.IError
import com.dataoceanai.malllibrary.net.callback.IFailure
import com.dataoceanai.malllibrary.net.callback.ISuccess
import com.dataoceanai.malllibrary.ui.recycler.MultipleFields
import com.dataoceanai.malllibrary.ui.recycler.MultipleRecyclerAdapter
import com.mall.example.fragments.index.IndexDataConverter
import com.mall.library.ui.recycler.BaseDecoration

class indexFragment : BottomItemFragment() {
    private val TAG = "indexFragment"
    private lateinit var mRecyclerView:RecyclerView
    override fun setLayout(): Any {
        return R.layout.fragment_index
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mRecyclerView = findView(R.id.rv_index)
    }

    //UI彻底构建后调用
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //初始化沉浸式toolbar的颜色,初始化透明
        val toolbar = view.findViewById<Toolbar>(R.id.tb_index)
        toolbar.background.alpha = 0
    }

    //就是多行的加载数据和UI
    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initRecyclerView()
        initData()
    }

    private fun initRecyclerView(){
        mRecyclerView.addItemDecoration(BaseDecoration.create(Color.LTGRAY,5))
    }

    private fun initData(){
        RestClient.builder().url("index.php").loader(context).success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d(TAG, "onSuccess: $response")
//                    response返回的数据参考libs中的responseData.json
                    val adapter = MultipleRecyclerAdapter.create(IndexDataConverter().setJsonData(response))
                    mRecyclerView.adapter = adapter

                    //设置SpanSizeLookup宽度监听，根据MultipleFields.SPAN_SIZE决定item的大小自动布局
                    val manager = GridLayoutManager(context,4)
                    manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return IndexDataConverter().setJsonData(response).convert().get(position).getField(
                                MultipleFields.SPAN_SIZE)
                        }
                    }
                    mRecyclerView.layoutManager = manager
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