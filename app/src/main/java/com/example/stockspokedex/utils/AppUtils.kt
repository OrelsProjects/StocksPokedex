package com.example.stockspokedex.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.stockspokedex.utils.AppUtils.hideKeyboard

object AppUtils {

    fun addFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int
    ) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

//    fun hideKeyboard(activity: Activity?) {
//        val imm: InputMethodManager =
//            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        var view = activity.currentFocus
//        if (view == null) {
//            view = View(activity)
//        }
//        imm.hideSoftInputFromWindow(view.windowToken, 0)
//        val idmm =
//            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        idmm.hideSoftInputFromWindow(view.windowToken, 0)
//    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}