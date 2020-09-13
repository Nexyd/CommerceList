package dani.kotlin.domain.listener

import dani.kotlin.domain.entities.CommerceInfo

interface CommerceOperationsListener {
    fun sortCommercesByProximity(commerces: List<CommerceInfo>,
        latitude: Double, longitude: Double): List<CommerceInfo>

    fun filterCommerces(fullList: List<CommerceInfo>,
        filters: ArrayList<String>): List<CommerceInfo>
}