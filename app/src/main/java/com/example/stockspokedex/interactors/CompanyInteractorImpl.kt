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

    override suspend fun deleteCompany(company: CompanyEntity): CompanyEntity? {
        db.deleteCompany(company)
        company.isActive = false
        return company
    }

    override suspend fun deleteCompanies(companyIDs: List<String>) {
        db.deleteCompanies(companyIDs)
    }

    override suspend fun updateCompany(company: CompanyEntity): CompanyEntity? {
        return try {
            // todo compare to local to decide whether to go firestore or not
            companiesCollection.document(company.companyID).update(company.toHashMap()).await()
            db.updateCompany(company)
            company
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getCompany(companyID: String): CompanyEntity = db.getCompany(companyID)

    override suspend fun getAllCompaniesAsync(): LiveData<List<CompanyEntity>> =
        db.getAllCompaniesAsync()

    override fun getCompanyByTickerFromSync(ticker: String): CompanyEntity? =
        db.getCompanyByTicker(ticker)

    override fun getAllCompaniesSync(): List<CompanyEntity> = db.getAllCompaniesSync()

}