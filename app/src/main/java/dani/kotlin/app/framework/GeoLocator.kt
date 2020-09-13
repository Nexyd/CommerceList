package dani.kotlin.app.framework

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dani.kotlin.app.CommerceViewModel
import dani.kotlin.data.listener.LocationListener

class GeoLocator(
    private val context: Context
) : LocationListener {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun getUserLocation() {
        fusedLocationClient = LocationServices
            .getFusedLocationProviderClient(context)

        if (ActivityCompat.checkSelfPermission(context,
            android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationClient.lastLocation.addOnSuccessListener(
                context as Activity
            ) { location ->
                if (location != null) {
                    CommerceViewModel.userLocation = LatLng(
                        location.latitude,
                        location.longitude)
                }
            }
        }
    }
}