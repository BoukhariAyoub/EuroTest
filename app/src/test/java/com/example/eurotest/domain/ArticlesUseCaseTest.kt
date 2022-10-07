package com.example.eurotest.domain

import org.junit.Assert
import org.junit.Test

internal class ArticlesUseCaseTest {

    @Test
    fun test_longer_list_1() {
        val list1 = listOf(1, 3, 5, 7, 9)
        val list2 = listOf(2, 4, 6, 8)
        val result = list1.mix(list2)
        val expectedResult = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        Assert.assertEquals("cool", expectedResult, result)
    }

    @Test
    fun test_longer_list_2() {
        val list1 = listOf(1, 3, 5, 7)
        val list2 = listOf(2, 4, 6, 8, 10)
        val result = list1.mix(list2)
        val expectedResult = listOf(1, 2, 3, 4, 5, 6, 7, 8, 10)
        Assert.assertEquals("cool", expectedResult, result)
    }

    @Test
    fun test_same_list_size() {
        val list1 = listOf(1, 3, 5, 7)
        val list2 = listOf(2, 4, 6, 8)
        val result = list1.mix(list2)
        val expectedResult = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun test_empty_lists() {
        val list1 = emptyList<Int>()
        val list2 = emptyList<Int>()
        val result = list1.mix(list2)
        val expectedResult = emptyList<Int>()
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun test_empty_list_1() {
        val list1 = emptyList<Int>()
        val list2 = listOf(2, 4, 6, 8)
        val result = list1.mix(list2)
        val expectedResult = listOf(2, 4, 6, 8)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun test_empty_list_2() {
        val list1 = listOf(1, 3, 5, 7)
        val list2 = emptyList<Int>()
        val result = list1.mix(list2)
        val expectedResult = listOf(1, 3, 5, 7)
        Assert.assertEquals(expectedResult, result)
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