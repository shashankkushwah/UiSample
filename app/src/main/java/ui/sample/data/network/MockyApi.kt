package ui.sample.data.network

import io.reactivex.Observable
import retrofit2.http.GET
import ui.sample.data.model.MockyResponse

/**
 * Created by Shashank on 20/10/2017.
 */
interface MockyApi {

    @GET("v2/59ea780c110000f0022677fe")
    fun getMockData(): Observable<MockyResponse>

}