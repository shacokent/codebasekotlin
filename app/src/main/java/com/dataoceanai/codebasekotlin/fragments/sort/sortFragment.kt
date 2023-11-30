package com.dataoceanai.codebasekotlin.fragments.sort

import android.os.Bundle
import android.view.View
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.codebasekotlin.fragments.sort.content.ContentFrament
import com.dataoceanai.codebasekotlin.fragments.sort.list.VerticalListFragment
import com.dataoceanai.malllibrary.fragments.bottom.BottomItemFragment

class sortFragment : BottomItemFragment() {
    override fun setLayout(): Any {
        return R.layout.fragment_sort
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        val listFragment = VerticalListFragment()
        //默认显示contentId是1的content
        val contentFragment = ContentFrament.newInstance(1)
        supportDelegate.loadRootFragment(
            R.id.vertical_list_container, listFragment
        )
        supportDelegate.loadRootFragment(
            R.id.sort_content_container, contentFragment
        )
    }
}