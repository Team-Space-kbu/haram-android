package com.space.repository.repository

import javax.inject.Inject

class BibleRepository @Inject constructor(

) {
    private val bible = linkedMapOf(
        "창세기" to 50,
        "출애굽기" to 40,
        "레위기" to 27,
        "민수기" to 36,
        "신명기" to 34,
        "여호수아" to 24,
        "사사기" to 21,
        "룻기" to 4,
        "사무엘상" to 31,
        "사무엘하" to 24,
        "열왕기상" to 22,
        "열왕기하" to 25,
        "역대상" to 29,
        "역대하" to 36,
        "에스라" to 10,
        "느헤미야" to 13,
        "에스더" to 10,
        "욥기" to 42,
        "시편" to 150,
        "잠언" to 31,
        "전도서" to 12,
        "솔로몬" to 8,
        "이사야" to 66,
        "예레미야" to 52,
        "예레미야" to 5,
        "에스겔" to 48,
        "다니엘" to 12,
        "호세아" to 14,
        "요엘" to 3,
        "아모스" to 9,
        "오바댜" to 1,
        "요나" to 4,
        "미카" to 7,
        "나훔" to 3,
        "하박국" to 3,
        "스파냐" to 3,
        "학개" to 2,
        "스카랴" to 14,
        "말라키" to 4,
        "마태복음" to 28,
        "마가복음" to 16,
        "누가복음" to 24,
        "요한복음" to 21,
        "사도행전" to 28,
        "로마서" to 16,
        "고린도전서" to 16,
        "고린도후서" to 13,
        "갈라디아서" to 6,
        "에베소서" to 6,
        "빌립보서" to 4,
        "골로새서" to 4,
        "데살로니가전서" to 5,
        "데살로니가후서" to 3,
        "디모데전서" to 6,
        "디모데후서" to 4,
        "디도서" to 3,
        "빌레몬서" to 1,
        "히브리서" to 13,
        "야고보서" to 5,
        "베드로전서" to 5,
        "베드로후서" to 3,
        "요한일서" to 5,
        "요한이서" to 1,
        "요한삼서" to 1,
        "유다서" to 1,
        "요한계시록" to 22,
    )

    fun getBibleData(): LinkedHashMap<String, Int> {
        return this.bible
    }

    fun findBibleChapter(chapter: String): Boolean {
        return bible.containsKey(chapter)
    }


}