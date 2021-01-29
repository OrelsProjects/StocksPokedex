package com.example.stockspokedex.ui.fragments

import android.animation.LayoutTransition
import android.content.Intent
import android.view.View
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.example.stockspokedex.R
import com.example.stockspokedex.activities.MainActivity
import com.example.stockspokedex.data.entities.db.ChecklistEntity
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.data.entities.db.StockEntity
import com.example.stockspokedex.ui.base.BaseFragment
import com.example.stockspokedex.ui.viewmodels.StockInfoViewModel
import com.example.stockspokedex.ui.viewstates.StockInfoViewState
import com.example.stockspokedex.utils.AppIntents
import com.example.stockspokedex.utils.AppIntents.EXTRA_STOCK_INFO_BUNDLE
import com.example.stockspokedex.utils.AppUtils.hideKeyboard
import com.example.stockspokedex.utils.General
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.add_file_bullish_thesis.*
import kotlinx.android.synthetic.main.add_file_dcf.*
import kotlinx.android.synthetic.main.add_file_price_target.*
import kotlinx.android.synthetic.main.check_box_cc.*
import kotlinx.android.synthetic.main.check_box_ceo.*
import kotlinx.android.synthetic.main.check_box_investors_presentation.*
import kotlinx.android.synthetic.main.check_box_news.*
import kotlinx.android.synthetic.main.check_box_ten_k.*
import kotlinx.android.synthetic.main.check_box_ten_q.*
import kotlinx.android.synthetic.main.fragment_stock_info.*
import java.util.*
import javax.inject.Inject

class StockInfoFragment @Inject constructor(
) : BaseFragment<StockInfoViewModel, StockInfoViewState>(),
    View.OnClickListener {

    private var isSavePressed = false

    private lateinit var viewModel: StockInfoViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(StockInfoViewModel::class.java)
    }

    // todo if resumed dont reset fields!

    override fun getViewModel(): StockInfoViewModel = viewModel

    override fun updateUI(state: StockInfoViewState) {
        state.state.observe(this, {
            when (it) {
                StockInfoViewState.State.New -> {
                    setNewStock()
                }
                StockInfoViewState.State.Edit -> {
                    setEditStock(state.companyToEdit, state.stockToEdit, state.checklistToEdit)
                }
                else -> {

                }
            }
        })
        if (state.isEditStockDone) {
            isSavePressed = false
            navigateMainActivity()
        }
        if (state.isStockSaveDone) {
            if (state.isCompanyExistsInDB) {
                setErrorToField(companyNameInput, "The company already exists in your list.")
            } else {
                isSavePressed = false
                navigateMainActivity()
            }
        }
        state.reset()
    }

    override fun attachClickListeners() {
        addStockBackground.setOnClickListener(this)
        checklistLayout.setOnClickListener(this)
        addBullishThesisFileImage.setOnClickListener(this)
        addPriceTargetFileImage.setOnClickListener(this)
        addDCFFileImage.setOnClickListener(this)
        saveStockButton.setOnClickListener(this)
        cancelStockButton.setOnClickListener(this)
        tenKRadio.setOnClickListener(this)
        ceoRadio.setOnClickListener(this)
        ccRadio.setOnClickListener(this)
        tenQRadio.setOnClickListener(this)
        investorsPresentationRadio.setOnClickListener(this)
        newsRadio.setOnClickListener(this)
    }

    override fun getLayoutResourceFile(): Int = R.layout.fragment_stock_info

    override fun onViewCreated() {
        setAnimationsToFileLayouts()
        val bundle = arguments?.getBundle(EXTRA_STOCK_INFO_BUNDLE)
        val isEdit = bundle?.getBoolean(AppIntents.EXTRA_IS_EDIT_STOCK) ?: false
        var company: CompanyEntity? = null
        var stock: StockEntity? = null
        var checklist: ChecklistEntity? = null
        if (isEdit) {
            val companyJson = bundle?.getString(AppIntents.EXTRA_COMPANY_TO_EDIT)
            val stockJson = bundle?.getString(AppIntents.EXTRA_STOCK_TO_EDIT)
            val checklistJson = bundle?.getString(AppIntents.EXTRA_CHECKLIST_TO_EDIT)
            if (companyJson == null || stockJson == null || checklistJson == null) {
                setNewStock()
                return
            }
            company = CompanyEntity.fromJson(companyJson)
            stock = StockEntity.fromJson(stockJson)
            checklist = ChecklistEntity.fromJson(checklistJson)
        }
        viewModel.setState(isEdit, company, stock, checklist)

    }


    private fun onBackgroundClick() {
        clearFocus()
        hideKeyboard()
    }


    private fun clearFocus() {
        companyTickerInput.clearFocus()
        companyNameInput.clearFocus()
    }

    private fun cancel() {
        navigateMainActivity()
    }


    private fun saveChanges() {
        if (isSavePressed) return
        if (checkAndSetIsErrorFields()) return
        isSavePressed = true
        val company = getCompanyFromField()
        val stock = getStockFromField()
        val checklist = getChecklistFromField()
        viewModel.handleSaveStock(company, checklist, stock)
    }

    private fun setEditStock(
        companyEntity: CompanyEntity?,
        stockEntity: StockEntity?,
        checklistEntity: ChecklistEntity?
    ) {
        setFields(companyEntity, stockEntity, checklistEntity)
    }

    private fun setNewStock() {
        resetFields()
    }

    private fun resetFields() {
        companyNameEdit.text?.clear()
        companyTickerEdit.text?.clear()
        priceTargetEdit.text?.clear()
        tenKRadio.isChecked = false
        ceoRadio.isChecked = false
        ccRadio.isChecked = false
        tenQRadio.isChecked = false
        investorsPresentationRadio.isChecked = false
        newsRadio.isChecked = false
    }

    private fun setFields(
        companyEntity: CompanyEntity?,
        stockEntity: StockEntity?,
        checklistEntity: ChecklistEntity?
    ) {
        companyNameEdit.setText(companyEntity?.companyName)
        companyTickerEdit.setText(companyEntity?.companyTicker)
        priceTargetEdit.setText(stockEntity?.priceTarget)
        tenKRadio.isChecked = checklistEntity?.tenK ?: false
        ceoRadio.isChecked = checklistEntity?.ceo ?: false
        ccRadio.isChecked = checklistEntity?.conferenceCall ?: false
        tenQRadio.isChecked = checklistEntity?.tenQ ?: false
        investorsPresentationRadio.isChecked = checklistEntity?.investorsPresentation ?: false
        newsRadio.isChecked = checklistEntity?.news ?: false
    }

    private fun getCompanyFromField(): CompanyEntity =
        CompanyEntity(
            companyName = companyNameEdit.text.toString(),
            companyTicker = companyTickerEdit.text.toString(),
            companyID = Date().time.toString()
        )

    private fun getStockFromField(): StockEntity = StockEntity(
        priceTarget = priceTargetEdit.text.toString()
    )

    private fun getChecklistFromField(): ChecklistEntity = ChecklistEntity(
        tenK = tenKRadio.isChecked,
        ceo = ceoRadio.isChecked,
        conferenceCall = ccRadio.isChecked,
        tenQ = tenQRadio.isChecked,
        investorsPresentation = investorsPresentationRadio.isChecked,
        news = newsRadio.isChecked
    )

    private fun checkAndSetIsErrorFields(): Boolean {
        var bool = false
        if (companyNameEdit.text.toString() == "") {
            bool = true
            setErrorToField(companyNameInput, "Company's name must be filled")
        }
        if (companyTickerEdit.text.toString() == "") {
            bool = true
            setErrorToField(companyTickerInput, "Company's ticker must be filled")
        }
        if (priceTargetEdit.text.toString() == "") {
            if (!bool) { // Meaning this is the only field that is not filled.
                hideKeyboard()
                stockInfoScrollView.fullScroll(View.FOCUS_DOWN)
                priceTargetEdit.requestFocus()
            }
            bool = true
            setErrorToField(priceTargetInput, "A price target must be set")
        }
        return bool
    }

    private fun setErrorToField(
        inputLayout: TextInputLayout,
        errorMessage: String = "Error"
    ) {
        inputLayout.error = null
        inputLayout.isErrorEnabled = false
        inputLayout.error = errorMessage
    }


    private fun showChecklist() {
        if (checklistFlexbox.getChildAt(0).visibility == View.VISIBLE) {
            rotateChecklistToggleClose()
            removeItemsFromChecklist()
        } else {
            rotateChecklistOpen()
            showItemsInChecklist()
        }
    }

    private fun rotateChecklistToggleClose() {
        checklistToggleImage.animate().rotation(90f).setDuration(200).start()
    }

    private fun rotateChecklistOpen() {
        checklistToggleImage.animate().rotation(270f).setDuration(200).start()
    }

    private fun removeItemsFromChecklist() {
        checklistFlexbox.children.forEach {
            it.visibility = View.GONE
        }
    }

    private fun showItemsInChecklist() {
        checklistFlexbox.children.forEach {
            it.visibility = View.VISIBLE
        }
    }

    private fun setAnimationsToFileLayouts() {
        bullishThesisLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        dcfLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        priceTargetLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun navigateMainActivity() {
        val mainActivityIntent = Intent(context, MainActivity::class.java)
        mainActivityIntent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context?.startActivity(mainActivityIntent)
        activity?.finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.addStockBackground -> onBackgroundClick()
            R.id.checklistLayout -> showChecklist()
            R.id.saveStockButton -> saveChanges()
            R.id.cancelStockButton -> cancel()
            R.id.tenKRadio
                    or R.id.ceoRadio
                    or R.id.ccRadio
                    or R.id.tenQRadio
                    or R.id.investorsPresentationRadio
                    or R.id.ceoRadio -> {
                onBackgroundClick()
            }
        }
    }

    override fun setCurrentFragment() {
        General.presentedFragment = General.Fragments.AddStock
    }
}