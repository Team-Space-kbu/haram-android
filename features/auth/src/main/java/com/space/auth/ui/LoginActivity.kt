package com.space.auth.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.auth.ui.intranet.IntranetFragment
import com.space.auth.ui.login.LoginFragment
import com.space.core_ui.startActivity
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.shared.data.auth.AuthType
import com.space.shared.decodeFromString
import com.space.shared.encodeToString

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val type by extraNotNull<String>("type")
        .map { encodeString ->
            encodeString.decodeFromString<AuthType>()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(
                    R.id.container,
                    when (type) {
                        AuthType.INTRANET -> IntranetFragment.newInstance()
                        AuthType.LOGIN -> LoginFragment.newInstance()
                    }
                )
            }
        }
    }


    companion object {
        fun open(context: Context, authType: AuthType) {
            context.startActivity<LoginActivity>(
                "type" to authType.encodeToString()
            )
        }
    }
}