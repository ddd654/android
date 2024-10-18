package com.coding404.myfirstapp

import com.example.firstapp.QuizDTO
import com.example.firstapp.R


//object >> 싱글톤 클래스가 됨
object QuizRepo {
    fun getQuiz() : ArrayList<QuizDTO> { //지역변수로
    
    val quizList = ArrayList<QuizDTO>()

    
        val quiz01 = QuizDTO(1, "세계에서 가장 많은 인구를 가진 국가는 어디인가요?", R.drawable.quiz01 ,"미국", "인도", "중국", "브라질", 3)
        val quiz02 = QuizDTO(2, "태양계에서 몇 개의 행성이 있나요", R.drawable.quiz02 ,"7개", "8개", "9개", "10개", 2)
        val quiz03 = QuizDTO(3, "지구에서 가장 높은 산은 무엇인가요?", R.drawable.quiz03 ,"백두산", "한라산", "참이슬", "에베레스트", 4)
        val quiz04 = QuizDTO(4, "아프리카 대륙에 속하지 않는 나라는 어디인가요?", R.drawable.quiz04 ,"남아프리카", "나이지리아", "이집트", "아르헨티나", 4)
        val quiz05 = QuizDTO(5, "유럽에서 가장 큰 강은 어디인가요?", R.drawable.quiz05 ,"센강", "다나우강", "세이나강", "불가강", 1)
        val quiz06 = QuizDTO(6, "세계에서 가장 큰 사막은 어디인가요?", R.drawable.quiz06 ,"카라쿠름 사막", "사하라 사막", "가비 사막", "실리안 사막", 2)
        val quiz07 = QuizDTO(7, "세계에서 가장 큰 도시는 어디인가요?", R.drawable.quiz07 ,"도쿄", "베이징", "상하이", "뉴욕", 3)
        val quiz08 = QuizDTO(8, "국제적으로 가장 널리 사용되는 언어는 무엇인가요?", R.drawable.quiz08 ,"영어", "중국어", "일본어", "러시아어", 1)
        val quiz09 = QuizDTO(9, "지구상에서 가장 큰 대륙은 어디인가요?", R.drawable.quiz09 ,"아시아", "아프리카", "유럽", "북아메리카", 1)
        val quiz10 = QuizDTO(10, "훈민정음을 창제한 왕은 누구입니까?", R.drawable.quiz10 ,"태조", "정종", "태종", "세종", 4)

        quizList.add(quiz01)
        quizList.add(quiz02)
        quizList.add(quiz03)
        quizList.add(quiz04)
        quizList.add(quiz05)
        quizList.add(quiz06)
        quizList.add(quiz07)
        quizList.add(quiz08)
        quizList.add(quiz09)
        quizList.add(quiz10)

        return quizList
    }

}