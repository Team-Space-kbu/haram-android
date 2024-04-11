package com.space.signup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.showToast
import com.space.core_ui.startActivity
import com.space.core_ui.startFragment
import com.space.shared.type.SingupType
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import com.space.signup.ui.find.FindPwEmailFragment
import com.space.signup.ui.policy.PolicyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private val type by extraNotNull<String>("type")
        .map { encodeString ->
            encodeString.decodeFromString<SingupType>()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            when (type) {
                SingupType.SINGUP -> {
                    supportFragmentManager.startFragment<PolicyFragment>(
                        R.id.container
                    )
                }
                SingupType.FIND_PW->{
                    supportFragmentManager.startFragment<FindPwEmailFragment>(
                        R.id.container
                    )
                }
                else -> {
                    showToast("잘못된 값이 존재합니다.")
                }
            }

        }
    }

    companion object {
        fun open(context: Context, type: SingupType) {
            context.startActivity<SignupActivity>(
                "type" to type.encodeToString()
            )
        }
    }

}