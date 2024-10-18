package com.example.firstapp


//값 저장용 데이터 만들기
//data class
data class QuizDTO(
    val id : Int,
    val quiz : String,
    val img : Int,
    val one :String,
    val two :String,
    val three :String,
    val four :String,
    val answer : Int // 정답
)