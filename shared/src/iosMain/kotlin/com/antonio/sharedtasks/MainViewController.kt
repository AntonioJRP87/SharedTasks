package com.antonio.sharedtasks

import androidx.compose.ui.window.ComposeUIViewController
import com.antonio.sharedtasks.data.initializeFirebase
import platform.UIKit.UIViewController

@Suppress("FunctionName")
fun MainViewController(): UIViewController {
    initializeFirebase()
    return  ComposeUIViewController { App() }
}