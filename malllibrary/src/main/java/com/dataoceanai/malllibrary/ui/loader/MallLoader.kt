package com.dataoceanai.malllibrary.ui.loader

import android.content.Context
import android.view.Gravity
import androidx.appcompat.app.AppCompatDialog
import com.blankj.utilcode.util.ScreenUtils
import com.dataoceanai.malllibrary.R
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.SpriteFactory
import com.github.ybq.android.spinkit.Style

object MallLoader {

    private val LOADER_SIZE_SCALE = 8
    private val LOADER_OFFSET_SCALE = 10

    private val LOADERS = ArrayList<AppCompatDialog>()
//默认的
    private val DEFAULT_LOADER = 9

    private fun createDialog(
        context: Context?,
        loadingView:SpinKitView
    ):AppCompatDialog{
        val dialog = AppCompatDialog(context, R.style.dialog)
        //获取屏幕宽高,引入工具类库AndroidUtilCode
        val deviceWidth = ScreenUtils.getScreenWidth()
        val deviceHeight = ScreenUtils.getScreenHeight()
        val dialogWindow = dialog.window
        dialog.setContentView(loadingView)
        if(dialogWindow != null){
            val lp = dialogWindow.attributes
            lp.width = deviceWidth/ LOADER_SIZE_SCALE
            lp.height = deviceHeight/ LOADER_SIZE_SCALE+deviceHeight/ LOADER_OFFSET_SCALE
            lp.gravity = Gravity.CENTER
        }
        LOADERS.add(dialog)
        return dialog
    }

    fun showLoading(context: Context?, position:Int = DEFAULT_LOADER){
        val loadingView = SpinKitView(context)
        val style = SpriteFactory.create(Style.values()[position])
        loadingView.setIndeterminateDrawable(style)
        createDialog(context, loadingView).show()
    }

    fun stopLoding(){
        for(dialog in LOADERS){
            if(dialog.isShowing){
                dialog.cancel()
            }
        }
        LOADERS.clear()
    }
}

