package com.space.biblemon.ui.book.home


import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.space.biblemon.BR
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentBookHomeBinding
import com.space.biblemon.util.FragmentFactory
import com.space.biblemon.util.ViewType
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookHomeFragment : BaseFragment<FragmentBookHomeBinding>(R.layout.fragment_book_home) {

    companion object {
        fun newInstance() = BookHomeFragment()
    }

    private val viewModel: BookHomeViewModel by viewModels()

    override fun init() {
        super.init()
        toolbarTitle = "도서"
    }
    override fun initView() {
        super.initView()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                return activity!!.finish()
            }
        }
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.bookHomeSearch.windowToken, 0)
    }

    override fun afterObserverListener(): Unit = with(viewModel) {
        super.afterObserverListener()
        viewListener.observe(viewLifecycleOwner) {
            if (it.viewStatus) {
                setFragmentResult("detailKey", bundleOf("pathUrl" to it.viewPath))
                newFragmentView(FragmentFactory.createFragment(ViewType.BOOK_DETAIL)!!)
                bindingViewListener.clearViewType()
            }
            if (it.keyEvent) {
                val result = binding.bookHomeSearch.text.toString().replace("\n", "")
                setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                newFragmentView(FragmentFactory.createFragment(ViewType.BOOK_SEARCH)!!)
                viewModel.bindingKeyListener.keyEventEnd()
            }
        }
    }


}