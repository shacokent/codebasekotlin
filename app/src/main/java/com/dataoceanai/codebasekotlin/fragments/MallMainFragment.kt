package com.dataoceanai.codebasekotlin.fragments

import android.graphics.Color
import com.dataoceanai.codebasekotlin.fragments.index.indexFragment
import com.dataoceanai.codebasekotlin.fragments.shoppingcart.shoppingCartFragment
import com.dataoceanai.codebasekotlin.fragments.sort.sortFragment
import com.dataoceanai.codebasekotlin.fragments.user.userFragment
import com.dataoceanai.malllibrary.fragments.bottom.BaseBottomFragment
import com.dataoceanai.malllibrary.fragments.bottom.BottomItemFragment
import com.dataoceanai.malllibrary.fragments.bottom.BottomTabBean
import com.dataoceanai.malllibrary.fragments.bottom.ItemBuilder

class MallMainFragment:BaseBottomFragment() {
    override fun setItems(builder: ItemBuilder): LinkedHashMap<BottomTabBean, BottomItemFragment> {
        val items = LinkedHashMap<BottomTabBean,BottomItemFragment>()
        //添加主界面和TAB对，需要几个添加几个
        //框架自动判断并渲染出正确的主界面
//        不需要调整任何代码
        items[BottomTabBean("{fa-home}","主页")] = indexFragment()
        items[BottomTabBean("{fa-sort}","分类")] = sortFragment()
        items[BottomTabBean("{fa-shopping-cart}","购物车")] = shoppingCartFragment()
        items[BottomTabBean("{fa-user}","我的")] = userFragment()
        return builder.addItems(items).build()
    }

    override fun setIndexFragmnet(): Int {
        return 0
    }

    override fun setClickedColor(): Int {
        return Color.parseColor("#ffff8800")
    }
}