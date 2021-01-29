package com.example.stockspokedex.data.database

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirestoreUtils @Inject constructor(
    private val firestoreDB: FirebaseFirestore
) {

    fun getUsersCollection(): CollectionReference = firestoreDB.collection("users")

    fun getUserDocument(uid: String): DocumentReference =
        firestoreDB.collection("users").document(uid)

    fun getChecklistsCollection(uid: String): CollectionReference =
        getUserDocument(uid).collection("checklists")

    fun getCompaniesCollection(uid: String): CollectionReference =
        getUserDocument(uid).collection("companies")

    fun getStocksCollection(uid: String): CollectionReference =
        getUserDocument(uid).collection("stocks")

    fun getFilesCollection(uid: String): CollectionReference =
        getUserDocument(uid).collection("files")
}