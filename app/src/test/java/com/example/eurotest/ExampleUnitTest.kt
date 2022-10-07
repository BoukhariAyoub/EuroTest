package com.example.eurotest

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
        val list1 = listOf(1, 3, 5, 7, 9)
        val list2 = listOf(2, 4, 6, 8)
        val result = list1.mix(list2)
        val expectedResult = listOf(1, 2, 3, 4, 5, 6, 7,8,9)
        assertEquals("cool", expectedResult, result)
    }

    fun <T> List<T>.mix(other: List<T>): List<T> {
        val first = iterator()
        val second = other.iterator()
        val list = ArrayList<T>(minOf(this.size, other.size))

        while (first.hasNext() || second.hasNext()) {
            if (first.hasNext()) list.add(first.next())
            if (second.hasNext()) list.add(second.next())
        }
        return list
    }
}