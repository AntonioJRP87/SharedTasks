package com.antonio.sharedtasks.data

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.crashlytics.crashlytics

fun reportTestError() {
    Firebase.crashlytics.log("Reporting test error from shared module")
    Firebase.crashlytics.recordException(
        Exception("Test exception from shared module")
    )
}