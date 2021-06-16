package com.example.myapplication.di

import com.example.myapplication.data.GetChartsRepoImpl
import com.example.myapplication.data.services.GetChartsDataApi
import com.example.myapplication.data.source.remote.GetChartsRemoteDataSource
import com.example.myapplication.data.source.remote.GetChartsRemoteDataSourceImpl
import com.example.myapplication.domain.model.mapper.ChartResponseMapper
import com.example.myapplication.domain.repository.GetChartsRepo
import com.example.myapplication.domain.usecase.GetChartsUseCase
import com.example.myapplication.presentation.main.ChartsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    fun getChartsService(retrofit: Retrofit): GetChartsDataApi {
        return retrofit.create(GetChartsDataApi::class.java)
    }
    single { getChartsService(get()) }
}
val networkModule = module {
    val connectTimeout: Long = 40// 20s
    val readTimeout: Long = 40 // 20s

    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)


        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }


    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        val baseUrl ="https://api.blockchain.info/"
        provideRetrofit(get(), baseUrl)
    }
}

val viewModelModule = module {
    // Specific viewModel pattern to tell Koin how to build SearchViewModel
    viewModel {
        ChartsViewModel(getChartsUseCase = get())
    }
}

val repoModule = module {
    factory<GetChartsRepo> {
        return@factory GetChartsRepoImpl(get(), get())
    }
    factory {
        return@factory GetChartsRepoImpl(get(), get())
    }

    factory{
        return@factory ChartResponseMapper()
    }

    factory<GetChartsRemoteDataSource> {
        return@factory GetChartsRemoteDataSourceImpl(get())
    }

    factory { GetChartsUseCase(get()) }
}