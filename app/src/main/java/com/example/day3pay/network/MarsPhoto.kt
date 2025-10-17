package com.example.day3pay.network

import com.squareup.moshi.Json

//VO,POJO,Model

data class MarsPhoto (
    val id: String,
@Json(name = "img_src")
val imgSrc: String)