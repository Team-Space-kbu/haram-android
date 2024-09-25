package com.space.pdf_viewer.ui

import androidx.lifecycle.lifecycleScope
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.util.showToast
import com.space.pdf_viewer.R

import com.space.pdf_viewer.databinding.FragmentViewerBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ViewerFragment : BaseFragment<FragmentViewerBinding>(
    R.layout.fragment_viewer
) {
    private val pdf by extraNotNull<String>("pdf").map { pdf ->
        if (pdf.isEmpty() || pdf.isBlank()) {
            Timber.e("pdf is null or empty, $pdf")
            requireContext().showToast("PDF 정보가 정확하지 않습니다. 나중에 다시 시도해주세요.")
            activity?.finish()
        }
        pdf
    }

    companion object {
        fun newInstance() = ViewerFragment()
    }

    override fun initView() {
        super.initView()

        binding.pdfView.initWithUrl(
            url = pdf,
            lifecycleCoroutineScope = lifecycleScope,
            lifecycle = lifecycle
        )
    }


}