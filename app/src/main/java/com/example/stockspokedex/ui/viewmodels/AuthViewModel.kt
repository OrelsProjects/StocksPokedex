package com.example.stockspokedex.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import com.example.stockspokedex.data.entities.db.UserEntity
import com.example.stockspokedex.models.*
import com.example.stockspokedex.ui.base.BaseViewModel
import com.example.stockspokedex.ui.viewstates.AuthViewState
import com.example.stockspokedex.utils.AppUtils
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
    private val viewState: AuthViewState,
    private val userInteractor: UserInteractor,
    private val companyInteractor: CompanyInteractor,
    private val stockInteractor: StockInteractor,
    private val checklistInteractor: ChecklistInteractor,
    private val firebaseAuthInteractor: FirebaseAuthInteractor
) : BaseViewModel<AuthViewState>() {

    override fun getViewState(): AuthViewState = viewState

    fun handleIsUserLoggedIn() {
        val user = firebaseAuthInteractor.getLoggedInUser()
        if (user != null) {
            viewState.connectedUser = UserEntity.firebaseUserToEntity(user)
            viewState.isLoginSuccessful = true
            updateUI()
        }
    }

    fun handleGoogleLogin(idToken: String) {
        networkScope.launch {
            val firebaseUser = firebaseAuthInteractor.authWithGoogle(idToken) ?: return@launch
            // todo couldnt login error if firebaseuser null
            // todo Check if user connected instead of cached perhaps?
            if (isUserCached(firebaseUser.uid)) {  // Same user that was previously logged in has logged in again.
                viewState.connectedUser = userInteractor.getCurrentUser()
                viewState.isLoginSuccessful = true
            } else { // New user has logged in.
                val user =
                    if (!userInteractor.isUserRegistered(firebaseUser.uid)) {
                        val userEntity = UserEntity(
                            uid = firebaseUser.uid,
                            email = firebaseUser.email ?: ""
                        )
                        userInteractor.createUnregisteredUser(userEntity)
                        userEntity
                    } else {
                        userInteractor.getUserFromFirestoreDB(firebaseUser.uid)
                    }
                if (user == null) {
                    viewState.isLoginFailed = true
                    AppUtils.reportCrash(Throwable("Login failed, userEntity is null in AuthViewModel"))
                } else {
                    clearCache()
                    populateCache(user.uid)
                    userInteractor.insertUser(user)
                    viewState.isLoginSuccessful = true
                    viewState.connectedUser = user
                }
            }
            updateUI()
        }
    }

    private fun isUserCached(uid: String?): Boolean {
        val cachedUser = userInteractor.getCurrentUser()
        if (cachedUser?.uid == uid) return true
        return false
    }

    private fun clearCache() {
        userInteractor.clear()
        companyInteractor.clear()
        stockInteractor.clear()
        checklistInteractor.clear()
    }

    private suspend fun populateCache(uid: String) {
        val companies = companyInteractor.getCompaniesFromFirestore(uid)
        val checklists = checklistInteractor.getChecklistsFromFirestore(uid)
        val stocks = stockInteractor.getStocksFromFirestore(uid)
        companyInteractor.insertCompanies(companies)
        checklistInteractor.insertChecklists(checklists)
        stockInteractor.cacheStocks(stocks)
    }
}