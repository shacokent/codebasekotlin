package com.mall.example.fragments.sort.content

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter4.BaseMultiItemAdapter
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.codebasekotlin.fragments.sort.content.SectionItemMTextImageBinding
import com.dataoceanai.codebasekotlin.fragments.sort.content.SectionItemMTextViewBinding
import com.dataoceanai.malllibrary.ui.recycler.ItemType


class SectionAdapter(data: List<SectionBean>) :
BaseMultiItemAdapter<SectionBean>(data){

    class ItemMTextVH(val viewBinding: SectionItemMTextViewBinding) : RecyclerView.ViewHolder(viewBinding.root)
    class ItemMTextImageVH(val viewBinding: SectionItemMTextImageBinding) : RecyclerView.ViewHolder(viewBinding.root)

    //初始化静态的参数和对象
    companion object {
        private val RECYCLER_OPTIONS = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()

        fun create(data: List<SectionBean>): SectionAdapter {
            return SectionAdapter(data)
        }
    }

    init {
        var text: String
        var imageUrl: String
        addItemType(ItemType.TEXT, object : OnMultiItemAdapterListener<SectionBean, ItemMTextVH> { // 类型 1
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemMTextVH {
                // 创建 viewholder
                val viewBinding = SectionItemMTextViewBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemMTextVH(viewBinding)
            }

            override fun onBind(holder: ItemMTextVH, position: Int, item: SectionBean?) {
                // 绑定 item 数据
                if (item != null) {
                    text = item.getheader()
                    holder.itemView.findViewById<AppCompatTextView>(R.id.header).text =  text
                    holder.itemView.findViewById<AppCompatTextView>(R.id.more).visibility = if(item.getIsMore()){
                        View.VISIBLE
                    }else{
                        View.GONE
                    }
                }
            }

            override fun isFullSpanItem(itemType: Int): Boolean {
                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                return true;
            }

        }).addItemType(ItemType.TEXT_IMAGE, object : OnMultiItemAdapterListener<SectionBean, ItemMTextImageVH> { // 类型 2
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemMTextImageVH {
                // 创建 viewholder
                val viewBinding = SectionItemMTextImageBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemMTextImageVH(viewBinding)
            }

            override fun onBind(holder: SectionAdapter.ItemMTextImageVH, position: Int, item: SectionBean?) {
                if (item != null) {
                    text = item.getSectionContentItemEntity()?.goodsName ?:""
                    imageUrl = item.getSectionContentItemEntity()?.goodsThumb.toString()
                    holder.itemView.findViewById<AppCompatTextView>(R.id.tv).text =  text
                    Glide.with(context)
                        .load(imageUrl)
                        .apply(SectionAdapter.RECYCLER_OPTIONS)
                        .into(holder.itemView.findViewById<AppCompatImageView>(R.id.iv) as ImageView)
                }
            }

            override fun isFullSpanItem(itemType: Int): Boolean {
                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                return true;
            }

        }).onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
            if(list[position].getIsHeader()){
                ItemType.TEXT
            }
            else{
                ItemType.TEXT_IMAGE
            }
        }
    }

}