package fastcampus.part5.data.repository

import fastcampus.part5.data.datasource.PreferenceDatasource
import fastcampus.part5.domain.model.AccountInfo
import fastcampus.part5.domain.repository.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val preferenceDatasource: PreferenceDatasource
) : AccountRepository {
    private val accountInfoFlow = MutableStateFlow(preferenceDatasource.getAccountInfo())

    override fun getAccountInfo(): StateFlow<AccountInfo?> {
        return accountInfoFlow
    }

    override suspend fun signInGoogle(accountInfo: AccountInfo) {
        preferenceDatasource.putAccountInfo(accountInfo)
        accountInfoFlow.emit(accountInfo)
    }

    override suspend fun signOutGoogle() {
        preferenceDatasource.removeAccountInfo()
        accountInfoFlow.emit(null)
    }
}