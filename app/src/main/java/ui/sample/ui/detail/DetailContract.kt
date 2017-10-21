package ui.sample.ui.detail

import ui.sample.data.model.Mock
import ui.sample.ui.BaseContract

/**
 * Created by Shashank on 21/10/2017.
 */
interface DetailContract {

    interface View : BaseContract.View {
        fun showMock(mock: Mock)
    }

    interface Presenter : BaseContract.Presenter {
        fun loadMock(mock: Mock)
    }
}