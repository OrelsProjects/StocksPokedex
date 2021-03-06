package com.example.stockspokedex.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

abstract class BaseFragment<V : BaseViewModel<S>, S : BaseViewState> :
    Fragment() {

    val disposables = CompositeDisposable()

    fun getIsLoading(): BehaviorSubject<Boolean> = isLoading
    fun setIsLoading(value: Boolean) = isLoading.onNext(value)

    protected open fun onBindViewModel() {
        observeState()
    }

    protected open fun onUnbindViewModel() {
        // Empty lifecycle function to be overridden
    }

    open fun onViewCreated() {}

    open fun attachLongClickListeners() {
    }

    abstract fun setCurrentFragment()

    abstract fun initViewModel()

    abstract fun getViewModel(): V

    abstract fun updateUI(state: S)

    abstract fun attachClickListeners()

    abstract fun getLayoutResourceFile(): Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initViewModel()
        setCurrentFragment()
        return inflater.inflate(getLayoutResourceFile(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachClickListeners()
        attachLongClickListeners()
        onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        onBindViewModel()
    }

    override fun onPause() {
        super.onPause()
        onUnbindViewModel()
    }

    @Suppress("UNCHECKED_CAST")
    private fun observeState() {
        getViewModel().getState().observe(this, { state -> updateUI(state as S) })
    }

    companion object {
        private val isLoading: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    }
}