package com.space.biblemon.ui.partners

import androidx.annotation.UiThread
import androidx.fragment.app.viewModels
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentPartnersBinding
import com.space.data.res.partners.PartnersReq
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PartnersFragment :
    BaseFragment<FragmentPartnersBinding>(R.layout.fragment_partners),
    OnMapReadyCallback {

    companion object {
        fun newInstance() = PartnersFragment()
    }

    private val viewModel: PartnersViewModel by viewModels()


    override fun init() {
        super.init()
        this.toolbarTitle = "협력업체"
    }

    override fun initView() {
        super.initView()
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    override fun initListener() {
        super.initListener()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }


    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        val uiSettings = naverMap.uiSettings
        uiSettings.isZoomControlEnabled = false
        uiSettings.isCompassEnabled = false
        uiSettings.isScaleBarEnabled = false

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.6486885, 127.0642073))
        naverMap.moveCamera(cameraUpdate)

        viewModel.partnersData.observe(viewLifecycleOwner) {
            it?.let {
                for (partners in it) {
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