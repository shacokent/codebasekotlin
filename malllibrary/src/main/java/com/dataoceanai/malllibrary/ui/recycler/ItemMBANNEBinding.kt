package com.dataoceanai.malllibrary.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.dataoceanai.malllibrary.R
import com.youth.banner.Banner

class ItemMBANNEBinding private constructor(
    private val rootView: Banner
) : ViewBinding {

    override fun getRoot(): View {
        return rootView
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): ItemMBANNEBinding {
            val root = inflater.inflate(R.layout.item_multiple_banner, parent, false)
            if (attachToParent) {
                parent?.addView(root)
            }
            return bind(root)
        }

        fun bind(rootView: View): ItemMBANNEBinding {
            return ItemMBANNEBinding(rootView as Banner)
        }
    }
}