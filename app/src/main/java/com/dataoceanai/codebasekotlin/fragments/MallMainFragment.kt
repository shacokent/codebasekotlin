package com.dataoceanai.codebasekotlin.fragments

import android.graphics.Color
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
        return items
    }

    override fun setIndexFragmnet(): Int {
        return 0
    }

    override fun setClickedColor(): Int {
        return Color.parseColor("#ffff8800")
    }
}