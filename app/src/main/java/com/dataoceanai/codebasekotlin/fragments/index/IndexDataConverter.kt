package com.mall.example.fragments.index

import com.alibaba.fastjson.JSON
import com.dataoceanai.malllibrary.ui.recycler.ItemType
import com.dataoceanai.malllibrary.ui.recycler.MultipleFields
import com.dataoceanai.malllibrary.ui.recycler.MultipleItemEntity
import com.mall.library.ui.recycler.DataConverter

class IndexDataConverter : DataConverter() {

    override fun convert(): ArrayList<MultipleItemEntity> {
        val dataArray =
            JSON.parseObject(jsonData).getJSONArray("data")
        val size = dataArray.size
        for (i in 0 until size) {
            //循环出了每一个商品的json信息
            val data = dataArray.getJSONObject(i)

            val imageUrl = data.getString("imageUrl")
            val text = data.getString("text")
            val spanSize = data.getInteger("spanSize")
            val id = data.getInteger("goodsId")
            val banners = data.getJSONArray("banners")

            val bannerImages = ArrayList<String>()
            var type = 0
            //具体的判断怎么去渲染数据
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE
            } else if (banners != null) {
                type = ItemType.BANNER
                //初始化轮播图
                val bannerSize = banners.size
                for (j in 0 until bannerSize) {
                    val banner = banners.getString(j)
                    bannerImages.add(banner)
                }
            }

            //构造entity
            val entity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, type)
                .setField(MultipleFields.SPAN_SIZE, spanSize)
                .setField(MultipleFields.ID, id)
                .setField(MultipleFields.TEXT, text)
                .setField(MultipleFields.IMAGE_URL, imageUrl)
                .setField(MultipleFields.BANNERS, bannerImages)
                .build()

            mEntities.add(entity)

        }

        return  mEntities
    }
}








