package com.skillbox.a16homework

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_layout.*


class LocationFragment : Fragment(R.layout.fragment_layout) {

    val locationDataset = mutableListOf<Location>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialization()
    }

//    В методе проверяется дано ли разрешение. Если да, то одна кнопка скрывается,
//    вторая появляется, а также меняется текст в textView. Если нет, то на первую кнопку вешется листнер,
//    который запрашивает разрешение
    private fun initialization() {
        if (permissionApproval()) {
            changeOfFragmentInterface()
        } else {
            buttonConsent.setOnClickListener {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION
                )
            }
        }
    }

//    Метод проверяющий какие разрешения дал пользователь. Если пользователь дал разрешение,
//    то меняется интрефейс экрана. Если нет, то появляется тост.
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            changeOfFragmentInterface()
        } else {
            Toast.makeText(
                requireContext(),
                fragmentView.resources.getString(R.string.warningAboutTheImpossibilityOfObtainingALocation),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

//    Метод проверяет дано ли разрешение
    private fun permissionApproval(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

//    Метод смены интерфейса, если дано разрешение. На новую кнопку вешается листнер,
//    который запрашивает текущее местоположение пользователя
    private fun changeOfFragmentInterface() {
        textViewDeclaration.text = fragmentView.resources.getString(R.string.noDisplayLocations)
        buttonConsent.visibility = View.GONE
        buttonGetLocation.visibility = View.VISIBLE
        buttonGetLocation.setOnClickListener {}
    }

    private fun locationRequest () {
        if (permissionApproval()) {
            LocationServices.getFusedLocationProviderClient(requireContext()).lastLocation}

    }

    companion object {
        const val LOCATION_PERMISSION = 1
    }
}