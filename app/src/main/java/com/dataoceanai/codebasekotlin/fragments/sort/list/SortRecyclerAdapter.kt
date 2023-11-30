package com.mall.example.fragments.sort.list

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.codebasekotlin.fragments.sort.content.ContentFrament
import com.dataoceanai.codebasekotlin.fragments.sort.list.ItemMenuListViewBinding
import com.dataoceanai.codebasekotlin.fragments.sort.sortFragment
import com.dataoceanai.malllibrary.ui.recycler.ItemType
import com.dataoceanai.malllibrary.ui.recycler.MultipleFields
import com.dataoceanai.malllibrary.ui.recycler.MultipleItemEntity
import com.dataoceanai.malllibrary.ui.recycler.MultipleRecyclerAdapter
import me.yokeyword.fragmentation.SupportHelper

class SortRecyclerAdapter(data: List<MultipleItemEntity>, private val sortFragment: sortFragment) :
    MultipleRecyclerAdapter(data) {

    private var mPrePosition = 0

    class ItemMenuListVH(val viewBinding: ItemMenuListViewBinding) : RecyclerView.ViewHolder(viewBinding.root)

    init {
        var text: String
        var isClicked : Boolean
        addItemType(ItemType.VERTICAL_MENU_LIST, object : OnMultiItemAdapterListener<MultipleItemEntity, ItemMenuListVH> { // 类型 1
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemMenuListVH {
                // 创建 viewholder
                val viewBinding = ItemMenuListViewBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemMenuListVH(viewBinding)
            }

            override fun onBind(holder: ItemMenuListVH, position: Int, item: MultipleItemEntity?) {
                // 绑定 item 数据
                if (item != null) {
                    text = item.getField<String>(MultipleFields.TEXT)

                    val itemView = holder.itemView

                    val name = itemView.findViewById<AppCompatTextView>(R.id.tv_vertical_item_name)
                    isClicked = item.getField<Boolean>(MultipleFields.TAG)

                    val line = itemView.findViewById<View>(R.id.view_line)

                    itemView.setOnClickListener {
                        val currentPosition = holder.bindingAdapterPosition
                        if (mPrePosition != currentPosition) {
                            //还原上一个item的颜色和状态
                            data[mPrePosition].setField(MultipleFields.TAG, false)
                            notifyItemChanged(mPrePosition)
                            //更新当前点击的item的状态
                            item.setField(MultipleFields.TAG, true)
                            notifyItemChanged(currentPosition)
                            //已经点击过的position就成为过去式了
                            mPrePosition = currentPosition

                            val contentId =
                                data[currentPosition]
                                    .getField<Int>(MultipleFields.ID)

                            //切换content
                            showContent(contentId)
                        }
                    }
                    //点击事件结束
                    if (!isClicked) {
                        line.visibility = View.INVISIBLE
                        name.setTextColor(
                            ContextCompat.getColor(context, R.color.we_chat_black)
                        )
                        itemView.setBackgroundColor(
                            ContextCompat.getColor(context, R.color.item_background)
                        )
                    } else {
                        line.visibility = View.VISIBLE
                        name.setTextColor(
                            ContextCompat.getColor(context, R.color.app_main)
                        )
                        line.setBackgroundColor(
                            ContextCompat.getColor(context, R.color.app_main)
                        )
                        itemView.setBackgroundColor(
                            Color.WHITE
                        )
                    }
                    name.text = text
                    }
                }

            override fun isFullSpanItem(itemType: Int): Boolean {
                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                return true;
            }

        }).onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
            ItemType.VERTICAL_MENU_LIST
        }
    }

    private fun switchContent(fragment: ContentFrament) {
        val contentFragment =
            SupportHelper.findFragment(
                sortFragment.childFragmentManager
                , ContentFrament::class.java
            )
        contentFragment.supportDelegate.replaceFragment(fragment, false)
    }

    private fun showContent(contentId: Int) {
        val fragment =
            ContentFrament.newInstance(contentId)
        switchContent(fragment)
    }

}

