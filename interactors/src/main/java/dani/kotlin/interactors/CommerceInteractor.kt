package dani.kotlin.interactors

import dani.kotlin.data.repository.CommerceRepository
import dani.kotlin.domain.entities.CommerceInfo
import javax.inject.Inject

class CommerceInteractor @Inject constructor(private val repository: CommerceRepository) {
    fun loadCommerces() = repository.loadCommerces()
    fun sortByProximity(commerces: List<CommerceInfo>, latitude: Double, longitude: Double):
        List<CommerceInfo> = repository.sortByProximity(commerces, latitude, longitude)

    fun filterCommerces(fullList: List<CommerceInfo>,
        filters: ArrayList<String>): List<CommerceInfo> =
        repository.filterCommerces(fullList, filters)
}