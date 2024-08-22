package com.space.notice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.R
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.extension.startActivity
import com.space.core_ui.extension.startFragment
import com.space.notice.ui.home.NoticeHomeFragment
import com.space.notice.ui.search.NoticeSearchFragment
import com.space.shared.data.notice_bible.NoticeType
import com.space.shared.data.notice_bible.NoticeViewType
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeActivity : AppCompatActivity() {

    private val type by extraNotNull<String>("type")
        .map { encodeString ->
            encodeString.decodeFromString<NoticeViewType>()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            when (type) {
                NoticeViewType.DEFAULT -> {
                    supportFragmentManager.startFragment<NoticeHomeFragment>(
                        R.id.container
                    )
                }

                NoticeViewType.JOB -> {
                    supportFragmentManager.startFragment<NoticeSearchFragment>(
                        R.id.container,
                        "search" to NoticeType("job-student","취업정보").encodeToString()
                    )
                }

                NoticeViewType.BIBLE -> {
                    supportFragmentManager.startFragment<NoticeSearchFragment>(
                        R.id.container,
                        "search" to NoticeType("job-church","사역정보").encodeToString()
                    )
                }
            }

        }
    }

    companion object {
        fun open(context: Context, noticeViewType: NoticeViewType) {
            context.startActivity<NoticeActivity>(
                "type" to noticeViewType.encodeToString()
            )
        }
    }
}