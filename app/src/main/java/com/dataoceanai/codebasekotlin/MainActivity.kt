package com.dataoceanai.codebasekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dataoceanai.LearnBase.LearnBase
import com.dataoceanai.LearnBase.LearnBaseClass
import kotlinx.android.synthetic.main.activity_main.*

//添加自定义的malllibrary到app中,业务无关的代码（网络框架，UI框架等）放在malllibrary中，简化APP代码，整洁性
//File->Project Structure->Dependencies->app->+->Module Dependencies->malllibrary->apply->ok

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //可以直接用控件id,调用方法，省去findViewByID,需要导入扩展包
        // plugins {
        //    id 'org.jetbrains.kotlin.android.extensions'
        //}，也可以使用ViewBinding替代
        id_tv_hello.setOnClickListener {
            id_tv_hello.text = "${System.currentTimeMillis()}"
//            传参到另一个页面
            val intent = Intent(this@MainActivity,MainActivity2::class.java)
            intent.putExtra("Key","From MainActivity")
            startActivity(intent)
        }


        //基础学习
        val learn: LearnBase = LearnBase()
        val learnClass: LearnBaseClass = LearnBaseClass()
    }
}