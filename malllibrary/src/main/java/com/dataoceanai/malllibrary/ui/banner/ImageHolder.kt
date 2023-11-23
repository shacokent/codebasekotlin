package com.dataoceanai.malllibrary.ui.banner

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dataoceanai.malllibrary.R

class ImageHolder(itemView: View?) : Holder<String>(itemView) {
    private lateinit var mImageView:AppCompatImageView

    companion object{
        private val BANNER_OPTIONS = RequestOptions()
                //全部缓存图片
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop()
    }

    override fun initView(itemView: View?) {
        if (itemView != null) {
            mImageView = itemView.findViewById(R.id.ivPost)
        };
    }

    override fun updateUI(data: String?) {
        Glide.with(itemView)
            .load(data)
            .apply(BANNER_OPTIONS)
            .into(mImageView)
    }

//    override fun createView(context: Context?): View {
//        mImageView = AppCompatImageView(context!!)
//        return mImageView as AppCompatImageView
//    }
//
//    //就是把URL加载到ImageView中
//    override fun UpdateUI(context: Context?, position: Int, data: String?) {
//        Glide.with(context!!)
//            .load(data)
//            .apply(BANNER_OPTIONS)
//            .into(mImageView)
//    }


}