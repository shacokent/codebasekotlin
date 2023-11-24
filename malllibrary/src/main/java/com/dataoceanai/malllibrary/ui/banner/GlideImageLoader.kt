package com.mall.library.ui.banner

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {

    companion object {
        private val options = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    }

    override fun createImageView(context: Context): ImageView {
        return AppCompatImageView(context)
    }

    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
        Glide.with(context)
            .load(path)
            .apply(options)
            .into(imageView)
    }
}