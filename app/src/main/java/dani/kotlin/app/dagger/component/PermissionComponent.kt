package dani.kotlin.app.dagger.component

import dagger.Component
import dani.kotlin.app.dagger.module.PermissionModule
import dani.kotlin.app.framework.PermissionCheckerSource
import javax.inject.Singleton

@Component(modules = [PermissionModule::class])
interface PermissionComponent {
    fun inject(checkerSource: PermissionCheckerSource)
}