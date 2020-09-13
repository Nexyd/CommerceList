package dani.kotlin.interactors

import dani.kotlin.data.repository.PermissionRepository

class PermissionInteractor(private val repository: PermissionRepository) {
    fun askMapPermission() = repository.askMapPermission()
}