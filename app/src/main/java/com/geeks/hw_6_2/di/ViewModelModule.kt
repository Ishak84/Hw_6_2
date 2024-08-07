package com.geeks.hw_6_2.di

import com.geeks.hw_6_2.ui.fragments.cartoon.CharacterViewModel
import com.geeks.hw_6_2.ui.fragments.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        CharacterViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }
}