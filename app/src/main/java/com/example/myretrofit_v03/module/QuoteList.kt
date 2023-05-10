package com.example.myretrofit_v03.module

data class QuoteList (
    val count:Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)