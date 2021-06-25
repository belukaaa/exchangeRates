package com.leavingston.exchangerates.DI

import androidx.room.Dao
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leavingston.exchangerates.ViewModel.DBViewModel
import com.leavingston.exchangerates.ViewModel.EurRatesViewModel
import com.leavingston.exchangerates.ViewModel.GelRatesViewModule
import com.leavingston.exchangerates.ViewModel.exchangeRatesViewModule
import com.leavingston.exchangerates.networking.BASE_URL
import com.leavingston.exchangerates.networking.RemoteApiService
import com.leavingston.exchangerates.repository.ExchangeRatesRepo
import com.leavingston.exchangerates.repository.roomRepository
import com.leavingston.exchangerates.room.DAO
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    //რეტროფიტის ინსტანსის შექმნა , შემდეგში რო დაგჭირდება და რო გამოიყენო , ახალ ახალი რეტროფიტის ობიექტების შექმნის ნაცვლად
    var retrofitInstance : Retrofit? = null



    // აპი ინტერფეის კლასის შექმნა
    fun provideUseApi(retrofit: Retrofit) : RemoteApiService{
        return  retrofit.create((RemoteApiService::class.java))
    }
    // ჯსონის ქონვერთერის შექმნა
    fun provideGson() : Gson {
        return  GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }
    //ჰტტპ კლიენტის შექმნა
    fun provideHttpClient() : OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        return  okHttpClient.build()
    }
    //რეტროფიტის შექმნა და მისი დაკავშირება საჭირო კომპონენტებთან
    fun provideRetrofit(factory : Gson , client: OkHttpClient) : Retrofit {
        //აქ მოწმდება ზემოთ ხსენებული რეტროფინის ინსტანსი თუ არ არის შექმნილი , იქმნება ახალი და მახსოვრდება კოინში
        return if (retrofitInstance == null){
            retrofitInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(factory))
                .client(client)
                .build()
            retrofitInstance as Retrofit
        }
        // შემდეგი გამოძახებისას რადგან უკვე შექმნილია ინსტანსი პირდაპირ რეტროფიტის ობიექტს დააბრუნებს
        else retrofitInstance as Retrofit

    }
    // სინგლი ნიშნავს რომ მთლიან პროექტში ეს ობიექტი ერთხელ შეიქმნება და ამ ერთხელ შექმნილ ობექტთან გექნება შეხება
    single { provideUseApi(get()) }
    single { ExchangeRatesRepo(get()) }
    //ვიუ მოდელის შესაქმნელი key-word -ი
    viewModel { exchangeRatesViewModule(get()) }
//    viewModel { GelRatesViewModule(get ()) }
    viewModel { EurRatesViewModel(get()) }
    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get() , get()) }

}