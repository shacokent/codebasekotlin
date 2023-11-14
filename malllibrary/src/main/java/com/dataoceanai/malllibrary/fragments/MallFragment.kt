package com.dataoceanai.malllibrary.fragments

import android.widget.Toast

abstract class MallFragment : BaseFragment() {
    private var mTouchTime:Long = 0
    companion object{
        private const val WAIT_TIME = 2000L
    }
    protected  fun exitWithDoubleClick():Boolean{
        if(System.currentTimeMillis()-mTouchTime< WAIT_TIME){
            _mActivity.finish()
        }
        else{
            mTouchTime = System.currentTimeMillis()
            Toast.makeText(_mActivity,"双击退出",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}