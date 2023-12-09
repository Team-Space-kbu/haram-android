package com.space.partners.ui

import androidx.annotation.UiThread
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.space.core_ui.base.BaseFragment
import com.space.partners.R
import com.space.partners.databinding.FragmentPartnersContainerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartnersFragment :
    BaseFragment<FragmentPartnersContainerBinding>(R.layout.fragment_partners_container),
    OnMapReadyCallback
{

    companion object {
        fun newInstance() = PartnersFragment()
    }


    private val viewModel: PartnersViewModel by viewModels()

    override fun init() {
        super.init()
    }

    override fun initView() {
        super.initView()
        binding.titleToolbar.text = "협력업체"
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    override fun initListener() {
        super.initListener()
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        val uiSettings = naverMap.uiSettings
        uiSettings.isZoomControlEnabled = false
        uiSettings.isCompassEnabled = false
        uiSettings.isScaleBarEnabled = false

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.6486885, 127.0642073))
        naverMap.moveCamera(cameraUpdate)


    }


}