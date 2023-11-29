package com.dataoceanai.codebasekotlin.fragments.sort.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewbinding.ViewBinding
import com.dataoceanai.codebasekotlin.R

class SectionItemMTextImageBinding private constructor(
    private val rootView: LinearLayoutCompat
) : ViewBinding {

    override fun getRoot(): View {
        return rootView
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): SectionItemMTextImageBinding {
            val root = inflater.inflate(R.layout.item_section_content, parent, false)
            if (attachToParent) {
                parent?.addView(root)
            }
            return bind(root)
        }

        fun bind(rootView: View): SectionItemMTextImageBinding {
            return SectionItemMTextImageBinding(rootView as LinearLayoutCompat)
        }
    }
}