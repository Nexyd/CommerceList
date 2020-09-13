package dani.kotlin.interactors

import dani.kotlin.data.repository.LocationRepository

class LocationInteractor(private val repository: LocationRepository) {
    fun getUserLocation() = repository.getUserLocation()
}