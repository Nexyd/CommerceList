package dani.kotlin.interactors

import dani.kotlin.data.repository.PermissionRepository
import javax.inject.Inject

class PermissionInteractor @Inject constructor(private val repository: PermissionRepository) {
    fun askMapPermission() = repository.askMapPermission()
}