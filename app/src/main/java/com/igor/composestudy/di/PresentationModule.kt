package com.igor.composestudy.di

import com.igor.composestudy.presentation.mapper.CharacterUiMapper
import com.igor.composestudy.presentation.screens.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MainViewModel(mapper = get(), getCharacterListUseCase = get())
    }

    factory {
        CharacterUiMapper()
    }
}