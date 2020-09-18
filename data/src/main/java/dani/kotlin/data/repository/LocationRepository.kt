package dani.kotlin.data.repository

import dani.kotlin.data.listener.LocationListener
import javax.inject.Inject

class LocationRepository @Inject constructor(private val listener: LocationListener) {
    fun getUserLocation() = listener.getUserLocation()
}