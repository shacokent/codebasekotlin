package com.mall.example.fragments.sort.content

class SectionBean{

    private var header: String = ""
    private var isHeader: Boolean = false
    private var isMore = false
    private var id = -1

    private var sectionContentItemEntity: SectionContentItemEntity? = null
    constructor(sectionContentItemEntity: SectionContentItemEntity){
        this.sectionContentItemEntity = sectionContentItemEntity
    }

    constructor(isHeader: Boolean, header: String){
        this.isHeader = isHeader
        this.header = header
    }

    fun getheader():String{
        return header;
    }

    fun setheader(header: String){
        this.header = header
    }

    fun getIsHeader():Boolean{
        return isHeader;
    }

    fun setIsHeader(isHeader: Boolean){
        this.isHeader = isHeader
    }

    fun getIsMore():Boolean{
        return isMore;
    }

    fun setIsMore(isMore: Boolean){
        this.isMore = isMore
    }

    fun getId():Int{
        return id;
    }

    fun setId(id: Int){
        this.id = id
    }

    fun getSectionContentItemEntity(): SectionContentItemEntity? {
        return sectionContentItemEntity;
    }

    fun setSectionContentItemEntity(sectionContentItemEntity: SectionContentItemEntity){
        this.sectionContentItemEntity = sectionContentItemEntity
    }
}