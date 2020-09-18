package dani.kotlin.app.dagger.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import dani.kotlin.app.framework.PermissionCheckerSource
import dani.kotlin.data.listener.PermissionListener
import javax.inject.Inject

@Module
abstract class PermissionModule {
    @Binds
    abstract fun getPermissionListener(checker: PermissionCheckerSource): PermissionListener
}