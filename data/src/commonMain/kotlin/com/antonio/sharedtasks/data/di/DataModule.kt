package com.antonio.sharedtasks.data.di

import com.antonio.sharedtasks.data.KoinSmokeTest
import org.koin.dsl.module

val dataModule = module {
    single { KoinSmokeTest() }
}
