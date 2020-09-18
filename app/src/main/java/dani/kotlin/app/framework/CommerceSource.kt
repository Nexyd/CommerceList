package dani.kotlin.app.framework

import dani.kotlin.app.CommerceViewModel
import dani.kotlin.app.framework.listener.RetrofitAPI
import dani.kotlin.data.listener.CommerceApiListener
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CommerceSource @Inject constructor(): CommerceApiListener {

    override fun loadCommerces() {
        val service = Retrofit.Builder()
            .baseUrl("https://prod.klikin.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitAPI::class.java)

        service.loadCommerces().subscribe({ result ->
            CommerceViewModel.commerces.postValue(result)
        }, { error ->
            error.printStackTrace()
        })
    }
}