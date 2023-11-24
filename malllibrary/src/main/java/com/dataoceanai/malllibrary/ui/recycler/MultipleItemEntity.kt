package com.dataoceanai.malllibrary.ui.recycler


data class MultipleItemEntity(
    val fields:LinkedHashMap<Any,Any> ,
    ){
    private val mMultipleFields = LinkedHashMap<Any, Any>()

    init {
        mMultipleFields.putAll(fields)
    }

    companion object {
        fun builder(): MultipleEntityBuilder {
            return MultipleEntityBuilder()
        }
    }

    fun getItemType(): Int {
        return mMultipleFields[MultipleFields.ITEM_TYPE] as Int
    }

    fun <T> getField(key: Any): T {
        @Suppress("UNCHECKED_CAST")
        return mMultipleFields[key] as T
    }

    fun setField(key: Any, value: Any): MultipleItemEntity {
        mMultipleFields[key] = value
        return this
    }
}