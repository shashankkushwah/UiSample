package ui.sample.ui.main

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ui.sample.data.network.ApiHelper

/**
 * Created by Shashank on 20/10/2017.
 */
class MainPresenter(var view: MainContract.View?, private val apiHelper: ApiHelper) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun loadMockData() {
        view?.setProgressIndicator(true)
        compositeDisposable.add(apiHelper.getMockList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe({ comicResponse ->
            view?.setProgressIndicator(false)
            view?.showMockData(comicResponse.data.results)
        }, { t: Throwable ->
            var message = "Something went wrong!"
            t.message?.let {
                message = t.message!!
            }
            view?.setProgressIndicator(false)
            view?.showMessage(message)
        }))
    }

    override fun loadLocalMockData(context: Context) {
        view?.setProgressIndicator(true)
        compositeDisposable.add(apiHelper.getLocalMockList(context).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mockyResponse ->
                    view?.setProgressIndicator(false)
                    view?.showMockData(mockyResponse.data.results)
                }, { t: Throwable ->
                    var message = "Something went wrong!"
                    t.message?.let {
                        message = t.message!!
                    }
                    view?.setProgressIndicator(false)
                    view?.showMessage(message)
                }))
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }

}