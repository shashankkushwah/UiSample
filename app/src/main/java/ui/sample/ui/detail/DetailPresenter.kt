package ui.sample.ui.detail

import ui.sample.data.model.Mock

/**
 * Created by Shashank on 21/10/2017.
 */
class DetailPresenter(var view: DetailContract.View?) : DetailContract.Presenter {
    override fun loadMock(mock: Mock) {
        view?.showMock(mock)
    }

    override fun onDetach() {
        view = null
    }
}