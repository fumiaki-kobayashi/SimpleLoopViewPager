# SimpleLoopViewPager

* version 1.0.0

ループするように見えるViewPagerを作成しました。
ViewPagerとAdapterにループの処理を分ける事で、処理を完結に。

## 使用方法

```
<jp.co.ikafumi.loopview.SimpleLoopViewPager
    android:id="@+id/loop_view_pager"
    android:layout_width="0dp"
    android:layout_height="0dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

```
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // SimpleLoopAdapterを継承した任意のAdapterを設定
        findViewById(R.id.loop_view_pager).adapter = LoopAdapter(supportFragmentManager)
    }
}
```

```
class LoopAdapter(fm : FragmentManager) : SimpleLoopAdapter(fm) {

    override fun getPageItem(position: Int): Fragment {
        return Fragment()
    }

    override fun getPageCount(): Int {
        return PageSize
    }
}
```
