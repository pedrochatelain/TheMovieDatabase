package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.MyDataSource
import kotlinx.coroutines.delay
import javax.inject.Inject

class Repository @Inject constructor(private val dataSource: MyDataSource)  {

    fun addNumber(number: Int) {
        dataSource.addNumber(number)
    }

    fun printNumbers() {
        dataSource.printNumbers()
    }

    suspend fun fakeNetworkCall(): String {
        delay(1500)
        return "Network call finished"
    }

}