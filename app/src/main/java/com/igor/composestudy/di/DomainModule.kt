package com.igor.composestudy.di

import com.igor.composestudy.domain.usecase.GetCharacterListUseCase
import org.koin.dsl.module


val domainModule = module {

    factory {
        GetCharacterListUseCase(networkRepository = get())
    }
}
