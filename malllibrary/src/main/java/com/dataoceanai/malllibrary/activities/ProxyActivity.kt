package com.dataoceanai.malllibrary.activities

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatDelegate
import com.dataoceanai.malllibrary.fragments.MallFragment
import me.yokeyword.fragmentation.SupportActivity

abstract class ProxyActivity: SupportActivity() {
    abstract fun setRootFragment(): MallFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainer(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun initContainer(savedInstanceState: Bundle?) {
        val container = FrameLayout(this)
        container.id = View.generateViewId()
        setContentView(container)
        if (savedInstanceState == null) {
            loadRootFragment(container.id, setRootFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
        System.runFinalization()
    }
}