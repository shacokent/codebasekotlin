package com.dataoceanai.malllibrary.ui.banner

import com.ToxicBakery.viewpager.transforms.DefaultTransformer
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.listener.OnItemClickListener
import com.dataoceanai.malllibrary.R

object BannerCreator {
//    创建默认风格的轮播图
    fun setDefault(convertBanner: ConvenientBanner<String>,banners:ArrayList<String>,clickListener:OnItemClickListener){
        convertBanner.setPages(HolderCreator(),banners)
//                轮播图下面的小点点
            .setPageIndicator(intArrayOf(R.drawable.dot_focus,R.drawable.dot_normal))
            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
            .setOnItemClickListener(clickListener)
                //设置3秒的轮播间隔
            .startTurning(3000)
        convertBanner.transitionName = DefaultTransformer::class.java.simpleName
//        convertBanner.transitionName = DefaultTransformer::class.simpleName
        convertBanner .isCanLoop = true
            //设置转换轮播图的动画
    }
}
