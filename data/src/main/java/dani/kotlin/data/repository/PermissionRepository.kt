package dani.kotlin.data.repository

import dani.kotlin.data.listener.PermissionListener

class PermissionRepository(
    private val listener: PermissionListener
) {
    fun askMapPermission() = listener.askMapPermission()
}