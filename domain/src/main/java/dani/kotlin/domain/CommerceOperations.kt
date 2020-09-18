package dani.kotlin.domain

import dani.kotlin.domain.comparator.LocationComparator
import dani.kotlin.domain.entities.CommerceInfo
import dani.kotlin.domain.listener.CommerceOperationsListener
import javax.inject.Inject

class CommerceOperations @Inject constructor() : CommerceOperationsListener {

    override fun sortCommercesByProximity(commerces: List<CommerceInfo>,
        latitude: Double, longitude: Double): List<CommerceInfo>
        = commerces.sortedWith(LocationComparator(latitude, longitude))

    override fun filterCommerces(fullList: List<CommerceInfo>,
        filters: ArrayList<String>): List<CommerceInfo> =

        if (filters.isNotEmpty()) {
            val filteredList = ArrayList<CommerceInfo>()
            for (filter: String in filters)
                filteredList.addAll(fullList.filter
                { it.category == filter })

            filteredList
        } else
            fullList
}