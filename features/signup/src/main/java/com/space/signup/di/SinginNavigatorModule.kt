package com.space.signup.di

import android.content.Context
import com.space.navigator.view.NavigatorSingup
import com.space.navigator.view.NavigatorUser
import com.space.shared.type.SingupType
import com.space.signup.SignupActivity
import com.space.signup.UserActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SingupNavigatorModule {
    @Binds
    abstract fun providerSingupNavigator(
        impl: SingupNavigatorImpl
    ): NavigatorSingup

    @Binds
    abstract fun providerUserNavigator(
        impl: UserNavigatorImpl
    ): NavigatorUser
}


internal class SingupNavigatorImpl @Inject constructor(

) : NavigatorSingup {
    override fun openView(context: Context, type: SingupType) {
        SignupActivity.open(context, type)
    }

    override fun openView(context: Context) {
        SignupActivity.open(context, SingupType.SINGUP)
    }

}

internal class UserNavigatorImpl @Inject constructor(

): NavigatorUser{
    override fun openView(context: Context) {
        UserActivity.open(context)
    }

}