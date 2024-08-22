package com.space.other

import android.annotation.SuppressLint
import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.binding.adapter.func.FuncAdapter
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.util.showToast
import com.space.core_ui.extension.startOpenBrowser
import com.space.other.adapter.LineAdapter
import com.space.other.adapter.SettingAdapter
import com.space.other.adapter.ShimmerAdapter
import com.space.other.adapter.UserAdapter
import com.space.shared.type.SettingType
import com.space.shared.data.auth.User
import com.space.shared.data.core_ui.Func
import com.space.shared.data.notice_bible.NoticeViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment : ContainerCustomFragment<FragmentEmtpyContainerBinding, User>(
    com.space.core_ui.R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = OtherFragment()
    }

    private var adapter: RecyclerView.Adapter<*> = ShimmerAdapter()
    override val viewModel: OtherViewModel by viewModels()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        val job = requireContext().getDrawable(R.drawable.ic_job)!!
        val bible = requireContext().getDrawable(R.drawable.ic_bible)!!
        adapter = ConcatAdapter(
            UserAdapter(data) {
                viewModel.navigatorUser.openView(requireContext())
            },
            FuncAdapter(Func(job, "취업정보")) {
                viewModel.navigatorNotice.openView(requireContext(), NoticeViewType.JOB)
            },
            FuncAdapter(Func(bible, "교회(사역) 채용공고"))
            {
                viewModel.navigatorNotice.openView(requireContext(), NoticeViewType.BIBLE)
            },
            LineAdapter(),
            SettingAdapter { settingHandler(it) }
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(com.space.core_ui.R.dimen.margin_10dp)
            )
        )


    }

    override fun afterObserverListener() {
        viewModel.logout.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.navigatorLogin.openView(requireContext())
                activity?.finish()
                requireContext().showToast("로그아웃되었습니다.")
            } else {
                requireContext().showToast("로그아웃중 오류가 발생했습니다.")
            }
        }
    }

    private fun settingHandler(type: SettingType) {
        when (type) {
            SettingType.LOGOUT -> viewModel.logout()
            SettingType.LICENSES -> {
                startActivity(
                    Intent(
                        requireContext(),
                        OssLicensesMenuActivity::class.java
                    )
                )
                OssLicensesMenuActivity.setActivityTitle(
                    getString(R.string.opensource_licenses)
                )
            }

            SettingType.SPACE_POLICY ->
                requireContext().startOpenBrowser("https://team-spaces.notion.site/51257ec335724f90ad69ce20ae3e2393")

            SettingType.PRIVACY_POLICY ->
                requireContext().startOpenBrowser("https://team-spaces.notion.site/238de2ae5b7a4000a40492037ed35640")

            SettingType.SERVICE_CENTER ->
                requireContext().startOpenBrowser("https://team-spaces.notion.site/027b854625fe4d2f8b2938f63a2532f2")

            else -> {

            }
        }
    }
}