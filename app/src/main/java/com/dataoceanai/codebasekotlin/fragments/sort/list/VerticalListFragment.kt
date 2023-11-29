package com.dataoceanai.codebasekotlin.fragments.sort.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.malllibrary.fragments.MallFragment
import com.dataoceanai.malllibrary.net.RestClient
import com.dataoceanai.malllibrary.net.callback.ISuccess

class VerticalListFragment : MallFragment() {
    private val TAG = "VerticalListFragment"
    internal lateinit var mRecyclerView : RecyclerView

    override fun setLayout(): Any {
        return R.layout.fragment_vertical_list
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mRecyclerView = findView(R.id.rv_vertical_menu_list)
        val manager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = manager
        //屏蔽动画
        mRecyclerView.itemAnimator = null
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        RestClient.builder().url("sort_list.php")
            .loader(context)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d(TAG, "onSuccess: $response")
                }

            })
    }

}