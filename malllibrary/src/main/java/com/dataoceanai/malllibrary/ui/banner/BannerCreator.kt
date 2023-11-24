package com.dataoceanai.malllibrary.ui.banner

//import com.ToxicBakery.viewpager.transforms.DefaultTransformer
//import com.bigkoo.convenientbanner.ConvenientBanner
//import com.bigkoo.convenientbanner.listener.OnItemClickListener
import com.dataoceanai.malllibrary.R
import com.mall.library.ui.banner.GlideImageLoader
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer

object BannerCreator {
////    创建默认风格的轮播图
//    fun setDefault(convertBanner: ConvenientBanner<String>,banners:ArrayList<String>,clickListener:OnItemClickListener){
//        convertBanner.setPages(HolderCreator(),banners)
////                轮播图下面的小点点
//            .setPageIndicator(intArrayOf(R.drawable.dot_focus,R.drawable.dot_normal))
//            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
//            .setOnItemClickListener(clickListener)
//                //设置3秒的轮播间隔
//            .startTurning(3000)
//        convertBanner.transitionName = DefaultTransformer::class.java.simpleName
////        convertBanner.transitionName = DefaultTransformer::class.simpleName
//        convertBanner .isCanLoop = true
//            //设置转换轮播图的动画
//    }
    fun setDefault(banner: Banner, data: List<*>) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        //设置图片加载器
        banner.setImageLoader(GlideImageLoader())
        banner.setImages(data)
        banner.setBannerAnimation(Transformer.Default)
        banner.isAutoPlay(true)
        //设置轮播的间隔时间
        banner.setDelayTime(3000)
        //设置指示器位置
        banner.setIndicatorGravity(BannerConfig.CENTER)
        banner.start()
    }
}
