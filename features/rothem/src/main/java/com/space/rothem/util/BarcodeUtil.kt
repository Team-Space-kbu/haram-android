package com.space.rothem.util

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


fun createBarcode(code: String) : Bitmap{
    val barcodeEncoder = BarcodeEncoder()
    return barcodeEncoder.encodeBitmap(code, BarcodeFormat.CODE_128, 512, 512)
}
fun createQrcode(code:String): Bitmap {
    val barcodeEncoder = BarcodeEncoder()
    return barcodeEncoder.encodeBitmap(code, BarcodeFormat.QR_CODE, 512, 512)
}
