package com.dataoceanai.malllibrary.fragments.bottom

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.dataoceanai.malllibrary.R
import com.dataoceanai.malllibrary.fragments.MallFragment
import com.joanzapata.iconify.widget.IconTextView
import me.yokeyword.fragmentation.ISupportFragment

abstract class BaseBottomFragment:MallFragment(), View.OnClickListener {
    private val mTabBean = ArrayList<BottomTabBean>()
    val mItemFragments = ArrayList<BottomItemFragment>()
    private val mItems = LinkedHashMap<BottomTabBean,BottomItemFragment>()
    private var mCurrentFragment = 0
        //设置首页打开展示哪个平级的Fragment
    private var mIndexFragment = 0
    private var mClickedColor = Color.RED
    private lateinit var mBottomBar:LinearLayoutCompat
    abstract fun setItems(builder: ItemBuilder):LinkedHashMap<BottomTabBean,BottomItemFragment>

    override fun setLayout(): Any {
        return R.layout.fragment_bottom
    }

    //设置首页打开展示哪个平级的Fragment
    abstract fun setIndexFragmnet(): Int

    //注解只允许传入Color的资源值
    @ColorInt
    abstract fun setClickedColor():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIndexFragment = setIndexFragmnet()
        if(setClickedColor() != 0){
            mClickedColor = setClickedColor()
        }
        val builder = ItemBuilder.builder()
        val items = setItems(builder)
        mItems.putAll(items)
        for((key,value ) in mItems){
            mTabBean.add(key)
            mItemFragments.add(value)
        }
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mBottomBar = findView(R.id.bottom_bar)
        val size = mItems.size
        for(i in 0 until size){
            LayoutInflater.from(context).inflate(R.layout.bottom_item_icon_text,mBottomBar)
            val item = mBottomBar.getChildAt(i) as RelativeLayout

            //为了正确的辨认点击事件
            item.tag = i
            item.setOnClickListener(this)

            val itemIcon = item.getChildAt(0) as IconTextView
            val itemTitle = item.getChildAt(1) as AppCompatTextView

            //初始化相应数据
            val bean = mTabBean[i]
            itemIcon.text = bean.icon
            itemTitle.text = bean.title

            if(i == mIndexFragment){
                itemIcon.setTextColor(mClickedColor)
                itemTitle.setTextColor(mClickedColor)
            }
        }
        val fragments = mItemFragments.toTypedArray<ISupportFragment>()
        supportDelegate.loadMultipleRootFragment(R.id.bottom_bar_fragment_container,mIndexFragment,*fragments)
    }

    private fun resetColor(){
        val count = mBottomBar.childCount
        for(i in 0 until count){
            val item = mBottomBar.getChildAt(i) as RelativeLayout
            val itemIcon = item.getChildAt(0) as IconTextView
            val itemTitle = item.getChildAt(1) as AppCompatTextView
            itemIcon.setTextColor(Color.GRAY)
            itemTitle.setTextColor(Color.GRAY)
        }
    }

    private fun changeColor(tabIndex:Int){
        resetColor()
        val item = mBottomBar.getChildAt(tabIndex) as RelativeLayout
        val itemIcon = item.getChildAt(0) as IconTextView
        val itemTitle = item.getChildAt(1) as AppCompatTextView
        itemIcon.setTextColor(mClickedColor)
        itemTitle.setTextColor(mClickedColor)
    }

    override fun onClick(v: View) {
        val tabIndex = v.tag as Int
        changeColor(tabIndex)
//        展示或隐藏内容
        supportDelegate.showHideFragment(mItemFragments[tabIndex],mItemFragments[mCurrentFragment])
//        先后顺序不能错
        mCurrentFragment = tabIndex
    }

}