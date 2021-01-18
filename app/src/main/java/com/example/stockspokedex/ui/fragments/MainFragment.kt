package com.example.stockspokedex.ui.fragments

import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.MainViewModel
import com.example.stockspokedex.ui.viewstates.MainViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, MainViewState>() {

    private lateinit var viewModel: MainViewModel

    override fun updateUI(state: MainViewState) {

    }

    override fun attachClickListeners() {

    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_main

    override fun getViewModel(): MainViewModel = viewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}