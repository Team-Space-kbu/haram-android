package com.space.partners.ui

import androidx.annotation.UiThread
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.space.core_ui.base.BaseFragment
import com.space.partners.R
import com.space.partners.databinding.FragmentPartnersBinding
import com.space.partners.ui.databinding.adapter.PartnersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartnersFragment :
    BaseFragment<FragmentPartnersBinding>(R.layout.fragment_partners),
    OnMapReadyCallback
{

    companion object {
        fun newInstance() = PartnersFragment()
    }

    private val viewModel: PartnersViewModel by viewModels()


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
        viewModel.partnersInfo.observe(viewLifecycleOwner){
            binding.recyclerview.adapter = PartnersAdapter(it)
        }
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        val uiSettings = naverMap.uiSettings
        uiSettings.isZoomControlEnabled = false
        uiSettings.isCompassEnabled = false
        uiSettings.isScaleBarEnabled = false

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.6486885, 127.0642073))
        naverMap.moveCamera(cameraUpdate)

        viewModel.partnersInfo.observe(viewLifecycleOwner) {
            it?.let {
                it.forEach { partners->
                    Marker().apply {
                        width = Marker.SIZE_AUTO
                        height = Marker.SIZE_AUTO
                        position = LatLng(
                            partners.x_coordinate!!.toDouble(),
                            partners.y_coordinate!!.toDouble()
                        )
                        map = naverMap
                    }
                }
            }
        }
    }

}