package dani.kotlin.interactors

import dani.kotlin.data.repository.LocationRepository
import javax.inject.Inject

class LocationInteractor @Inject constructor(private val repository: LocationRepository) {
    fun getUserLocation() = repository.getUserLocation()
}