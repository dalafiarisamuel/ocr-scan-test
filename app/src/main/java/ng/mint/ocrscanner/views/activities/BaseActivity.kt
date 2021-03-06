package ng.mint.ocrscanner.views.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

@Suppress("DEPRECATION")
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_ALL = 1
    }


    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    open fun setLightStatusBar(view: View?) {
        view?.run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                var flags = view.systemUiVisibility
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                view.systemUiVisibility = flags
                this@BaseActivity.window.statusBarColor =
                    view.context.resources.getColor(android.R.color.white)
            }
        }
    }

    open fun hideKeyboard(activity: Activity = this) {
        val imm =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val f = activity.currentFocus
        if (null != f && null != f.windowToken && EditText::class.java.isAssignableFrom(f.javaClass)) {
            imm.hideSoftInputFromWindow(f.windowToken, 0)
        } else activity.window
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }


    open fun requestPermissionsFromDevice() {
        if (!permissionGranted(*permissions)) {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                PERMISSION_ALL
            )
        }

    }

    open fun permissionGranted(vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }
}