package com.dataoceanai.malllibrary.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import me.yokeyword.fragmentation.SupportFragment

abstract class BaseFragment: SupportFragment(){
    private lateinit var mRootView:View
    abstract fun setLayout():Any
    abstract fun onBindView(savedInstanceState:Bundle?, rootView: View)
    fun<T:View>findView(@IdRes viewId:Int): T{
        return mRootView.findViewById(viewId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:View = when {
            setLayout() is Int -> inflater.inflate(setLayout() as Int, container,false)
            setLayout() is View -> setLayout() as View
            else -> throw ClassCastException("type of setLayout() must be int or view")
        }
        mRootView = rootView
        onBindView(savedInstanceState,rootView)
        return rootView
    }
}