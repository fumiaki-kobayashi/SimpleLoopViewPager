package loopview

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

abstract class SimpleLoopAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {

    abstract fun getPageItem(position: Int) : Fragment
    abstract fun getPageCount() : Int

    override fun getItem(position: Int): Fragment {
        // ダミーを除いた値を求める
        return getPageItem(adjustPosition(position))
    }

    override fun getCount(): Int {
        // ダミー数分を加算
        return getPageCount() + 2
    }

    /**
     * 位置を調整する
     */
    private fun adjustPosition(position: Int): Int {
        var adjustPosition = position
        if (position == 0) adjustPosition = count - 2
        if (position > count - 2) adjustPosition = 1
        return adjustPosition - 1
    }
}
