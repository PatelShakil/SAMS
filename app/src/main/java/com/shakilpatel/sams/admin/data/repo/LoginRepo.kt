package com.shakilpatel.sams.admin.data.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginRepo {

    val auth = FirebaseAuth.getInstance()
    suspend fun doLogin(email: String, pass: String): Boolean {
        return auth.signInWithEmailAndPassword(email, pass).isSuccessful
    }

}