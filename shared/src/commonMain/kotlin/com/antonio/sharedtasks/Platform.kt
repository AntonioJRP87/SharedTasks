package com.antonio.sharedtasks

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform