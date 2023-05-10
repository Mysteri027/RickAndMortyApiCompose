package com.igor.composestudy.di

import com.igor.composestudy.data.mapper.CharacterResponseMapper
import com.igor.composestudy.data.network.RickAndMortyApi
import com.igor.composestudy.data.repository.NetworkRepositoryImpl
import com.igor.composestudy.domain.repository.NetworkRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://rickandmortyapi.com/"
val dataModule = module {

    factory<NetworkRepository> {
        NetworkRepositoryImpl(mapper = get(), rickAndMortyApi = get())
    }

    factory {
        CharacterResponseMapper()
    }

    factory {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .baseUrl(BASE_URL)
            .build()
    }

    factory {
        OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()
    }

    factory {
        provideRickAndMortyApi(retrofit = get())
    }
}


fun provideRickAndMortyApi(retrofit: Retrofit): RickAndMortyApi =
    retrofit.create(RickAndMortyApi::class.java)
