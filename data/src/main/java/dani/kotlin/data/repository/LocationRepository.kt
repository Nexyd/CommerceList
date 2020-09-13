package dani.kotlin.data.repository

import dani.kotlin.data.listener.LocationListener

class LocationRepository(private val listener: LocationListener) {
    fun getUserLocation() = listener.getUserLocation()
}