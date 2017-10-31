package ui.sample.data.network

import android.content.Context
import io.reactivex.Observable
import io.reactivex.Single
import ui.sample.data.model.MockyResponse

/**
 * Created by Shashank on 20/10/2017.
 */
interface ApiHelper {

    fun getMockList(): Observable<MockyResponse>

    fun getLocalMockList(context: Context): Single<MockyResponse>

}