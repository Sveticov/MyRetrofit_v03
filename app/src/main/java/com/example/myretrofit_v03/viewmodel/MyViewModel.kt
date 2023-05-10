package com.example.myretrofit_v03.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myretrofit_v03.module.Result
import com.example.myretrofit_v03.retrofit.QuotesApi
import com.example.myretrofit_v03.retrofit.RetrofitHelper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    var quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)

    var listResult: MutableList<Result> = emptyList<Result>().toMutableList()

    var list = MutableStateFlow<List<Result>>(emptyList())
    val listResultFlow:SharedFlow<List<Result>> = list

    fun getQuotes() {
        viewModelScope.launch {


            val result = quotesApi.getQuotes()
            if (result != null) {
             list.emit(result.body()!!.results)
                for (res in result.body()!!.results) {
                    Log.d("result", res.toString())
                    listResult.add(res)

                }
            }
        }
    }

    fun getMyList():List<Result>{
        Log.d("list",listResult.toString())
        return listResult
    }
}


