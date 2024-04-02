package com.space.partners.ui.detail.databinding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.annotation.UiThread
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.space.partners.databinding.ItemNaverMapBinding

internal class MapAdapter(
    private val fragmentManager: FragmentManager,
    private val xCoordinate: Double,
    private val yCoordinate: Double,
) : RecyclerView.Adapter<NaverMapViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaverMapViewHolder =
        NaverMapViewHolder.newInstance(parent,xCoordinate, yCoordinate)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: NaverMapViewHolder, position: Int) {
        holder.itemBind(fragmentManager)
    }

}

internal class NaverMapViewHolder(
    private val binding: ItemNaverMapBinding,
    private val xCoordinate: Double,
    private val yCoordinate: Double,
) : RecyclerView.ViewHolder(binding.root), OnMapReadyCallback {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            xCoordinate: Double,
            yCoordinate: Double,
        ): NaverMapViewHolder {
            val binding =
                ItemNaverMapBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return NaverMapViewHolder(binding, xCoordinate, yCoordinate)
        }
    }

    fun itemBind(
        fragmentManager: FragmentManager
    ) {
        val mapFragment =fragmentManager.findFragmentById(com.space.partners.R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fragmentManager.beginTransaction()
                    .add(com.space.partners.R.id.map, it)
                    .commit()
            }
        mapFragment.getMapAsync(this)
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        val uiSettings = naverMap.uiSettings
        uiSettings.isZoomControlEnabled = false
        uiSettings.isCompassEnabled = false
        uiSettings.isScaleBarEnabled = false

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(xCoordinate, yCoordinate))
        naverMap.moveCamera(cameraUpdate)
        Marker().apply {
            width = Marker.SIZE_AUTO
            height = Marker.SIZE_AUTO
            position = LatLng(
                xCoordinate,
                yCoordinate
            )
            map = naverMap
        }
    }
}

