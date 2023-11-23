package com.dataoceanai.malllibrary.ui.recycler

class MultipleEntityBuilder {
    companion object{
        private val FIELDS = LinkedHashMap<Any,Any>()
    }

    init{
        //先清空上一个数据
        FIELDS.clear()
    }

    fun setItemType(itemType: Int): MultipleEntityBuilder {
        FIELDS[MultipleFields.ITEM_TYPE] = itemType
        return this
    }

    fun setField(key:Any,value:Any):MultipleEntityBuilder {
        FIELDS[key] = value
        return this
    }

    fun setField(map:LinkedHashMap<*,*>):MultipleEntityBuilder {
        FIELDS.putAll(map)
        return this
    }

    fun build():MultipleItemEntity{
        return MultipleItemEntity(FIELDS)
    }
}