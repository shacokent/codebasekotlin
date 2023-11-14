package com.dataoceanai.codebasekotlin.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.malllibrary.fragments.MallFragment

class TestFragment : MallFragment() {
    override fun setLayout(): Any {
        return R.layout.fragment_test
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        Toast.makeText(context,"初始化完成",Toast.LENGTH_SHORT).show()
    }
}