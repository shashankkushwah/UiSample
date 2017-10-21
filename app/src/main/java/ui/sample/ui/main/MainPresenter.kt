package ui.sample.ui.main

import android.content.Context
import ui.sample.data.model.Mock
import ui.sample.data.network.ApiHelper

/**
 * Created by Shashank on 20/10/2017.
 */
class MainPresenter(var view: MainContract.View?, private val apiHelper: ApiHelper) : MainContract.Presenter {

    override fun loadMockData() {
        view?.setProgressIndicator(true)
        apiHelper.getMockList(object : ApiHelper.Callback<List<Mock>> {
            override fun onSuccess(data: List<Mock>) {
                view?.setProgressIndicator(false)
                view?.showMockData(data)
            }

            override fun onFailed(message: String) {
                view?.setProgressIndicator(false)
                view?.showMessage(message)
            }
        })
    }

    override fun loadLocalMockData(context: Context) {
        view?.setProgressIndicator(true)
        apiHelper.getLocalMockList(context, object : ApiHelper.Callback<List<Mock>> {
            override fun onSuccess(data: List<Mock>) {
                view?.setProgressIndicator(false)
                view?.showMockData(data)
            }

            override fun onFailed(message: String) {
                view?.setProgressIndicator(false)
                view?.showMessage(message)
            }
        })
    }

    override fun onDetach() {
        view = null
    }

}