package ui.sample.ui

import android.support.annotation.StringRes


/**
 * Created by Shashank on 20/10/2017.
 */
interface BaseContract {

    interface View {
        fun showMessage(message: String)

        fun showMessage(@StringRes resId: Int)
    }

    interface Presenter {
        fun onDetach()
    }

}