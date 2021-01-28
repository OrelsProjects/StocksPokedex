package com.example.stockspokedex.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.stockspokedex.R

object AppUtils {

    fun addFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

    @Suppress("DEPRECATION")
    fun getScreenDimensions(activity: Activity): IntArray {
        val displayMetrics = DisplayMetrics()
        val dimensions = IntArray(2)
        activity.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels
        val screenWidth = displayMetrics.widthPixels
        dimensions[0] = screenHeight
        dimensions[1] = screenWidth
        return dimensions
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun setNotificationAndNavigationBarsColors(
        activity: Activity,
        colorAttribute: Int = R.attr.backgroundColor
    ) {
        val window = activity.window
        val color = getColorFromAttributes(activity, colorAttribute)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
        window.navigationBarColor = color
    }

    fun getColorFromAttributes(context: Context, attr: Int): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }

    fun getPriceTextDollars(price: String) = "$price$"

    fun reportCrash(throwable: Throwable) {
//        FirebaseCrashlytics.getInstance().recordException(throwable)
    }

    fun reportCrashAndThrow(throwable: Throwable) {
//        FirebaseCrashlytics.getInstance().recordException(throwable)
//        throw throwable
    }

}