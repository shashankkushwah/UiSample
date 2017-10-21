package ui.sample.utils

import android.graphics.Outline
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.TextView


/**
 * Created by Shashank on 21/10/2017.
 */
class ViewUtils {
    companion object {
        val CIRCULAR_OUTLINE: ViewOutlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setOval(view.paddingLeft,
                        view.paddingTop,
                        view.width - view.paddingRight,
                        view.height - view.paddingBottom)
            }
        }

        fun setTextViewDrawableColor(textView: TextView, color: Int) {
            textView.compoundDrawables
                    .filterNotNull()
                    .forEach {
                        it.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(textView.context, color), PorterDuff
                                .Mode.SRC_IN)
                    }
        }
    }
}