package ui.sample.ui.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ui.sample.R
import ui.sample.UiSampleApplication
import ui.sample.data.model.Mock
import ui.sample.data.network.ApiHelper
import ui.sample.ui.custom.ZoomOutSlideTransformer
import ui.sample.utils.ImageUtils

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var apiHelper: ApiHelper

    private lateinit var mainPagerAdapter: MainPagerAdapter
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()

        val application = UiSampleApplication.get(this)
        apiHelper = application.getApiHelper()

        presenter = MainPresenter(this, apiHelper)
        presenter.loadLocalMockData(this)
    }

    private fun setupViews() {
        textview_slash.alpha = 0f
        textview_current.alpha = 0f
        textview_total.alpha = 0f

        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewpager.overScrollMode = View.OVER_SCROLL_NEVER
        viewpager.adapter = mainPagerAdapter
        viewpager.offscreenPageLimit = 3
        viewpager.clipToPadding = false
        val verticalPadding = resources.getDimension(R.dimen.viewpager_vertical_padding).toInt()
        val horizontalPadding = resources.getDimension(R.dimen.viewpager_horizontal_padding).toInt()
        viewpager.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        viewpager.pageMargin = resources.getDimension(R.dimen.margin).toInt()
        viewpager.setPageTransformer(true, ZoomOutSlideTransformer())

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                setBackground(position, mainPagerAdapter.getMockItem(position))
                textview_current.text = (position + 1).toString()
            }
        })
    }

    private fun setBackground(position: Int, mock: Mock) {
        val toFade: ImageView
        val toShow: ImageView
        if (position % 2 == 0) {
            toFade = imageview_background_1
            toShow = imageview_background_0
        } else {
            toFade = imageview_background_0
            toShow = imageview_background_1
        }
        toShow.setImageResource(ImageUtils.getImageResId(mock.imageId))

        toFade.animate().alpha(0f).setInterpolator(AccelerateInterpolator()).setDuration(600).start()
        toShow.animate().alpha(1f).setInterpolator(DecelerateInterpolator()).setDuration(600).start()
    }

    override fun setProgressIndicator(active: Boolean) {
        if (active) {
            progressbar.visibility = View.VISIBLE
        } else {
            progressbar.visibility = View.GONE
        }
    }

    override fun showMockData(mockList: List<Mock>) {
        mainPagerAdapter.setMockList(mockList)
        viewpager.currentItem = 0
        textview_current.text = (viewpager.currentItem + 1).toString()
        textview_total.text = mockList.size.toString()

        textview_slash.animate().alpha(1f).setDuration(200).start()
        textview_current.animate().alpha(1f).setDuration(200).start()
        textview_total.animate().alpha(1f).setDuration(200).start()
        setBackground(0, mainPagerAdapter.getMockItem(0))
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(resId: Int) {
        Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

}
