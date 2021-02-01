package com.example.stockspokedex.ui.fragments

import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.LoadingViewModel
import com.example.stockspokedex.ui.viewstates.LoadingViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoadingFragment : BaseFragment<LoadingViewModel, LoadingViewState>() {

    override fun setCurrentFragment() {}

    override fun initViewModel() {}

    override fun getViewModel(): LoadingViewModel = ViewModelProvider(this).get(LoadingViewModel::class.java)

    override fun updateUI(state: LoadingViewState) {}

    override fun attachClickListeners() {}

    override fun getLayoutResourceFile(): Int = R.layout.fragment_loading
}