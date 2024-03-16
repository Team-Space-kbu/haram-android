package com.space.signup.ui.policy

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.signup.R

class PolicyFragment : Fragment() {

    companion object {
        fun newInstance() = PolicyFragment()
    }

    private val viewModel: PolicyViewModel by viewModels()


}