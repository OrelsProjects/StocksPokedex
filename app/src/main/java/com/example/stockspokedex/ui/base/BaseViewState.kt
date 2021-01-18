package com.example.stockspokedex.ui.base

import android.os.Bundle
import kotlin.reflect.KClass

open class BaseViewState(
    var isLoadingAttached: Boolean = false,
    private var isLoading: Boolean = false,
    var newActivity: KClass<*>? = null,
    var clearActivityOnIntent: Boolean = false,
    var intentExtras: Bundle = Bundle()
) {
    fun setIsLoading(value:Boolean){
        isLoading = value
    }

    fun getIsLoading(): Boolean = isLoading

}