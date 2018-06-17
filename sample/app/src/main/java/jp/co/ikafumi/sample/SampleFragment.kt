package jp.co.ikafumi.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SampleFragment : Fragment() {

    companion object {
        const val KEY_PAGE_POSITION = "KEY_PAGE_POSITION"

        fun getInstance(position: Int): SampleFragment {
            val bundle = Bundle()
            bundle.putInt(KEY_PAGE_POSITION, position)
            val fragment = SampleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val position = arguments.getInt(KEY_PAGE_POSITION)
        val view = inflater?.inflate(R.layout.fragment_page, container, false)
        if (view != null) {
            view.findViewById<TextView>(R.id.page_text).text = "$position"
        }
        return view
    }
}