package ui.sample.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.fragment_mock.*
import kotlinx.android.synthetic.main.layout_profile.*
import ui.sample.R
import ui.sample.data.model.Mock
import ui.sample.ui.detail.DetailActivity
import ui.sample.utils.AnimationUtils
import ui.sample.utils.ImageUtils
import ui.sample.utils.ViewUtils

/**
 * Created by Shashank on 20/10/2017.
 */
class MockFragment : Fragment() {

    companion object {

        const val EXTRA_MOCK = "mock"

        fun newInstance(mock: Mock): Fragment {
            val fragment = MockFragment()
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_MOCK, mock)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var mock: Mock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mock = arguments.getParcelable(EXTRA_MOCK)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_mock, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(mock)
        view?.setOnClickListener({
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOCK, mock)
            intent.putExtra(DetailActivity.EXTRA_BACKGROUND_HEIGHT, imageview_background.height)

            val pairs = ArrayList<android.util.Pair<View, String>>()

            val decor = activity.window.decorView
            val navBar = decor.findViewById<View>(android.R.id.navigationBarBackground)
            navBar?.let {
                pairs.add(android.util.Pair(navBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME))
            }
            pairs.add(android.util.Pair(cardview, getString(R.string.transition_card)))
            pairs.add(android.util.Pair(textview_title, getString(R.string.transition_title)))
            pairs.add(android.util.Pair(imageview_profile, getString(R.string.transition_author_image)))
            pairs.add(android.util.Pair(textview_name, getString(R.string.transition_author_name)))
            pairs.add(android.util.Pair(textview_description, getString(R.string.transition_description)))
            pairs.add(android.util.Pair(textview_views, getString(R.string.transition_views)))
            pairs.add(android.util.Pair(textview_comments, getString(R.string.transition_comments)))
            pairs.add(android.util.Pair(textview_likes, getString(R.string.transition_likes)))

            val options = AnimationUtils.makeSceneTransitionAnimation(activity, pairs)
            startActivity(intent, options.toBundle())
        })
    }

    private fun bind(mock: Mock) {
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
    }

}