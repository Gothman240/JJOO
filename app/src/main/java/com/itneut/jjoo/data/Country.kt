package com.itneut.jjoo.data

data class Country(
    val id: Long,
    val position: Int,
    val name: String,
    val flag: String,
    val goldMedals: Int,
    val silverMedals: Int,
    val bronzeMedals: Int
)
