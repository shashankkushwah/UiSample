package ui.sample.data.network

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ui.sample.data.model.Mock
import ui.sample.data.model.MockyResponse

/**
 * Created by Shashank on 20/10/2017.
 */
class ApiHelperImpl(private val api: MockyApi) : ApiHelper {

    override fun getMockList(callback: ApiHelper.Callback<List<Mock>>) {
        api.getMockData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ comicResponse ->
            callback.onSuccess(comicResponse.data.results)
        }, { t: Throwable ->
            var message = "Something went wrong!"
            t.message?.let {
                message = t.message!!
            }
            callback.onFailed(message)
        })
    }

    override fun getLocalMockList(context: Context, callback: ApiHelper.Callback<List<Mock>>) {
        Single.fromCallable {
            val inputStream = context.assets.open("mock.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)

            val gson = Gson()
            val mockResponse = gson.fromJson<MockyResponse>(json, MockyResponse::class.java)

            mockResponse.data.results
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ mockList ->
            callback.onSuccess(mockList)
        }, { t: Throwable ->
            var message = "Something went wrong!"
            t.message?.let {
                message = t.message!!
            }
            callback.onFailed(message)
        })
    }

}