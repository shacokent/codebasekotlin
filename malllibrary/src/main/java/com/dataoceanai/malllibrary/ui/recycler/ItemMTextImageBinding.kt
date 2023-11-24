package com.dataoceanai.malllibrary.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewbinding.ViewBinding
import com.dataoceanai.malllibrary.R

class ItemMTextImageBinding private constructor(
    private val rootView: LinearLayoutCompat
) : ViewBinding {

    override fun getRoot(): View {
        return rootView
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): ItemMTextImageBinding {
            val root = inflater.inflate(R.layout.item_multiple_image_text, parent, false)
            if (attachToParent) {
                parent?.addView(root)
            }
            return bind(root)
        }

        fun bind(rootView: View): ItemMTextImageBinding {
            return ItemMTextImageBinding(rootView as LinearLayoutCompat)
        }
    }
}