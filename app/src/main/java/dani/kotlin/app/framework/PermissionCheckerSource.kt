package dani.kotlin.app.framework

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import dani.kotlin.data.listener.PermissionListener
import javax.inject.Inject

class PermissionCheckerSource @Inject constructor(private val context: Context) : PermissionListener {
    override fun askMapPermission() {
        if (ActivityCompat.checkSelfPermission(context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(context as Activity,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }
}