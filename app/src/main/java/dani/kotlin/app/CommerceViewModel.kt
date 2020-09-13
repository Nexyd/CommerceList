package dani.kotlin.app

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dani.kotlin.domain.entities.CommerceInfo
import kotlin.collections.ArrayList

class CommerceViewModel: ViewModel()  {

    companion object {
        val fullList = ArrayList<CommerceInfo>()
        var userLocation = LatLng(0.0, 0.0)
        var commerces = MutableLiveData<List<CommerceInfo>>()
        var detailCommerce = MutableLiveData<CommerceInfo>()
    }

    fun init(context: Context) {
        commerces.observe(context as LifecycleOwner, {
            if (fullList.isEmpty())
                fullList.addAll(it)
        })
    }
}