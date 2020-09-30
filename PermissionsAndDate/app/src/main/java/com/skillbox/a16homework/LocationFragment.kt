package com.skillbox.a16homework

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_layout.*


class LocationFragment : Fragment(R.layout.fragment_layout) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialization()
    }

    private fun initialization() {
        if (permissionApproval()) {

        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            textViewDeclaration.text = fragmentView.resources.getString(R.string.noDisplayLocations)
            buttonConsent.visibility = View.GONE
            buttonGetLocation.visibility = View.VISIBLE
        }
    }

    private fun permissionApproval(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val  LOCATION_PERMISSION = 1
    }
}