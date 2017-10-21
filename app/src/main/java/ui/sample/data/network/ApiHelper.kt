package ui.sample.data.network

import android.content.Context
import ui.sample.data.model.Mock

/**
 * Created by Shashank on 20/10/2017.
 */
interface ApiHelper {

    interface Callback<T> {

        fun onSuccess(data: T)

        fun onFailed(message: String)
    }

    fun getMockList(callback: Callback<List<Mock>>)

    fun getLocalMockList(context:Context, callback: Callback<List<Mock>>)

}