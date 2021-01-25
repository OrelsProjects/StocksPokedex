package com.example.stockspokedex.interactors

import androidx.lifecycle.LiveData
import com.example.stockspokedex.data.daos.CompanyDao
import com.example.stockspokedex.data.database.FirestoreUtils
import com.example.stockspokedex.data.entities.db.CompanyEntity
import com.example.stockspokedex.models.CompanyInteractor
import com.example.stockspokedex.utils.AppUtils
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CompanyInteractorImpl @Inject constructor(
    private val db: CompanyDao,
    firestoreUtils: FirestoreUtils
) : CompanyInteractor {

    private val companiesCollection = firestoreUtils.getCompaniesCollection()

    override suspend fun insertCompany(company: CompanyEntity): CompanyEntity? {
        return try {
            val companyDocument = companiesCollection.document()
            company.companyID = companyDocument.id
            companyDocument.set(company.toHashMap()).await()
            db.insertCompany(company)
            company
        } catch (e: Exception) {
            AppUtils.reportCrash(e)
            null
        }
    }

    override fun deleteCompany(company: CompanyEntity) {
        db.deleteCompany(company)
    }

    override fun deleteCompanies(companyIDs: List<String>) {
        db.deleteCompanies(companyIDs)
    }

    override fun updateCompany(company: CompanyEntity) {
        db.updateCompany(company)
    }

    override fun getCompany(companyID: String): CompanyEntity = db.getCompany(companyID)

    override fun getCompanyByTicker(ticker: String): CompanyEntity? = db.getCompanyByTicker(ticker)

    override fun getAllCompaniesAsync(): LiveData<List<CompanyEntity>> = db.getAllCompaniesAsync()

    override fun getAllCompaniesSync(): List<CompanyEntity> = db.getAllCompaniesSync()
}