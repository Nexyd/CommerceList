package dani.kotlin.app.ui.presenter

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import dani.kotlin.app.CommerceViewModel
import dani.kotlin.app.ui.FilterDialog
import dani.kotlin.domain.entities.CommerceInfo
import dani.kotlin.interactors.CommerceInteractor
import dani.kotlin.interactors.LocationInteractor
import dani.kotlin.interactors.PermissionInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(
    private val permission: PermissionInteractor,
    private val commerce: CommerceInteractor,
    private val location: LocationInteractor
) {

    fun onCreate() = GlobalScope.launch(Dispatchers.Main) {
        withContext(Dispatchers.IO) { permission.askMapPermission() }
        withContext(Dispatchers.IO) { commerce.loadCommerces() }
        withContext(Dispatchers.IO) { location.getUserLocation() }
    }

    fun showDialog(manager: FragmentManager, filters: ArrayList<String>) {
        val dialog = FilterDialog()
        val bundle = Bundle()

        if (filters.isNotEmpty()) {
            bundle.putString("shopping", getFilter("SHOPPING", filters))
            bundle.putString("food",     getFilter("FOOD",     filters))
            bundle.putString("beauty",   getFilter("BEAUTY",   filters))
            bundle.putString("leisure",  getFilter("LEISURE",  filters))
        }

        dialog.arguments = bundle
        dialog.show(manager, "Filter")
    }

    fun sortByProximity(commerces: List<CommerceInfo>) {
        val sortedCommerces = commerce.sortByProximity(commerces,
            CommerceViewModel.userLocation.latitude,
            CommerceViewModel.userLocation.longitude)

        CommerceViewModel.commerces.postValue(sortedCommerces)
    }

    fun filterCommerces(filters: ArrayList<String>) {
        val filteredList = commerce.filterCommerces(
            CommerceViewModel.fullList, filters)

        CommerceViewModel.commerces.postValue(filteredList)
    }

    private fun getFilter(key: String, filters: ArrayList<String>): String? {
        var result: String? = null
        if (filters.contains(key))
            result = filters.filter { it == key }[0]

        return result
    }
}