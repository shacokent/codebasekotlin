package com.dataoceanai.malllibrary.ui.banner

import android.view.View
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.dataoceanai.malllibrary.R

class HolderCreator:CBViewHolderCreator {
    override fun createHolder(itemView: View?): Holder<*> {
        return ImageHolder(itemView)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_localimage
    }

}