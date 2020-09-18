package dani.kotlin.data.repository

import dani.kotlin.data.listener.PermissionListener
import javax.inject.Inject

class PermissionRepository @Inject constructor(
    private val listener: PermissionListener
) {
    fun askMapPermission() = listener.askMapPermission()
}