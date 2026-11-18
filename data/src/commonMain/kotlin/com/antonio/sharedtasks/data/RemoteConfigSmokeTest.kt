package com.antonio.sharedtasks.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.get
import dev.gitlive.firebase.remoteconfig.remoteConfig

suspend fun readWelcomeMessage(): String {
    Firebase.remoteConfig.fetchAndActivate()
    return Firebase.remoteConfig["welcome_message"]
}