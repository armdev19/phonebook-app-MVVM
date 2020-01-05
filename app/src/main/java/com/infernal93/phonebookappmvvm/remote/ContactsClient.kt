package com.infernal93.phonebookappmvvm.remote

import androidx.lifecycle.MutableLiveData
import com.infernal93.phonebookappmvvm.entity.Contacts
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by Armen Mkhitaryan on 03.01.2020.
 */

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ContactsClient {

    companion object {
        const val KEY = "5e0fc647b68f0802dd3e5f8b"
        const val BASE_URL = "https://phonebookapp-683c.restdb.io/rest/"
    }

    var mutableLiveData = MutableLiveData<ArrayList<Contacts>>()

    fun loadContacts(): MutableLiveData<ArrayList<Contacts>> {
        // x-apikey interceptor for restdb API
        fun createOkHttpClient(): OkHttpClient? {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original = chain.request()
                    val originalHttpUrl = original.url
                    val url = originalHttpUrl.newBuilder()

                        //.addQueryParameter("apikey", KEY)


                        .build()
                    val requestBuilder = original.newBuilder()
                        .url(url)
                        .header("apikey", KEY)
                    val request = requestBuilder.build()
                    return chain.proceed(request)
                }
            })
            // logging interceptor
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            return httpClient.build()
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()

        val api: ContactsApi = retrofit.create(ContactsApi::class.java)
        api.fetchAllContacts().enqueue(object : Callback<List<Contacts>>{
            override fun onFailure(call: Call<List<Contacts>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Contacts>>, response: Response<List<Contacts>>) {
                mutableLiveData.postValue(response.body() as ArrayList<Contacts>)
            }
        })
        return mutableLiveData
    }
}