package com.dataoceanai.malllibrary.global

import android.os.Handler
import android.os.Looper
import com.dataoceanai.malllibrary.util.storage.MemoryStore

/**
 * 全局配置控制类
 * 单例模式
 *  private constructor()是不能New的
 */
class Configurator private constructor(){
    /**
     * 线程安全的单例模式
     * 深入学习可以仿照Java写法
     */
    private object Holder{
        internal val INSTANCE = Configurator()
    }

    companion object{
        //这里获取到全局的存储器
        private val mStore:MemoryStore = MemoryStore.instance
        //Handler需要反复使用，不放提前创建
        private val mHandler = Handler(Looper.getMainLooper())
        internal val instance: Configurator
        get() = Holder.INSTANCE
    }

    init{
        //加一个标签，判断是否配置完成,初始化false,现在还没开始配置
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY,false)
        mStore.addData(GlobalKeys.HANDLER, mHandler)
    }

    /**
     * 访问服务器的API设置
     */
    fun withApiHost(host:String):Configurator{
        mStore.addData(GlobalKeys.API_HOST,host)
        return this
    }

    fun withLoaderDelayed(delayed:Long):Configurator{
        mStore.addData(GlobalKeys.LOADER_DELAYED,delayed)
        return this
    }

    /**
     * 结束配置
     */
    fun configure() {
        /**
         * 配置完成设置成true
         */
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY,true)
        //下面可以做一些回收动作
    }

    /**
     * 检查配置是否完成
     */
    private fun checkConfiguration(){
        val isReady = mStore.getData<Boolean>(GlobalKeys.IS_CONFIGURE_READY)
        if(!isReady){
            throw java.lang.RuntimeException("config is not ready!")
        }
    }

    /**
     * 获取配置
     */
    fun <T> getConfiguration(key: String):T{
        checkConfiguration()
        return mStore.getData(key)
    }

    fun <T> getConfiguration(key: Enum<*>):T{
        return getConfiguration(key.name)
    }
}