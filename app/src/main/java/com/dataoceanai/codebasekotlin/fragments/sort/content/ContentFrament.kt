package com.dataoceanai.codebasekotlin.fragments.sort.content

import android.os.Bundle
import android.view.View
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.malllibrary.fragments.MallFragment

class ContentFrament : MallFragment() {

    companion object{
        private val ARGS_CONTENT_ID = "CONTENT_ID"

        fun newInstance(contentId:Int):ContentFrament{
            val args = Bundle()
            args.putInt(ARGS_CONTENT_ID,contentId)
            val fragment = ContentFrament()
            fragment.arguments = args
            return fragment
        }
    }

    override fun setLayout(): Any {
        return R.layout.fragment_list_content
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        TODO("Not yet implemented")
    }

}