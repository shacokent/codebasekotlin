package com.dataoceanai.malllibrary.ui.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter4.BaseMultiItemAdapter
import com.dataoceanai.malllibrary.R
import com.dataoceanai.malllibrary.ui.banner.BannerCreator
import com.mall.library.ui.recycler.DataConverter
import com.youth.banner.Banner


open class MultipleRecyclerAdapter(data: List<MultipleItemEntity>) :
    BaseMultiItemAdapter<MultipleItemEntity>(data){
    class ItemMTextVH(val viewBinding: ItemMTextViewBinding) : RecyclerView.ViewHolder(viewBinding.root)
    class ItemMImageVH(val viewBinding: ItemMImageBinding) : RecyclerView.ViewHolder(viewBinding.root)
    class ItemMTextImageVH(val viewBinding: ItemMTextImageBinding) : RecyclerView.ViewHolder(viewBinding.root)
    class ItemMBANNERVH(val viewBinding: ItemMBANNEBinding) : RecyclerView.ViewHolder(viewBinding.root)

    //因为每次加载RecyclerView都会初始化变量(Banner实例),所以会出现重复加载
    //确保Banner初始化一次
    private var mIsInitBanner = false

    //初始化静态的参数和对象
    companion object {
        private val RECYCLER_OPTIONS = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()

        fun create(data: List<MultipleItemEntity>): MultipleRecyclerAdapter {
            return MultipleRecyclerAdapter(data)
        }

        fun create(converter: DataConverter): MultipleRecyclerAdapter {
            return MultipleRecyclerAdapter(converter.convert())
        }
    }

    init {
        var text: String
        var imageUrl: String
        var bannerImages: ArrayList<String>
        addItemType(ItemType.TEXT, object : OnMultiItemAdapterListener<MultipleItemEntity, ItemMTextVH> { // 类型 1
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemMTextVH {
                // 创建 viewholder
                val viewBinding = ItemMTextViewBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemMTextVH(viewBinding)
            }

            override fun onBind(holder: ItemMTextVH, position: Int, item: MultipleItemEntity?) {
                // 绑定 item 数据
                if (item != null) {
                    text = item.getField(MultipleFields.TEXT)
                    holder.itemView.findViewById<AppCompatTextView>(R.id.text_single).text =  text
                }
            }

            override fun isFullSpanItem(itemType: Int): Boolean {
                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                return true;
            }
        }).addItemType(ItemType.IMAGE, object : OnMultiItemAdapterListener<MultipleItemEntity, ItemMImageVH> { // 类型 2
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemMImageVH {
                // 创建 viewholder
                val viewBinding = ItemMImageBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemMImageVH(viewBinding)
            }

            override fun onBind(holder: ItemMImageVH, position: Int, item: MultipleItemEntity?) {
                if (item != null) {
                    imageUrl = item.getField(MultipleFields.IMAGE_URL)
                    Glide.with(context)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into(holder.itemView.findViewById<AppCompatImageView>(R.id.img_single) as ImageView)
                }
            }

            override fun isFullSpanItem(itemType: Int): Boolean {
                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                return true;
            }

        }).addItemType(ItemType.TEXT_IMAGE, object : OnMultiItemAdapterListener<MultipleItemEntity, ItemMTextImageVH> { // 类型 2
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemMTextImageVH {
                // 创建 viewholder
                val viewBinding = ItemMTextImageBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemMTextImageVH(viewBinding)
            }

            override fun onBind(holder: ItemMTextImageVH, position: Int, item: MultipleItemEntity?) {
                if (item != null) {
                    text = item.getField(MultipleFields.TEXT)
                    imageUrl = item.getField(MultipleFields.IMAGE_URL)
                    holder.itemView.findViewById<AppCompatTextView>(R.id.tv_multiple).text =  text
                    Glide.with(context)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into(holder.itemView.findViewById<AppCompatImageView>(R.id.img_multiple) as ImageView)
                }
            }

            override fun isFullSpanItem(itemType: Int): Boolean {
                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                return true;
            }

        }).addItemType(ItemType.BANNER, object : OnMultiItemAdapterListener<MultipleItemEntity, ItemMBANNERVH> { // 类型 2
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): ItemMBANNERVH {
                // 创建 viewholder
                val viewBinding = ItemMBANNEBinding.inflate(LayoutInflater.from(context), parent, false)
                return ItemMBANNERVH(viewBinding)
            }

            override fun onBind(holder: ItemMBANNERVH, position: Int, item: MultipleItemEntity?) {
                if (item != null) {
                    bannerImages = item.getField(MultipleFields.BANNERS)
                    val banner = holder.itemView.findViewById<Banner>(R.id.banner_recycler_item) as Banner
                    //先设置成默认的轮播样式
                    BannerCreator.setDefault(banner, bannerImages)
                    mIsInitBanner = true
                }
            }

            override fun isFullSpanItem(itemType: Int): Boolean {
                // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                return true;
            }

        }).onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
            list[position].getItemType()
        }
    }
}