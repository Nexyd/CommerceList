package dani.kotlin.data.repository

import dani.kotlin.data.listener.CommerceApiListener
import dani.kotlin.domain.entities.CommerceInfo
import dani.kotlin.domain.listener.CommerceOperationsListener

class CommerceRepository(
    private val apiListener: CommerceApiListener,
    private val opsListener: CommerceOperationsListener
) {
    fun loadCommerces() = apiListener.loadCommerces()
    fun sortByProximity(commerces: List<CommerceInfo>,
        latitude: Double, longitude: Double): List<CommerceInfo> =
        opsListener.sortCommercesByProximity(commerces, latitude, longitude)

    fun filterCommerces(fullList: List<CommerceInfo>,
        filters: ArrayList<String>): List<CommerceInfo> =
        opsListener.filterCommerces(fullList, filters)
}