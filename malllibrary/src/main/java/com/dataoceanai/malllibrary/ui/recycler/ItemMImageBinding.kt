package com.dataoceanai.malllibrary.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewbinding.ViewBinding
import com.dataoceanai.malllibrary.R

class ItemMImageBinding private constructor(
    private val rootView: AppCompatImageView
) : ViewBinding {

    override fun getRoot(): View {
        return rootView
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): ItemMImageBinding {
            val root = inflater.inflate(R.layout.item_multiple_image, parent, false)
            if (attachToParent) {
                parent?.addView(root)
            }
            return bind(root)
        }

        fun bind(rootView: View): ItemMImageBinding {
            return ItemMImageBinding(rootView as AppCompatImageView)
        }
    }
}