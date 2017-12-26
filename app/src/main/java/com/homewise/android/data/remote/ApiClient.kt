package com.homewise.android.data.remote

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by @raj on 26/12/17.
 */
class ApiClient (mContext: Context){

    private lateinit var mRetrofit: Retrofit
    private var mApiServiceEndPoints : ApiEndpoints? = null

    init {
        setUpClient()
    }

    private fun setUpClient() {
        val builder = OkHttpClient.Builder()
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(httpLogger)

        mRetrofit = Retrofit.Builder()
                    .baseUrl("https://www.google.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .build()

        mApiServiceEndPoints = getApiService()
    }

    private fun getApiService(): ApiClient.ApiEndpoints {
        if (mApiServiceEndPoints == null){
            mApiServiceEndPoints = mRetrofit.create(ApiEndpoints::class.java)
        }
        return mApiServiceEndPoints!!
    }


    private interface ApiEndpoints {

    }

    companion object {
        private var mInstance : ApiClient? = null

        fun getInstance(mContext: Context) : ApiClient {
            if(mInstance == null){
                mInstance = ApiClient(mContext)
            }
            return mInstance!!
        }
    }

}