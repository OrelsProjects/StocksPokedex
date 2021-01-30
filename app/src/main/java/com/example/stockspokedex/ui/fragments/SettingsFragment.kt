package com.example.stockspokedex.ui.fragments

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.activities.AuthActivity
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.SettingsViewModel
import com.example.stockspokedex.ui.viewstates.SettingsViewState
import com.example.stockspokedex.utils.General
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment<SettingsViewModel, SettingsViewState>(),
    View.OnClickListener {

    private lateinit var viewModel: SettingsViewModel

    override fun setCurrentFragment() {
        General.presentedFragment = General.Fragments.Settings
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    override fun getViewModel(): SettingsViewModel = viewModel

    override fun updateUI(state: SettingsViewState) {
        if (state.isLogoutComplete) {
            val intent = Intent(activity, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity?.startActivity(intent)
        }
        state.reset()
    }

    override fun attachClickListeners() {
        settings_sign_out.setOnClickListener(this)
    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_settings

    override fun onClick(view: View) {
        when (view.id) {
            R.id.settings_sign_out -> viewModel.handleSignOut()

        }
    }
}