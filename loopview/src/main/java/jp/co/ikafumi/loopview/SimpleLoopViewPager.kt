package jp.co.ikafumi.loopview

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet

class SimpleLoopViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    /**
     * ループ調整用Listener
     */
    private val loopListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            if (state == SCROLL_STATE_IDLE) {
                if (adapter == null) return
                if (adapter.count < 3) return
                // 端の場合、調整する
                if (currentItem == 0) {
                    setCurrentItem(adapter.count - 2, false)
                } else if (currentItem == adapter.count - 1) {
                    setCurrentItem(1, false)
                }
            }
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
        }
    }

    init {
        // 継承元の関数を使用し、ループ調整用Listenerを設定
        super.addOnPageChangeListener(loopListener)
    }

    override fun setAdapter(adapter: PagerAdapter?) {
        super.setAdapter(adapter)
        // 初期位置を調整
        setCurrentItem(1, false)
    }

    override fun addOnPageChangeListener(listener: OnPageChangeListener?) {
        super.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                listener?.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                listener?.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                // 調整した値を返す
                listener?.onPageSelected(adjustPosition(position))
            }
        })
    }

    override fun clearOnPageChangeListeners() {
        super.clearOnPageChangeListeners()
        // ループ調整用Listenerを再設定
        super.addOnPageChangeListener(loopListener)
    }

    /**
     * 位置を調整する
     */
    private fun adjustPosition(position: Int): Int {
        var adjustPosition = position
        if (position == 0) adjustPosition = adapter.count - 2
        if (position > adapter.count - 2) adjustPosition = 1
        return adjustPosition - 1
    }
}