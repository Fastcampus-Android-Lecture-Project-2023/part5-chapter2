package fastcampus.part5.chapter2.ui.main

import android.app.Activity
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import fastcampus.part5.chapter2.viewmodel.MainViewModel
import fastcampus.part5.domain.model.AccountInfo

@Composable
fun MyPageScreen(viewModel: MainViewModel, googleSignInClient: GoogleSignInClient) {
    val accountInfo by viewModel.accountInfo.collectAsState()
    val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    val context = LocalContext.current
    val startForResult = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val indent = result.data
            if (indent != null) {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(indent)
                handleSignInResult(context, task, viewModel, firebaseAuth)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        if (accountInfo != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "로그인 유저 : ${accountInfo?.name}",
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )

                Button(onClick = {
                    viewModel.signOutGoogle()
                    firebaseAuth.signOut()
                }) {
                    Text(text = "로그아웃")
                }
            }

        } else {
            Button(onClick = {
                startForResult.launch(googleSignInClient.signInIntent)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "로그인")
            }
        }

    }

}

private fun handleSignInResult(context: Context, accountTask: Task<GoogleSignInAccount>, viewModel: MainViewModel, firebaseAuth: FirebaseAuth) {
    try {
        val account = accountTask.result ?: return
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    viewModel.signInGoogle(AccountInfo(account.idToken.orEmpty(), account.displayName.orEmpty(), AccountInfo.Type.GOOGLE))
                } else {
                    viewModel.signOutGoogle()
                    firebaseAuth.signOut()
                }
            }

    } catch (e: Exception) {
        e.printStackTrace()
    }
}