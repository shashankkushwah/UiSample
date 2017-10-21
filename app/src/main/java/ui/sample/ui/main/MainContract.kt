package ui.sample.ui.main

import android.content.Context
import ui.sample.data.model.Mock
import ui.sample.ui.BaseContract

/**
 * Created by Shashank on 20/10/2017.
 */
interface MainContract {

    interface View : BaseContract.View {
        fun setProgressIndicator(active: Boolean)
        fun showMockData(mockList: List<Mock>)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadMockData()
        fun loadLocalMockData(context: Context)
    }

}