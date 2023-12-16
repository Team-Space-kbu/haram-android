package com.space.home.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.space.shared.data.home.Kokkos


fun Context.startOpenPdf(kokkos: Kokkos) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
            .setDataAndType(Uri.parse(kokkos.file), "application/pdf")
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(this, "현재 사용중인 디바이스는 pdf 읽기를 지원하지 않습니다.", Toast.LENGTH_LONG).show()
    }
}
