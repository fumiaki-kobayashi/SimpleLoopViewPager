package jp.co.ikafumi.sample

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import loopview.SimpleLoopAdapter

class AutoLoopAdapter(fm : FragmentManager) : SimpleLoopAdapter(fm) {

    override fun getPageItem(position: Int): Fragment {
        return SampleFragment.getInstance(position)
    }

    override fun getPageCount(): Int {
        return 3
    }
}
