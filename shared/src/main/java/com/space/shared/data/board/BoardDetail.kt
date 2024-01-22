package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardDetail(
    val boardSeq: Int,
    val boardTitle:String,
    val userId:String,
    val boardContent:String,
    val boardFileList:List<BoardFile>,
    val createdAt:String,
    val modifiedAt:String,
    val boardType:String,
    val commentDtoList:List<BoardComment>
)
