package ui.sample.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import ui.sample.R

/**
 * Created by Shashank on 21/10/2017.
 */
class DividerItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    private val divider = ContextCompat.getDrawable(context, R.drawable.divider)
    private val startMargin = context.resources.getDimension(R.dimen.margin_start_recyclerview_divider).toInt()
    private val endMargin = context.resources.getDimension(R.dimen.margin_end_recyclerview_divider).toInt()

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - endMargin

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight

            divider.setBounds(startMargin, top, right, bottom)
            divider.draw(c)
        }
    }
}