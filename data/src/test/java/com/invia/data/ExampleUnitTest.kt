package com.invia.data

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val sum =11;
        var list = listOf(1,2,3,4,5,6,7,8)

        var front = 0
        var back = list.size-1
        var isLoop = true;
        var iteration = 1;
        while( isLoop){
            if( list[front] <= list[back] ){
                var tempSum = list[front] + list[back]
                if(  tempSum== sum ){
                    println("==>${iteration++}, final pointer: $front(${list[front]}) ==>$back(${list[back]}), = ${list[front]+list[back]}, ")

                    isLoop = false
                }else if( tempSum < sum ){
                    front++
                }else{
                    back--
                }
            }
            println("==>${iteration++}, pointer: $front(${list[front]}) ==>$back(${list[back]}), = ${list[front]+list[back]}, ")
        }
    }
}