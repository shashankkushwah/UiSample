package ui.sample.data.network

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single
import ui.sample.data.model.MockyResponse

/**
 * Created by Shashank on 20/10/2017.
 */
class ApiHelperImpl(private val api: MockyApi) : ApiHelper {

    override fun getMockList(): Observable<MockyResponse> {
        return api.getMockData()
    }

    override fun getLocalMockList(context: Context): Single<MockyResponse> {
        return Single.fromCallable {
            val inputStream = context.assets.open("mock.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)

            val gson = Gson()
            val mockResponse = gson.fromJson<MockyResponse>(json, MockyResponse::class.java)

            mockResponse
        }
    }
}