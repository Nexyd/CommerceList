package dani.kotlin.app.framework.listener

import dani.kotlin.domain.entities.CommerceInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("commerces/public")
    fun loadCommerces(): Observable<ArrayList<CommerceInfo>>
}