package fastcampus.part5.domain.usecase

import fastcampus.part5.domain.model.AccountInfo
import fastcampus.part5.domain.repository.AccountRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {

    fun getAccountInfo() : StateFlow<AccountInfo?>{
        return accountRepository.getAccountInfo()
    }

    suspend fun signIn(accountInfo: AccountInfo) {
        accountRepository.signIn(accountInfo)
    }

    suspend fun  signOut() {
        accountRepository.signOut()
    }
}