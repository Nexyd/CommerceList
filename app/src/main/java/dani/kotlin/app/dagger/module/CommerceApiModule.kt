package dani.kotlin.app.dagger.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dani.kotlin.app.framework.CommerceSource
import dani.kotlin.data.listener.CommerceApiListener
import javax.inject.Inject
import javax.inject.Singleton

@Module
abstract class CommerceApiModule {
    @Binds
    abstract fun getCommerceApiListener(source: CommerceSource): CommerceApiListener
}