package ui.sample

import android.app.Activity
import android.app.Application
import com.google.gson.GsonBuilder
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ui.sample.data.network.ApiConfig
import ui.sample.data.network.ApiHelper
import ui.sample.data.network.ApiHelperImpl
import ui.sample.data.network.MockyApi
import java.util.concurrent.TimeUnit

/**
 * Created by Shashank on 20/10/2017.
 */
class UiSampleApplication : Application() {

    companion object {
        fun get(activity: Activity): UiSampleApplication {
            return activity.applicationContext as UiSampleApplication
        }
    }

    private lateinit var apiHelper: ApiHelper

    override fun onCreate() {
        super.onCreate()
        createDependencies()
    }

    private fun createDependencies() {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        val okHttpClient = okHttpClientBuilder
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .followSslRedirects(true)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .connectionPool(ConnectionPool(30, 120, TimeUnit.SECONDS))
                .build()

        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

        apiHelper = ApiHelperImpl(retrofit.create<MockyApi>(MockyApi::class.java))
    }

    fun getApiHelper(): ApiHelper {
        return apiHelper
    }
}