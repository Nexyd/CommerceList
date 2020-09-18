package dani.kotlin.app.dagger.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dani.kotlin.app.dagger.module.CommerceApiModule
import dani.kotlin.app.dagger.module.CommerceOperationsModule
import dani.kotlin.app.dagger.module.LocationModule
import dani.kotlin.app.dagger.module.PermissionModule
import dani.kotlin.app.ui.presenter.MainPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    PermissionModule::class,
    LocationModule::class,
    CommerceOperationsModule::class,
    CommerceApiModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun getMainPresenter(): MainPresenter
}