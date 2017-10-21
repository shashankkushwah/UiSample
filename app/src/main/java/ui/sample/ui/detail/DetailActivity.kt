package ui.sample.ui.detail

import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.Transition
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_profile.*
import ui.sample.R
import ui.sample.data.model.Comment
import ui.sample.data.model.Mock
import ui.sample.ui.custom.DividerItemDecoration
import ui.sample.utils.AnimationUtils
import ui.sample.utils.ImageUtils
import ui.sample.utils.ViewUtils

class DetailActivity : AppCompatActivity(), DetailContract.View {

    companion object {
        const val EXTRA_MOCK = "mock"
        const val EXTRA_BACKGROUND_HEIGHT = "background_height"
    }

    private lateinit var adapter: CommentsAdapter
    private lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ActivityCompat.postponeEnterTransition(this)
        setupViews()
        presenter = DetailPresenter(this)

        val mock = intent.getParcelableExtra<Mock>(EXTRA_MOCK)
        presenter.loadMock(mock)
    }

    private fun setupViews() {
        val backgroundLayoutParams = imageview_background.layoutParams
        backgroundLayoutParams.height = intent.getIntExtra(EXTRA_BACKGROUND_HEIGHT, resources.getDimension(R.dimen
                .detail_image_height).toInt())

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.addItemDecoration(DividerItemDecoration(this))
        adapter = CommentsAdapter(ArrayList<Comment>(0))
        recyclerview.adapter = adapter

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finishAfterTransition()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMock(mock: Mock) {
        imageview_background.setImageResource(ImageUtils.getImageResId(mock.imageId))
        textview_title.text = mock.title
        imageview_profile.setImageResource(ImageUtils.getImageResId(mock.author.imageId))
        textview_name.text = mock.author.name
        textview_description.text = mock.description
        textview_views.text = mock.view.toString()
        textview_comments.text = mock.comments.size.toString()
        textview_likes.text = mock.likes.toString()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            ViewUtils.setTextViewDrawableColor(textview_views, R.color.icons)
            ViewUtils.setTextViewDrawableColor(textview_comments, R.color.icons)
            ViewUtils.setTextViewDrawableColor(textview_likes, R.color.icons)
        }

        adapter.setItems(mock.comments)

        setupEnterAnimation()
        setupReturnAnimation()
        imageview_background.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        imageview_background.viewTreeObserver.removeOnPreDrawListener(this)

                        ActivityCompat.startPostponedEnterTransition(this@DetailActivity)
                        return true
                    }
                })

    }

    private fun setupEnterAnimation() {
        val enterTransition = window.sharedElementEnterTransition
        enterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
                if (toolbar.visibility == View.INVISIBLE) {
                    AnimationUtils.prepareFadeInAnimation(200, toolbar).start()
                }
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
            }
        })
    }

    private fun setupReturnAnimation() {
        val returnTransition = window.sharedElementReturnTransition
        returnTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition?) {
            }

            override fun onTransitionResume(transition: Transition?) {
            }

            override fun onTransitionPause(transition: Transition?) {
            }

            override fun onTransitionCancel(transition: Transition?) {
            }

            override fun onTransitionStart(transition: Transition?) {
                if (toolbar.visibility == View.VISIBLE) {
                    AnimationUtils.prepareFadeOutAnimation(200, toolbar, recyclerview).start()
                }
            }
        })
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
