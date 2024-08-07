package com.geeks.hw_6_2.di

import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module{
    includes(networkModule, ViewModelModule, RepositoryModule)
}