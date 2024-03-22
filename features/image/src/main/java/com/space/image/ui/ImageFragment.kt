package com.space.image.ui

import android.annotation.SuppressLint
import android.view.ScaleGestureDetector
import com.space.core_ui.NonParamsItemHandler
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.image.BR
import com.space.image.R
import com.space.image.databinding.FragmentImageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : BaseFragment<FragmentImageBinding>(
    R.layout.fragment_image
) {
    private val image by extraNotNull<String>("image").map { it }
    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f

    override fun init() {
        mScaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        binding.setVariable(BR.imageUri, image)
        binding.setVariable(BR.imageHandler, NonParamsItemHandler {
            activity?.finish()
        })
        binding.image.setOnTouchListener { _, event ->
            mScaleGestureDetector!!.onTouchEvent(event)
        }
        binding.image.bringToFront()
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = 0.5f.coerceAtLeast(scaleFactor.coerceAtMost(2.0f))
            binding.image.scaleX = scaleFactor
            binding.image.scaleY = scaleFactor
            return true
        }
    }
}