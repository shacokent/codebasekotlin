package com.mall.example.fragments.index

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.dataoceanai.codebasekotlin.R
import com.dataoceanai.malllibrary.global.GlobalKeys
import com.dataoceanai.malllibrary.global.Mall

//自定义顶部沉浸式ToolBar，靠JAVA 底层的反射去加载的，在fragment_index.xml中加载
@Suppress("unused")
class TranslucentBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<Toolbar>(context, attrs) {

    companion object {
        //延长滑动
        private const val MORE = 100
    }

    //注意，这里一定要是类变量
    private var mOffset = 0

    //确定我们所依赖的动作载体,这里就是recyclerView,根据recyclerView的滑动改变颜色
    override fun layoutDependsOn(parent: CoordinatorLayout, child: Toolbar, dependency: View): Boolean {
        return dependency.id == R.id.rv_index
    }

    //是否承接滑动事件，返回true我们自己去承载
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: Toolbar,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return true
    }

    //实现承接的滑动事件
    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        toolbar: Toolbar,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        val startOffset = 0
        val context =
            Mall.getConfiguration<Context>(GlobalKeys.APPLOCATION_CONTEXT)
        val endOffset = context.resources.getDimensionPixelOffset(
            R.dimen.header_height//toolbar高度
        ) + MORE//,一旦滑动越过header_height+more的高度，toolbar颜色改变
        mOffset += dyConsumed
        when {
            mOffset <= startOffset -> toolbar.background.alpha = 0
            mOffset in (startOffset + 1)..(endOffset - 1) -> {
                val percent =
                    (mOffset - startOffset).toFloat() / endOffset
                val alpha = Math.round(percent * 255)
                toolbar.background.alpha = alpha
            }
            mOffset >= endOffset -> toolbar.background
        }
    }
}