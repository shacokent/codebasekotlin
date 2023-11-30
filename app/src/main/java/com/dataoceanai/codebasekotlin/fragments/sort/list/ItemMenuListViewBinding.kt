package com.dataoceanai.codebasekotlin.fragments.sort.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.viewbinding.ViewBinding
import com.dataoceanai.codebasekotlin.R

class ItemMenuListViewBinding private constructor(
    private val rootView: RelativeLayout
) : ViewBinding {

    override fun getRoot(): View {
        return rootView
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): ItemMenuListViewBinding {
            val root = inflater.inflate(R.layout.item_vertical_menu_list, parent, false)
            if (attachToParent) {
                parent?.addView(root)
            }
            return bind(root)
        }

        fun bind(rootView: View): ItemMenuListViewBinding {
            return ItemMenuListViewBinding(rootView as RelativeLayout)
        }
    }
}