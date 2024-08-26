package com.space.signup.ui.policy

import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.view.Fill2wayButtonAdapter
import com.space.core_ui.binding.adapter.item.PolicyAdapter
import com.space.core_ui.util.showToast
import com.space.core_ui.extension.transformFragment
import com.space.shared.data.core_ui.PolicyForm
import com.space.shared.encodeToString
import com.space.signup.ui.binding.adapter.ShimmerAdapter
import com.space.signup.ui.email.SendEmailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = PolicyFragment()
    }

    private var adapter: RecyclerView.Adapter<*> = ShimmerAdapter()
    private val viewModel: PolicyViewModel by viewModels()

    override fun initView() {
        viewModel.clearPolicy()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_20dp)
            )
        )
    }

    override fun beforeObserverListener() {
        viewModel.toastMessage.observe(this) {
            requireContext().showToast(it)
        }
        viewModel.uiStatus.observe(this) {
            if (it) {
                parentFragmentManager.transformFragment<SendEmailFragment>(
                    R.id.container,
                    "policy" to viewModel.userPolicy.encodeToString()
                )
            }
        }
    }


    override fun afterObserverListener() {
        viewModel.policySingup.observe(this) { userPolicy ->
            val policy = userPolicy.map {
                PolicyForm(
                    it,
                    it.title,
                    HtmlCompat.fromHtml(it.content, HtmlCompat.FROM_HTML_MODE_LEGACY)
                )
            }
            adapter = Fill2wayButtonAdapter(
                PolicyAdapter(policy) { data, isChecked ->
                    viewModel.setPolicy(data, isChecked)
                },
                {
                    activity?.finish()
                },
                {
                    viewModel.policy()
                }
            )
            binding.recyclerView.adapter = adapter
        }
    }
}