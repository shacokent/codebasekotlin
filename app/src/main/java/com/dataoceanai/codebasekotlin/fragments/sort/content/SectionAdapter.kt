package com.mall.example.fragments.sort.content

import com.chad.library.adapter4.BaseMultiItemAdapter


class SectionAdapter(data: List<SectionBean>) :
BaseMultiItemAdapter<SectionBean>(data){


//    override fun convertHead(helper: BaseViewHolder, item: SectionBean) {
//        helper.setText(R.id.header, item.header)
//        helper.setVisible(R.id.more, item.isMore)
//    }
//
//    override fun convert(helper: BaseViewHolder, item: SectionBean) {
//        val thumb = item.t.goodsThumb
//        val name = item.t.goodsName
//        helper.setText(R.id.tv, name)
//        val goodsImageView =
//            helper.getView<AppCompatImageView>(R.id.iv)
//        Glide.with(mContext)
//            .load(thumb)
//            .into(goodsImageView)
//    }
}