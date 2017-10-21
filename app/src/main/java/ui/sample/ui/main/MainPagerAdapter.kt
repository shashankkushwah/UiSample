package ui.sample.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import ui.sample.data.model.Mock

/**
 * Created by Shashank on 20/10/2017.
 */
class MainPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val registeredFragments = SparseArray<Fragment>()
    private var mockList: List<Mock> = ArrayList(0)

    override fun getItem(position: Int): Fragment {
        return MockFragment.newInstance(mockList[position])
    }

    override fun getCount(): Int {
        return mockList.size
    }

    fun setMockList(mockList: List<Mock>) {
        this.mockList = mockList
        notifyDataSetChanged()
    }

    fun getMockItem(position: Int): Mock {
        return mockList[position]
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments.get(position)
    }

}