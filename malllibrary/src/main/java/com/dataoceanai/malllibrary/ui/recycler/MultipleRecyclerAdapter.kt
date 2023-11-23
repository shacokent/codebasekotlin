package com.dataoceanai.malllibrary.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseMultiItemAdapter
import com.dataoceanai.malllibrary.R


open class MultipleRecyclerAdapter(data: List<MultipleItemEntity>) :
    BaseMultiItemAdapter<MultipleItemEntity>(data){
//    // 类型 1 的 viewholder
    class ItemVH(val viewBinding: HomeItemViewBinding) : RecyclerView.ViewHolder(viewBinding.root)

    // 类型 2 的 viewholder
    class HeaderVH(val viewBinding: DefSectionHeadBinding) : RecyclerView.ViewHolder(viewBinding.root)

    init {
        addItemType(ItemType.TEXT, object : OnMultiItemAdapterListener<MultipleItemEntity, ItemVH> { // 类型 1
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemVH {
                // 创建 viewholder
                val viewBinding = HomeItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemVH(viewBinding)
            }

            override fun onBind(holder: ItemVH, position: Int, item: MultipleItemEntity?) {
                // 绑定 item 数据
                if (item != null) {
                    holder.itemView.findViewById<TextView>(R.id.id_tv_home_item_title).text = item.sectionTitle
                }
            }
        }).addItemType(ItemType.IMAGE, object : OnMultiItemAdapterListener<MultipleItemEntity, HeaderVH> { // 类型 2
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): HeaderVH {
                // 创建 viewholder
                val viewBinding = DefSectionHeadBinding.inflate(LayoutInflater.from(context), parent, false)
                return HeaderVH(viewBinding)
            }

            override fun onBind(holder: HeaderVH, position: Int, item: MultipleItemEntity?) {
                if (item != null) {
                    holder.itemView.findViewById<TextView>(R.id.id_tv_defsection_item_title).text = item.sectionTitle
                }
            }

            override fun isFullSpanItem(itemType: Int): Boolean {
                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                return true;
            }

        }).onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
            if (list[position].isSection) {
                ItemType.IMAGE
            } else {
                ItemType.TEXT
            }
        }
    }
}