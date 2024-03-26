package com.space.signup.ui.policy

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.Fill2wayButtonAdapter
import com.space.core_ui.binding.adapter.PolicyAdapter
import com.space.core_ui.showToast
import com.space.core_ui.transformFragment
import com.space.shared.data.core_ui.PolicyForm
import com.space.shared.encodeToString
import com.space.signup.ui.email.VerifyEmailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = PolicyFragment()
    }

    private val viewModel: PolicyViewModel by viewModels()
    private lateinit var adapter: RecyclerView.Adapter<*>
    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun beforeObserverListener() {
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
        viewModel.uiStatus.observe(this) {
            if (it) {
                parentFragmentManager.transformFragment<VerifyEmailFragment>(
                    R.id.container,
                    "policy" to viewModel.userPolicy.encodeToString()
                )
            }
        }
        viewModel.policySingup.observe(this) { userPolicy ->
            val policy = userPolicy.map { PolicyForm(it, it.title, it.content) }
            adapter = PolicyAdapter(policy) { data, isChecked ->
                viewModel.setPolicy(data, isChecked)
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.policySingup.observe(this) {
            binding.recyclerView.adapter =
                Fill2wayButtonAdapter(adapter, { activity?.finish() }) {
                    viewModel.policy()
                }
        }
    }
}