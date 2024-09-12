package com.space.course.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.space.core_ui.util.showToast
import com.space.shared.data.course.Course


fun Context.startOpenPdf(course: Course) {
    try {
        if (course.lectureFile.isNullOrEmpty()) throw NullPointerException()
        val intent = Intent(Intent.ACTION_VIEW)
            .setDataAndType(Uri.parse(course.lectureFile), "application/pdf")
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    } catch (e: NullPointerException) {
        this.showToast("강의계획서가 존재하지 않습니다.")
    } catch (e: Exception) {
        this.showToast("현재 사용중인 디바이스는 pdf 읽기를 지원하지 않습니다.")
    }
}
