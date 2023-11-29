package com.dataoceanai.codebasekotlin.fragments.sort.content

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.malllibrary.fragments.MallFragment
import com.dataoceanai.malllibrary.net.RestClient
import com.dataoceanai.malllibrary.net.callback.ISuccess
import com.dataoceanai.malllibrary.ui.recycler.MultipleFields
import com.mall.example.fragments.index.IndexDataConverter
import com.mall.example.fragments.sort.content.SectionAdapter
import com.mall.example.fragments.sort.content.SectionBean
import com.mall.example.fragments.sort.content.SectionDataConverter

class ContentFrament : MallFragment() {
    private val TAG = "ContentFrament"
    private lateinit var mRecyclerView: RecyclerView

//    简单工厂模式
    companion object{
        private val ARGS_CONTENT_ID = "CONTENT_ID"
        private var mContentId = -1

        fun newInstance(contentId:Int):ContentFrament{
            val args = Bundle()
            args.putInt(ARGS_CONTENT_ID,contentId)
            val fragment = ContentFrament()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            mContentId = args.getInt(ARGS_CONTENT_ID)
        }
    }

    override fun setLayout(): Any {
        return R.layout.fragment_list_content
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mRecyclerView = findView(R.id.rv_list_content)
        //初始化数据
        initData()
    }

    private fun initData(){
        RestClient.builder().url("sort_content_list.php?contentId=$mContentId")
            .loader(context)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.d(TAG, "onSuccess: $response")
                    val responsedata = SectionDataConverter().convert(response)
                    val sentionAdapter =  SectionAdapter.create(responsedata)
                    mRecyclerView.adapter = sentionAdapter

                    val manager = GridLayoutManager(context,2)
                    manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            if(responsedata.get(position).getIsHeader()) {
                                return 2
                            }
                            else{
                                return 1
                            }
                        }
                    }
                    mRecyclerView.layoutManager = manager
                }
            }).build().get()
    }

}