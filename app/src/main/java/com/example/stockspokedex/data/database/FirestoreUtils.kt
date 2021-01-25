package com.example.stockspokedex.data.database

import com.example.stockspokedex.utils.UserUtils
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirestoreUtils @Inject constructor(
    private val firestoreDB: FirebaseFirestore
) {

    fun getUsersCollection(): CollectionReference = firestoreDB.collection("users")

    private fun getUserDocument(): DocumentReference =
        firestoreDB.collection("users").document(UserUtils.currentUser.uid)

    fun getChecklistsCollection(): CollectionReference =
        getUserDocument().collection("checklists")

    fun getCompaniesCollection(): CollectionReference =
        getUserDocument().collection("companies")

    fun getStocksCollection(): CollectionReference =
        getUserDocument().collection("stocks")

    fun getFilesCollection(): CollectionReference = getUserDocument().collection("files")
}