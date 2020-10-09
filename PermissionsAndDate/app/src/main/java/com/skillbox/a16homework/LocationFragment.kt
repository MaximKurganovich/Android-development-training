package com.skillbox.a16homework

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_layout.*
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class LocationFragment : Fragment(R.layout.fragment_layout) {

    private var locationDataSet: List<LocationData> = listOf()
    private var dataLocationAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialization()
        dataLocationAdapter?.items = locationDataSet
    }

    //    В методе проверяется дано ли разрешение. Если да, то одна кнопка скрывается,
//    вторая появляется, а также меняется текст в textView. Если нет, то на первую кнопку вешется листнер,
//    который запрашивает разрешение
    @RequiresApi(Build.VERSION_CODES.O)
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
    @RequiresApi(Build.VERSION_CODES.O)
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
    @RequiresApi(Build.VERSION_CODES.O)
    private fun changeOfFragmentInterface() {
        if (locationDataSet.isEmpty()) {
            textViewDeclaration.text = fragmentView.resources.getString(R.string.noDisplayLocations)
        } else {
            textViewDeclaration.visibility = View.GONE
            recycleView.visibility = View.VISIBLE
        }
        initAdapter()
        buttonConsent.visibility = View.GONE
        buttonGetLocation.visibility = View.VISIBLE
        buttonGetLocation.setOnClickListener { locationRequest() }
    }

//    Метод запрашивает местоположение пользователя и добавляет в список, который содержит все ранее загруженные локации
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun locationRequest() {
        if (permissionApproval()) {
            val zonedDateTime = ZonedDateTime.now()
            val location = LocationServices.getFusedLocationProviderClient(requireContext()).lastLocation
            location.addOnSuccessListener {
                it?.let {
                    locationDataSet = listOf(
                        LocationData.Location(
                            id = locationDataSet.size,
                            time = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy").withZone(ZoneId.systemDefault()).format(zonedDateTime),
                            lnd = it.latitude,
                            lat = it.longitude,
                            image = addImage()
                        )
                    ) + locationDataSet
                    dataLocationAdapter?.items = locationDataSet
                    println("$locationDataSet")
                }
            }.addOnCanceledListener {
                Toast.makeText(
                    requireContext(),
                    fragmentView.resources.getText(R.string.locationError),
                    Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener{Toast.makeText(
                requireContext(),
                fragmentView.resources.getText(R.string.locationError),
                Toast.LENGTH_SHORT
            ).show()}
        }
    }

//    Метод выбирает случайную картинку и загружает на место аватара при запросе локации
    private fun addImage(): String {
        val linksToImages = listOf(
            "http://rabstol.ru/wallpapers/e5233f69f41a82bdef185ef2f8f55d1f/3802_5.jpg",
            "https://www.oboinastol.com/engine/downloadimg.php?s=1366x768&i=2015-03/oboinastol.com_1426665337.jpg&n=636",
            "https://wallbox.ru/wallpapers/main/201432/2d4cad690fb7386.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/0/0a/%D0%92%D0%B8%D0%B4_%D1%81%D0%B2%D0%B5%D1%80%D1%85%D1%83_-_panoramio_%282%29.jpg",
            "https://img4.goodfon.ru/original/2560x1440/8/b7/iaponiia-nagoya-derevnia-doma-polia-plantatsii-derevia-zelen.jpg",
            "https://cdn.trinixy.ru/pics4/20100716/new_york_city_from_above_16.jpg",
            "https://i.archi.ru/i/650/191935.png"
        )
        return linksToImages.random()
    }

    private fun initAdapter () {
        dataLocationAdapter = ListAdapter()
        with(recycleView) {
            adapter = dataLocationAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataLocationAdapter = null
    }

    companion object {
        const val LOCATION_PERMISSION = 1
    }
}