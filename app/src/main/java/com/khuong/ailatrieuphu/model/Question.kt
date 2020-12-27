package com.khuong.ailatrieuphu.model

data class Question(
    val id: Int,
    val level: Int,
    val question: String,
    val caseA: String,
    val caseB: String,
    val caseC: String,
    val caseD: String,
    val truecase: Int
)