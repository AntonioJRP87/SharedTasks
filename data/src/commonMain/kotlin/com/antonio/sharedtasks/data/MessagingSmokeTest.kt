package com.antonio.sharedtasks.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.messaging.messaging

suspend fun readMessagingToken(): String {
    return Firebase.messaging.getToken()
}