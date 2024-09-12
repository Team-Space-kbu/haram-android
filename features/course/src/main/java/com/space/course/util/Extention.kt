package com.space.course.util

import com.space.shared.data.Category
import com.space.shared.data.course.Course

fun Course.toCategory(): Category {
    return Category(
        subject?: "정보없음",
        profName?: "정보없음",
        lectureFile
    )
}