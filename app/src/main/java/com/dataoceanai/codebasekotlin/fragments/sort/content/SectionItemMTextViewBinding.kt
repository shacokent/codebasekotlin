package com.dataoceanai.codebasekotlin.fragments.sort.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewbinding.ViewBinding
import com.dataoceanai.codebasekotlin.R

class SectionItemMTextViewBinding private constructor(
    private val rootView: LinearLayoutCompat
) : ViewBinding {

    override fun getRoot(): View {
        return rootView
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): SectionItemMTextViewBinding {
            val root = inflater.inflate(R.layout.item_section_header, parent, false)
            if (attachToParent) {
                parent?.addView(root)
            }
            return bind(root)
        }

        fun bind(rootView: View): SectionItemMTextViewBinding {
            return SectionItemMTextViewBinding(rootView as LinearLayoutCompat)
        }
    }
}