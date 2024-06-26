package com.space.notice_space

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.notice_space.ui.NoticeFragment
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.startActivity
import com.space.core_ui.startFragment
import com.space.shared.data.home.Notice
import com.space.shared.data.notice_space.SpaceNoticeData
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import com.space.shared.type.NoticeSpaceType

@AndroidEntryPoint
class NoticeSpaceActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            val type by extraNotNull<String>("type")
                .map { it.decodeFromString<SpaceNoticeData>() }
            when (type.noticeSpaceType) {
                NoticeSpaceType.SPACE -> {
                    val data by extraNotNull<String>("data")
                        .map { it }
                    supportFragmentManager.startFragment<NoticeFragment>(
                        R.id.container,
                        "type" to type.encodeToString(),
                        "data" to data
                    )
                }

                else -> {
                    supportFragmentManager.startFragment<NoticeFragment>(
                        R.id.container,
                        "type" to type.encodeToString()
                    )
                }
            }

        }
    }

    companion object {
        fun open(
            context: Context,
            type: SpaceNoticeData
        ) {
            context.startActivity<NoticeSpaceActivity>(
                "type" to type.encodeToString(),
            )
        }

        fun open(
            context: Context,
            type: SpaceNoticeData,
            data: Notice
        ) {
            context.startActivity<NoticeSpaceActivity>(
                "type" to type.encodeToString(),
                "data" to data.encodeToString()
            )
        }
    }
}