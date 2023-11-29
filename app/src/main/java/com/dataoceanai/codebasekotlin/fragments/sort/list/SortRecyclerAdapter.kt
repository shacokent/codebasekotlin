//package com.mall.example.fragments.sort.list
//
//import android.graphics.Color
//import android.view.View
//import com.dataoceanai.codebasekotlin.R
//import com.dataoceanai.malllibrary.ui.recycler.ItemType
//import com.dataoceanai.malllibrary.ui.recycler.MultipleItemEntity
//import com.dataoceanai.malllibrary.ui.recycler.MultipleRecyclerAdapter
//import com.mall.library.ui.recycler.*
//import me.yokeyword.fragmentation.SupportHelper
//
//class SortRecyclerAdapter(data: List<MultipleItemEntity>, private val sortFragment: SortFragment) :
//    MultipleRecyclerAdapter(data) {
//
//    private var mPrePosition = 0
//
//    init {
//        //垂直菜单栏的布局文件
//        addItemType(
//            ItemType.VERTICAL_MENU_LIST,
//            R.layout.item_vertical_menu_list
//        )
//    }
//
//    override fun convert(holder: MultipleViewHolder, entity: MultipleItemEntity) {
//        super.convert(holder, entity)
//        when (holder.itemViewType) {
//            ItemType.VERTICAL_MENU_LIST -> {
//                val text =
//                    entity.getField<String>(MultipleFields.TEXT)
//                val isClicked =
//                    entity.getField<Boolean>(MultipleFields.TAG)
//                //取出控件
//                val name =
//                    holder.getView<AppCompatTextView>(R.id.tv_vertical_item_name)
//                val line = holder.getView<View>(R.id.view_line)
//                val itemView = holder.itemView
//                itemView.setOnClickListener {
//
//                    val currentPosition = holder.adapterPosition
//                    if (mPrePosition != currentPosition) {
//                        //还原上一个item的颜色和状态
//                        data[mPrePosition].setField(MultipleFields.TAG, false)
//                        notifyItemChanged(mPrePosition)
//
//                        //更新当前点击的item都状态
//                        entity.setField(MultipleFields.TAG, true)
//                        notifyItemChanged(currentPosition)
//                        //已经点击过的position就成为过去式了
//                        mPrePosition = currentPosition
//
//                        val contentId =
//                            data[currentPosition]
//                                .getField<Int>(MultipleFields.ID)
//
//                        //切换content
//                        showContent(contentId)
//                    }
//                }
//                //点击事件结束
//                if (!isClicked) {
//                    line.visibility = View.INVISIBLE
//                    name.setTextColor(
//                        ContextCompat.getColor(mContext, R.color.we_chat_black)
//                    )
//                    itemView.setBackgroundColor(
//                        ContextCompat.getColor(mContext, R.color.item_background)
//                    )
//                } else {
//                    line.visibility = View.VISIBLE
//                    name.setTextColor(
//                        ContextCompat.getColor(mContext, R.color.app_main)
//                    )
//                    line.setBackgroundColor(
//                        ContextCompat.getColor(mContext, R.color.app_main)
//                    )
//                    itemView.setBackgroundColor(
//                        Color.WHITE
//                    )
//                }
//                holder.setText(R.id.tv_vertical_item_name, text)
//            }
//            else -> {
//            }
//        }
//    }
//    //convert结束
//
//    private fun switchContent(fragment: ContentFragment) {
//        val contentFragment =
//            SupportHelper.findFragment(
//                sortFragment.childFragmentManager
//                , ContentFragment::class.java
//            )
//        contentFragment.supportDelegate.replaceFragment(fragment, false)
//    }
//
//    private fun showContent(contentId: Int) {
//        val fragment =
//            ContentFragment.newInstance(contentId)
//        switchContent(fragment)
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
