package com.antonio.sharedtasks.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

suspend fun signInAnonymously(): String {
    return Firebase.auth.signInAnonymously().user?.uid ?: throw Exception("Failed to sign in anonymously")
}