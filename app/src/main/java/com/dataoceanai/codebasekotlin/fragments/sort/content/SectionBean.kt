package com.mall.example.fragments.sort.content

class SectionBean{

    private var header: String = ""
    private var isHeader: Boolean = false
    var isMore = false
    var id = -1

    var sectionContentItemEntity: SectionContentItemEntity? = null
    constructor(sectionContentItemEntity: SectionContentItemEntity){
        this.sectionContentItemEntity = sectionContentItemEntity
    }

    constructor(isHeader: Boolean, header: String){
        this.isHeader = isHeader
        this.header = header
    }

}