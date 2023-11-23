package com.dataoceanai.malllibrary.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.dataoceanai.malllibrary.R


class HomeItemViewBinding private constructor(
    private val rootView: ConstraintLayout,
) : ViewBinding {

    override fun getRoot(): View {
        return rootView
    }

    companion object {

        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): HomeItemViewBinding {
            val root = inflater.inflate(R.layout.item_home_view, parent, false)
            if (attachToParent) {
                parent?.addView(root)
            }
            return bind(root)
        }

        fun bind(rootView: View): HomeItemViewBinding {
            return HomeItemViewBinding(rootView as ConstraintLayout)
        }
    }
}
