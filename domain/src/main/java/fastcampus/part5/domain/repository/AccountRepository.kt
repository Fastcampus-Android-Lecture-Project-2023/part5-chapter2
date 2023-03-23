package fastcampus.part5.domain.repository

import fastcampus.part5.domain.model.AccountInfo
import kotlinx.coroutines.flow.StateFlow

interface AccountRepository {
    fun getAccountInfo() : StateFlow<AccountInfo?>

    suspend fun signInGoogle(accountInfo: AccountInfo)

    suspend fun signOutGoogle()
}