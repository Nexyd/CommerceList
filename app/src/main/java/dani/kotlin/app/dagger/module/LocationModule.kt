package dani.kotlin.app.dagger.module

import dagger.Binds
import dagger.Module
import dani.kotlin.app.framework.GeoLocatorSource
import dani.kotlin.data.listener.LocationListener

@Module
abstract class LocationModule {
    @Binds
    abstract fun getLocationListener(checker: GeoLocatorSource): LocationListener
}