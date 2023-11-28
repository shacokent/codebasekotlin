package com.dataoceanai.codebasekotlin.fragments.shoppingcart

import android.os.Bundle
import android.view.View
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.malllibrary.fragments.bottom.BottomItemFragment

class shoppingCartFragment: BottomItemFragment() {
    override fun setLayout(): Any {
        return R.layout.fragment_shopping_cart
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {

    }
}