package com.dataoceanai.malllibrary.fragments.bottom

class ItemBuilder {
    //不能使用HashMap,因为HashMap是一个无需集合
//    而LinkedHashMap是有顺序的，tabbar按钮需要顺序排列
    private val mItems = LinkedHashMap<BottomTabBean,BottomItemFragment>()

    companion object{
        internal fun builder():ItemBuilder{
            return builder()
        }
    }

    fun addItem(bean:BottomTabBean,fragment: BottomItemFragment):ItemBuilder{
        mItems[bean] = fragment
        return this
    }

    fun addItems(items:LinkedHashMap<BottomTabBean,BottomItemFragment>):ItemBuilder{
        mItems.putAll(items)
        return this
    }

    fun build():LinkedHashMap<BottomTabBean,BottomItemFragment>{
        return mItems
    }
}