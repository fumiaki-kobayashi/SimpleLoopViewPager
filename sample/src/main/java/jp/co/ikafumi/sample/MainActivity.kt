package jp.co.ikafumi.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.view.ViewPager
import jp.co.ikafumi.loopview.SimpleLoopViewPager

class MainActivity : AppCompatActivity() {

    companion object {
        /** 自動切り替え時間 */
        const val TIME_AUTO_SWIPE_INTERVAL: Long = 2000
    }

   /** LoopViewPager */
    private var mLoopViewPager: SimpleLoopViewPager? = null
    /** ローテーション管理用 */
    private var mRotationHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setLoopViewPager()
    }

    private fun setLoopViewPager() {
        mLoopViewPager = findViewById(R.id.loop_view_pager)
        mLoopViewPager?.adapter = LoopAdapter(supportFragmentManager)
        mLoopViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                when (state) {
                    ViewPager.SCROLL_STATE_IDLE -> {
                        if (mLoopViewPager != null) startRotation(mLoopViewPager!!.currentItem)
                    }
                    else -> {
                        stopRotation()
                    }
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })
        // ローテーション開始
        if (mLoopViewPager != null) startRotation(mLoopViewPager!!.currentItem)
    }

    /**
     * ローテーションを開始する
     */
    private fun startRotation(position: Int) {
        mRotationHandler = Handler(Looper.getMainLooper())
        mRotationHandler?.postDelayed({
            // 次のページに切り替える
            mLoopViewPager?.setCurrentItem(position + 1, true)
        }, TIME_AUTO_SWIPE_INTERVAL)
    }

    /**
     * ローテーションを停止する
     */
    private fun stopRotation() {
        mRotationHandler?.removeCallbacksAndMessages(null)
        mRotationHandler = null
    }
}
