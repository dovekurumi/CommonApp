package com.jidouauto.testapp

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


object EnterClass{
    /**
     *   协程挂起操作
     *   不阻塞作用域（异步作用域） : launch / async
     *   阻塞作用域(同步作用域) ： withContext（） / runBlocking  /   supervisorScope  / coroutineScope
     */
    @JvmStatic
    fun main(args: Array<String>){

        runBlocking {
            //不阻塞作用域
            val launch = CoroutineScope(Dispatchers.Default).launch(CoroutineName("xxx")) {
                delay(1000)
                println("delay 1s")
            }
            //阻塞作用域
            withContext(Job() + Dispatchers.IO){
                delay(2000)
                println("withContext")
            }
//            MainScope(Dispatchers.Default).launch(){
//                delay(1000)
//                println("MainScope")
//            }


            launch {
                println("launch1")
//                try {
//                    1/0
//                }catch (e : java.lang.Exception){
//                    println("${e.message}")
//                }
            }
            //阻塞作用域
            runBlocking {
                delay(2000)
                println("runBlocking 2s")
            }
            //阻塞作用域
            var sup = supervisorScope{
                println("supervisorScope")
//               try {
//                    1/0
//                }catch (e : java.lang.Exception){
//                    println("${e.message}")
//                }
                val launch1 = launch {
                    delay(5000)
                    println("delay 5s")
                }
                val async = async {
                    delay(2000)
                    println("delay 2s")
                    2
                }
                println("awit${async.await()}")

                "supResult"
            }
            println("${sup}")

            //阻塞作用域
            var cos = coroutineScope {
                launch {
                    println("coroutineScope ")
                }
                try {
                    1/0
                }catch (e : java.lang.Exception){
                    println("${e.message}")
                }
                "coroutineScopeResult"
            }
            println("${cos}")
            CoroutineScope(Dispatchers.Default).async {
                delay(2000)
                println("async2 delay 2s")
            }
            launch {
                delay(1000)
                println("launch2 delay 1s")
            }
        }
    }
}
object CoroutineMain {

    @JvmStatic
    fun main(args: Array<String>){

        runBlocking{
            launch {
                val stringFlow = flow<String> {
                    (1..4).forEach {
                        emit("${it}")
                    }
                }
            }
    }

}

    class ClassOuter {
        var `object`: Any = object : Any() {

            fun finalize() {
                println("inner Free the occupied memory...")
            }
        }

        fun finalize() {
            println("Outer Free the occupied memory...")
        }
    }

    object TestInnerClass {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                Test()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            System.gc()
        }
        var `object`: Any? = null
        @Throws(InterruptedException::class)
        private fun Test() {
            println("Start of program.")
            var outer: ClassOuter? = ClassOuter()
            `object` = outer!!.`object`
            outer = null
            println("Execute GC")
            System.gc()
            Thread.sleep(3000)
            println("End of program.")
        }
    }



class ConcatTest {
    companion object {

        @JvmStatic
        fun main(args: Array<String>){

            runBlocking(){
                launch {
                  flow<String> {
                      (1..5).forEach {
                          emit("$it")
                      }
                  }
                      .flowOn(Dispatchers.IO)
                      .onCompletion {

                      }
                      .collect {
                      println(it)
                  }
                }

                var channel = Channel<Int>()
                launch {
                    for (i in 1..5) {
                        channel.send(i)
                    }

                    repeat(Int.MAX_VALUE){
                        println("receive == ${channel.receive()}" )
                    }
                }

                supervisorScope {
                    launch {

                        flow<String> {
                            emit("1")
                        }.map {
                            it.toInt()
                        }.map {

                        }

                        (1..5).asFlow().filter {
                            it < 3
                        }.collect(

                        )

                        val asFlow1 = (1..3).asFlow()
                        val asFlow2 = flowOf("1", "2", "3")

                        val toList = asFlow1.toList()

                        asFlow2.zip(asFlow1) {t1,t2->
                            "t1 == $t1 t2 == $t2"
                        }
                            .catch {

                            }
                            .map {
                                1
                            }
                            .map {
                                2
                            }
                            .collect {

                        }

                    }

                }

            }


        }

        }
    }
}