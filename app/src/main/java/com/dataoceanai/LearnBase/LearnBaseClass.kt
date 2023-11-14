package com.dataoceanai.LearnBase
import kotlin.reflect.KProperty

class LearnBaseClass {

    init {
        learn()
    }

    fun learn() {
        //抽象类和接口
        val computer = Computer()
        val mouse = USBMouse("逻辑鼠标")
        computer.addnputDevice(mouse)
        val mouseloji = lojitechMouse()
        computer.addnputDevice(mouseloji)

//        继承
        val driver = CarDriver()
        val writer = PPTWriter()
        val seniormanager = SeniorManager(driver,writer)
        seniormanager.driver()
        seniormanager.writer()

        E(1)
        E(-50)
        E(-150)
        E(-300)

//        Object
        MusicPlayer.paly("123")
        //        在java中调用
//        MusicPlayer.INSTANCE.paly();

        //        静态方法
        val latitude = Latitude.ofDouble(3.0)
        //静态变量
        Latitude.TAG
//        在java中调用
//        Latitude.Companion.ofJDouble(3.0)
//        Latitude.Companion.TAG
        //给方法加上@JvmStatic标签，再在JAVA中调用就可以不用Companion
//        Latitude.ofJDouble(3.0)
        //静态变量用@JvmField标签
//        Latitude.TAG

        //方法重载,如果在java中调用重载方法，需要在方法前加上注解@JvmOverloads

        //扩展成员
        println("abc".a)
        println("abc".b)
        //扩展方法
        println("abc".multipy(10))


//        属性代理
        val delegates = Delegates()
        println(delegates.hello)
        println(delegates.hello2)
        println(delegates.hello3)
        delegates.hello3 = "赋值Hello3"
        println(delegates.hello3)


        //    数据类 data class，但是底层会被定义成final,并且没有无参的构造方法，所以无法当成javaBean使用，无法被继承，解决这个问题，引入两个插件
        //allopen和noarg
//        引入流程build.gradle（app）中配置
//        ---start
//        plugins {
    //        id "org.jetbrains.kotlin.plugin.noarg" version "1.7.20"
    //        id "org.jetbrains.kotlin.plugin.allopen" version "1.7.20"
    //    }
//        noArg {
//            annotation("com.dataoceanai.LearnBase.NoArg")
//        }
//
//        allOpen {
//            annotation("com.dataoceanai.LearnBase.AllOpen")
//        }
//        ----end

        //建立模型不用在手写get，set方法，并且自带toString()方法
        val china = Country(0,"中国")
        println(china)
        println(china.component1())//打印第一个参数
        println(china.component2())//打印第二个参数

        //自动赋值
        val(id,name) = china
        println(id)//打印第一个参数
        println(name)//打印第二个参数

        //测试Son,继承Country,编译成功证明allopen和noarg配置成功
        Son("son").hello()

        //枚举
        println(LogLevel2.DEBUG.getTag())
        //获取指定枚举值的顺序
        println(LogLevel2.DEBUG.ordinal)
        //打印所有的枚举值
        LogLevel2.values().map(::println)
        //获取ERROR的实例
        println(LogLevel2.valueOf("ERROR"))
    }

    //接口
    interface InputDevice{
        fun input(event:Any)
    }

    //继承
    interface USBInputDevice:InputDevice
    interface BLEInputDevice:InputDevice

    class Computer{
        fun addUSBInputDevice(inputDevice:USBInputDevice): Unit {
            println("add input USB:$inputDevice")
        }

        fun addBLEInputDevice(inputDevice:BLEInputDevice): Unit {
            println("add input 蓝牙:$inputDevice")
        }

        fun addnputDevice(inputDevice:InputDevice): Unit {
            when(inputDevice){
                is BLEInputDevice->{
                    addBLEInputDevice(inputDevice)
                }
                is USBInputDevice->{
                    addUSBInputDevice(inputDevice)
                }
                else->{
                    throw IllegalArgumentException("输入设备不支持")
                }
            }
        }
    }

//    要调用接口用，就需要继承接口，实现接口，逗号分隔，可集成多个接口
    class USBMouse(val name:String):USBInputDevice{
        override fun input(event: Any) {

        }

        override fun toString(): String {
            return "USBMouse(name='$name')"
        }
    }

//    光电鼠标接口
    interface OpticalMouse{
        fun inputOp(event:Any)
    }

    //抽象类,不用必须实践接口方法，可以由子类实现，这里就没有实现OpticalMouse的inputOp方法，由继承他的lojitechMouse实现
    abstract class AbsUSBMouse(val name:String):USBInputDevice,OpticalMouse{
        override fun input(event: Any) {

        }

        override fun toString(): String {
            return "USBMouse(name='$name')"
        }
    }

    //逻辑鼠标
//    如果父类AbsUSBMouse实现了接口方法，子类就不必须用实现，这里的USBInputDevice的input方法，被AbsUSBMouse实现
    class lojitechMouse:AbsUSBMouse("逻辑光电鼠标"){
        override fun inputOp(event: Any) {
            TODO("Not yet implemented")
        }
    }


    class Manager:Driver,Writer{
        override fun driver() {
        }

        override fun writer() {
        }

    }

    interface Driver{
        fun driver();
    }
    interface Writer{
        fun writer();
    }

    //如果不想实现Driver和Writer的接口，这样写Driver by driver等于自动调用Driver中的driver
    class SeniorManager(val driver:Driver,val writer:Writer): Driver by driver, Writer by writer

    class CarDriver :Driver{
        override fun driver() {
            println("开车")
        }
    }

    class PPTWriter :Writer{
        override fun writer() {
            println("写PPT")
        }
    }


    //父类Open才可以被继承，抽象类不用
    abstract class A{
        //父类的属性需要open才可以被覆写
        open fun x():Int = 5
    }

//    接口方法和抽象类被默认为open
    interface B{
        fun x():Int = 1
    }

    interface C{
        fun x():Int = 0
    }

//    如果一个继承了两哥接口，存在同样的时间方法，避免冲突 通过一个值来区分应该调用哪个接口，用super<B>.x()和super<C>.x()调用指定接口中的方法
    class D(var y:Int = 0):B,C{
        override fun x(): Int {
            if(y > 0){
                return y
            }else if (y < -100){
                return super<B>.x()
            }else{
                return super<C>.x()
            }
        }
    }

//    类只能单继承，接口可以多实现class E(var y:Int = 0):A(),B,C其中A()是类，只能被继承一个，B,C是接口可以同时继承很多
    //如果存在一个抽象类A也有同样的方法待实现，E又继承了A,同上方法一样解决
    class E(var y:Int = 0):A(),B,C{
        override fun x(): Int {
            if(y > 0){
                return y
            }else if (y < -200) {
                return super<B>.x()
            }else if (y < -100){
                return super<C>.x()
            }else{
                return super<A>.x()
            }
        }
    }


    interface OnExternalDriverMountListener{
        fun onMount(driver:Driver)
        fun onUnmount(driver:Driver)
    }

    //Object
    abstract class Player
    //ohbject相当于单例
    object MusicPlayer:Player(),OnExternalDriverMountListener{
        val state:Int = 0

        fun paly(url:String){

        }

        fun stop(){

        }

        override fun onMount(driver: Driver) {

        }

        override fun onUnmount(driver: Driver) {

        }
    }

//        静态方法，用companion object包裹实现
    class Latitude private constructor(val value:Double){
        companion object{
            fun ofDouble(double:Double): Latitude {
                return Latitude(double)
            }

            //给方法加上@JvmStatic标签，再在JAVA中调用就可以不用Companion
            @JvmStatic
            fun ofJDouble(double:Double): Latitude {
                return Latitude(double)
            }

            //静态变量
            @JvmField
            val TAG:String = "Latitude"
        }
    }

    //扩展成员
//    以扩展String类举例说明，给String类扩展2个成员，a和b,String.a,String.b
    val String.a:String
        get() = "abc"

    var String.b:Int
        set(value) {}
        get() = 5
    //扩展方法
//    给String类扩展1个方法,String.multipy
    fun String.multipy(int:Int): String{
        val stringBuilder = java.lang.StringBuilder()
        for (i in 0 until int){
            stringBuilder.append(this)
        }
        return stringBuilder.toString()
    }


    //属性代理
    class Delegates{
        val hello by lazy{
            println("hello")
        }

        //使用代理
//        val只需要实现getValue
        val hello2 by X()

//        var需要实现getValue和setValue
        var hello3 by X()
    }

//    导入import kotlin.reflect.KProperty
    //    要成为代理需要实现getValue和setValue方法
    class X{
        private var value:String? = null
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            // 自定义 getter 行为
            println("You got: ${property.name}")
            return value?:""
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            // 自定义 setter 行为
            println("Setting ${property.name} to $value")
        }
    }

//    数据类 data class
    //建立模型不用在手写get，set方法，并且自带toString()方法
    @NoArg
    @AllOpen
    data class Country(val id:Int,val name:String){
        fun hello(){
            println("hello from Country")
        }
    }

    class Son(string: String) : Country(0,"中国") {
        override fun hello() {
            println("hello from son")
        }
    }

    //枚举
    enum class LogLevel{
        VERBOSE,DEBUG,INFO,WARN,ERROR,ASSERT
    }

    //可以传参,可以有构造方法，和其他方法，但是写其他方法时，VERBOSE(0),DEBUG(1),INFO(2),WARN(3),ERROR(4),ASSERT(5)后面必须有分号（;）
    enum class LogLevel2(val id:Int){
        VERBOSE(0),DEBUG(1),INFO(2),WARN(3),ERROR(4),ASSERT(5);

        fun getTag(): String {
            return "$id,$name"
        }

        override fun toString(): String {
            return "$name,$ordinal"
        }
    }

}
