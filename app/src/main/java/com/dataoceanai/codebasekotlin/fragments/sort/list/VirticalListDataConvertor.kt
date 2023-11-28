package com.dataoceanai.codebasekotlin.fragments.sort.list

import com.alibaba.fastjson2.JSON
import com.dataoceanai.malllibrary.ui.recycler.ItemType
import com.dataoceanai.malllibrary.ui.recycler.MultipleFields
import com.dataoceanai.malllibrary.ui.recycler.MultipleItemEntity
import com.mall.library.ui.recycler.DataConverter

class VirticalListDataConvertor : DataConverter() {
    override fun convert(): ArrayList<MultipleItemEntity> {
        val dataList = ArrayList<MultipleItemEntity>()
        var dataArray = JSON.parseObject(jsonData)
            .getJSONObject("data")
            .getJSONArray("list")

        val size = dataArray.size
        for(i in 0 until size){
            val data = dataArray.getJSONObject(i)
            val id = data.getInteger("id")
            val name = data.getString("name")
            val entity = MultipleItemEntity
                .builder()
                .setField(MultipleFields.ITEM_TYPE,ItemType.VERTICAL_MENU_LIST)
                .setField(MultipleFields.ID,id)
                .setField(MultipleFields.TEXT,name)
                .setField(MultipleFields.TAG,false)
                .build()
            dataList.add(entity)

            //默认设置为第一个类型呗选中
            dataList[0].setField(MultipleFields.TAG,true)
        }
        return dataList
    }
}