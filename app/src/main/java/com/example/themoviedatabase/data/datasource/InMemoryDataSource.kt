package com.example.themoviedatabase.data.datasource

import android.util.Log

class InMemoryDataSource: MyDataSource {

    private var numbers = mutableListOf<Int>()

    override fun addNumber(number: Int) {
        numbers.add(number)
    }

    override fun printNumbers() {
        for (n: Int in numbers) {
            Log.i("Numbers", n.toString())
        }
    }

}