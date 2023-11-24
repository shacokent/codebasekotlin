package com.dataoceanai.malllibrary.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewbinding.ViewBinding
import com.dataoceanai.malllibrary.R


class ItemMTextViewBinding private constructor(
    private val rootView: AppCompatTextView,
) : ViewBinding {

    override fun getRoot(): View {
        return rootView
    }

    companion object {

        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): ItemMTextViewBinding {
            val root = inflater.inflate(R.layout.item_multiple_text, parent, false)
            if (attachToParent) {
                parent?.addView(root)
            }
            return bind(root)
        }

        fun bind(rootView: View): ItemMTextViewBinding {
            return ItemMTextViewBinding(rootView as AppCompatTextView)
        }
    }
}
