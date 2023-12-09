package com.space.partners.di

import android.content.Context
import com.space.navigator.NavigatorPartners
import com.space.partners.PartnersActivity
import javax.inject.Inject

class PartnersNavigatorImpl @Inject constructor(): NavigatorPartners {
    override fun openPartners(context: Context) {
        PartnersActivity.open(context)
    }
}