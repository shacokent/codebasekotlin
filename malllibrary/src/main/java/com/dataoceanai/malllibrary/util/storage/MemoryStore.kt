package com.dataoceanai.malllibrary.util.storage

/**
 * 全局的存储器控制类
 * 单例模式
 *  private constructor()是不能New的
 */
class MemoryStore private constructor(){
    /**
     * 线程安全的单例模式
     * 深入学习可以仿照Java写法
     */
    private object Holder{
        internal val INSTANCE = MemoryStore()
    }

    companion object{
        val instance:MemoryStore
        get() = Holder.INSTANCE
    }


    private val mDataMap = HashMap<String, Any>()

    fun <T> getData(key: String):T{
        @Suppress("UNCHECKED_CAST")//这个注解的意思是说标记 mDataMap[key] as T 转换是未经过检测的，消除警告
        return mDataMap[key] as T
    }

    fun addData(key: String, value: Any): MemoryStore {
        mDataMap[key] = value
        return this
    }

    fun <T> getData(key: Enum<*>):T{
        return getData(key.name)
    }

    fun addData(key: Enum<*>, value: Any): MemoryStore {
        addData(key.name, value)
        return this
    }
}