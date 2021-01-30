package com.example.stockspokedex.interactors

import com.example.stockspokedex.models.FirebaseAuthInteractor
import com.example.stockspokedex.utils.AppUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthInteractorImpl @Inject constructor() : FirebaseAuthInteractor {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun authWithGoogle(idToken: String): FirebaseUser? {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            result.user
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun getLoggedInUser(): FirebaseUser? = auth.currentUser
}