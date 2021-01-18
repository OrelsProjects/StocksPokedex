package com.example.stockspokedex.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

object AppUtils {

    fun initViewModel(activity: Class<ViewModel>, owner: ViewModelStoreOwner): ViewModel = ViewModelProvider(owner).get(
        activity)

}