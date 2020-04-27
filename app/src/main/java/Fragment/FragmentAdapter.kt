package Fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var fragmentList:MutableList<Fragment>?= mutableListOf()
    override fun getItem(position: Int): Fragment {
         return fragmentList?.get(position)!!
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }


    override fun getCount(): Int {
         return fragmentList!!.size
    }
}