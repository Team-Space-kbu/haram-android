package com.space.data.service.board

import com.google.gson.Gson
import com.space.data.rest.BoardApi
import com.space.shared.SpaceBody
import com.space.shared.common.exception.board.AnonymousRegistrationNotAllowedException
import com.space.shared.common.exception.board.BoardAlreadyExistsException
import com.space.shared.common.exception.board.FileMoveFailedException
import com.space.shared.common.exception.board.InvalidAnonymityException
import com.space.shared.common.exception.board.InvalidTitleException
import com.space.shared.common.exception.board.NoWritePermissionException
import com.space.shared.common.exception.board.NonexistentCategoryException
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.data.board.BoardPage
import com.space.shared.model.BoardModel
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject

class BoardServiceImpl @Inject constructor(
    private val boardApi: BoardApi
) : BoardService {
    private val gson = Gson()
    override suspend fun getCategory(): List<BoardCategory> {
        return runBlocking {
            boardApi.getBoardCategory().data
        }
    }

    override suspend fun getPage(type: Int): SpaceBody<BoardPage> {
        return runBlocking {
            boardApi.getBoardPage(type)
        }
    }

    override suspend fun postBoard(type: Int, boardModel: BoardModel): SpaceBody<Boolean> {
        return runBlocking {
            try {
                boardApi.postBoard(type, boardModel)
            } catch (e: HttpException) {
                handleErrorCode(e)
                throw e
            }
        }
    }

    override suspend fun getDetail(type: BoardDetailNum): SpaceBody<BoardDetail> {
        return runBlocking {
            boardApi.getBoardDetail(type.boardSeq, type.categorySeq)
        }
    }


    private fun handleErrorCode(e: HttpException) {
        val responseBody = e.response()?.errorBody()?.string()
        val errorObject = gson.fromJson(responseBody, SpaceBody::class.java)
        when (errorObject.code) {
            "BD17" -> throw InvalidTitleException("The title value is incorrect.")
            "BD18" -> throw InvalidAnonymityException("Anonymous value is incorrect.")
            "BD15" -> throw NonexistentCategoryException("The post category does not exist.")
            "BD04" -> throw NoWritePermissionException("You do not have permission to write.")
            "BD07" -> throw AnonymousRegistrationNotAllowedException("Anonymous registration is not possible.")
            "BD19" -> throw BoardAlreadyExistsException("Board already exists.")
            "IMG05" -> throw FileMoveFailedException("Failed to move the file.")
            else -> throw IllegalArgumentException("알 수 없는 에러 코드입니다.")
        }
    }
}