package com.mall.library.ui.recycler

import com.dataoceanai.malllibrary.ui.recycler.MultipleItemEntity

//数据转换层
abstract class DataConverter {
    protected val mEntities = ArrayList<MultipleItemEntity>()

    private lateinit var mJsonData: String

    protected val jsonData: String
        get() {
            if (mJsonData.isEmpty()) {
                throw NullPointerException("Data is NULL!")
            }
            return mJsonData
        }

    abstract fun convert(): ArrayList<MultipleItemEntity>

    fun setJsonData(json: String): DataConverter {
        this.mJsonData = json
        return this
    }

    fun clearData() {
        mEntities.clear()
    }
}