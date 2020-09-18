package dani.kotlin.app.dagger.module

import dagger.Binds
import dagger.Module
import dani.kotlin.data.listener.CommerceApiListener
import dani.kotlin.domain.CommerceOperations
import dani.kotlin.domain.listener.CommerceOperationsListener

@Module
abstract class CommerceOperationsModule {
    @Binds
    abstract fun getCommerceOpsListener(operations: CommerceOperations): CommerceOperationsListener
}