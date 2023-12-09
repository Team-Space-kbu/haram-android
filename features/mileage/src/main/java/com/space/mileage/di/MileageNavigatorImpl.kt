package com.space.mileage.di

import android.content.Context
import com.space.mileage.MileageActivity
import com.space.navigator.NavigatorMileage
import javax.inject.Inject

class MileageNavigatorImpl @Inject constructor(): NavigatorMileage {
    override fun openMileage(context: Context) {
        MileageActivity.open(context)
    }
}