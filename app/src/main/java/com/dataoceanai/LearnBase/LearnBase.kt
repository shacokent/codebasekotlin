package com.dataoceanai.LearnBase

//fun main(array: Array<String>) {
//    val learn: LearnBase = LearnBase()
//    learn.learn()
//}

class LearnBase {
    private var isbool : Boolean = true
    //匿名函数
//    val int2Long = fun(x:Int):Long{
//        x.toLong()
//    }
//    简写后
    val int2Long = fun(x:Int):Long = x.toLong()


    val printLambad = fun(printlambadval:()->Unit){
        printlambadval()
    }

    enum class State{
        A,B,C,D,E,F,G
    }
    private var state = State.A

    init {
        learn()
    }

    //这里的Unit相当于Java的void
//    fun learn():Unit{
    fun learn(){
//        注意
//        val初始化赋值后不能更改，相当于java的final,类似swift的let
//        var 可以更改，同swift

        //特殊
//        输出整形字符串
        val arg1:Int = 0
        val arg2:Int = 1
        println("$arg1 + $arg2 = ${arg1 + arg2}")

        //可空类型
        //?: 意思是如果是null就return，类似swift的guard
//        val name:String = getName()?:return
        val name:String = getName()?:"return"//为了不让程序直接结束，改为返回字符串
        print(name.length)

        //如果要打印可空数据类型，在确定value不为空时，可以用!!修饰打印，类似swift强制解包
        val value:String? = "helloWorld"
        print(value!!.length)

        //智能类型转换,不用想Java一样强转
        val parent: Parent = Child()
        if(parent is Child){
            println(parent.name)
            println(parent.toString())
        }

        val string:String? = "hello"
        if(string != null){
            println(string.length)
        }

        //Parent强转Child, 用as?避免强转时，类型转换异常崩溃,as?转换不成功返回null
        val parent2: Parent = Parent("123", 10)
        val child: Child? = parent2 as? Child

        //区间
        val range:IntRange = 0..1024//闭区间[0,1024]
        val range_exclusive:IntRange = 0 until  1024//半开区间[0,1024) = 【0，1023】

        val emptyRange : IntRange = 0..-1
        println(emptyRange.isEmpty())//不存在大于0小于-1的区间，返回true
        println(range.contains(50))//是否包含50
        println(50 in range)//等同range.contains(50)

        for(i in range_exclusive){
            print("$i,")
        }

        //数组
        val arrayOfInt : IntArray = intArrayOf(1,3,5,7)
        val arrayOfChar: CharArray = charArrayOf('h','e','l','l','o')
        val arrayOfString: Array<String> = arrayOf("hello","world")

        println(arrayOfInt.size)
        for(i in arrayOfChar){
            print("$i,")
        }

        println(arrayOfString[0])
        println(arrayOfChar.joinToString("-"))//连接字符串
        println(arrayOfInt.slice(1..2))//取出指定位置的几个字符串

        //打印类名
        println(LearnBase::class.java.simpleName)

        //函数简写
        println(sum(1,3))
        // 匿名函数,相当于代码块block
        println(int2Long(3))

        //lambda表达式
        val lambdasum = {num1:Int, num2:Int ->
            println("$num1 + $num2 = ${num1 + num2}")
            num1 + num2
        }
        println(lambdasum(1,5))//等同于println(lambdasum.invoke(1,5))

//        无参数可以省略传入参数类型声明
        val printlnHello = {
            println("hello")
        }
        //传递一个lambda表达式
        printLambad(printlnHello)

        //forEach本质是lambda表达式，
//        原型 arrayOfChar.forEach { it: Char -> print(it)}
//        正常写法
//        arrayOfChar.forEach {
//            print(it)
//        }
        //可简写为
        arrayOfChar.forEach(::println)
        println("\n")
        //跳出forEach，实现Continue和break
//        run ForEachBreak@{
//            arrayOfChar.forEach ForEachContinue@{
//                if(it == 'e') return@ForEachContinue //continue
//                if(it == 'o') return@ForEachBreak //break
//                println(it)
//            }
//        }
        //简写
        run{
            arrayOfChar.forEach {
                if(it == 'e') return@forEach //continue
                if(it == 'o') return@run //break
                println(it)
            }
        }

//        if表达式
        //在kotlin中给 val修饰的值初始化，需要根据条件赋值的情况，可以用if表达式，这里if表达式有返回值
        val mode = if("1" == "2"){
            "mode"
        } else{
            "elsemode"
        }
        println(mode)

//       when表达式
//      kotlin中没有switch表达式，用when表达式代替,不用break执行一个条件就停止
        when(state){
            State.A,State.B -> AB()
            else->{
//                do anything
            }
        }

        //更强的功能
        val y = 5
        when(y){
            is Int -> println("是int类型")
            in 1..100 -> println("在1到100之间")
            !in 1..100 -> println("不在1到100之间")
            5 -> println("是等于5")
        }

        //也可以同if用法，有返回值
        val whenmode = when(y){
            5 -> 6
            else -> 0
        }
        println(whenmode)

//        for循环
        val args = charArrayOf('a','b','c')
//        写法1
        for ((index,value)in args.withIndex()){
            println("$index -> $value")
        }
//        写法2
        for (indexedValue in args.withIndex()){
            println("${indexedValue.index} -> ${indexedValue.value}")
        }

//        可以用标签跳出多层循环
        myfor@ for (indexedValue in args.withIndex()){
            for (indexedValue in args.withIndex()){
                if(indexedValue.index == 2){
                    println("${indexedValue.index} -> break")
                    break@myfor//直接跳出最外层的循环
                }
            }
        }

        //while循环
        var z = 5
        while (z >= 4){
            println(z)
            z--
        }

        //do..while
        z = 5
        do{
            println(z)
            z--
        }while (z >= 4)

        //异常捕获
        try {
            1/0
        }catch (e:Exception){
            println(e.message)
        }
        finally {
            println("finally")
        }

        //具名参数
        sum(num1 = 1, num2 = 2)
//        变长参数
        longstring("h","e","l","l","o")
        //,在kotlin中边长参数比一定需要作为最后一个参数传递，因为可以使用具名参数，如果不作为最后一个参数，需要使用具名参数赋值
        longstringmore(3.0,1,2,3,string = "hello")
//       另一种写法， 边长参数支持数组展开传递，用*array
        val arrayvararg = intArrayOf(1,2,3)
        longstringmore(3.0,*arrayvararg,string = "hello")
//        当Double默认值是3.0，如果不穿默认是3.0，但是传参时他后面的参数需要传递具名参数,所以一般默认参数写在最后，这样可以省略传参，并且不用使用具名参数
        longstringmore(ints = *arrayvararg,string = "hello")



        println("end")
    }

    fun AB(){
        println("AB")
    }

    //函数简写
//    fun sum(num1 :Int, num2:Int):Int{
//        return num1 + num2
//    }
//    简写后
    fun sum(num1 :Int, num2:Int) = num1 + num2

    fun longstring(vararg strs:String) {
        for(str in strs){
            print(str)
        }
        print("\n")
    }

//    double默认参数
    fun longstringmore(double:Double = 3.0, vararg ints:Int, string:String) {
        println(double)
        ints.forEach(::println)
        println(string)
    }

    fun getName():String?{
        return null
    }

    class X
    open class Parent(name:String, age:Int){
//        var可以定义set和get，val只能定义get
//        var gender = "0"
//        get() {
//            return field
//        }
//        set(value) {
//            field = value
//        }

        //简写
        protected var gender = "0"
        get
        protected set

        //lateinit延迟初始化，之后要处理好，正放在var前面
        lateinit var c:String
        //如果是val用lazy
        val e: X by lazy {
            X()
        }

        init {

        }
    }

    class Child : Parent("123",10) {
        var name:String ="456"
        var age :Int = 20
        override fun toString(): String {
            return "Child(name='$name', age=$age)"
        }

    }
}