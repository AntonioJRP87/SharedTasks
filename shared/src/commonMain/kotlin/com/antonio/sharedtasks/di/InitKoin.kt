package com.antonio.sharedtasks.di

import com.antonio.sharedtasks.presentation.di.presentationModule
import com.antonio.sharedtasks.data.di.dataModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(dataModule, presentationModule)
    }
}