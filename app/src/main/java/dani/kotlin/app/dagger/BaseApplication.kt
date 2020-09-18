package dani.kotlin.app.dagger

import android.app.Application
import dani.kotlin.app.dagger.component.AppComponent
import dani.kotlin.app.dagger.component.DaggerAppComponent

open class BaseApplication : Application() {

    val appComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}