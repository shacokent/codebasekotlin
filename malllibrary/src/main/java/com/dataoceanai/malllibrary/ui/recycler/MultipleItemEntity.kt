package com.dataoceanai.malllibrary.ui.recycler


data class MultipleItemEntity(
    val mMultipleFields:LinkedHashMap<Any,Any> = LinkedHashMap<Any ,Any>(),
    val sectionTitle: String = ""
    ){
    val isSection: Boolean
        get() = sectionTitle.isNotBlank()
}