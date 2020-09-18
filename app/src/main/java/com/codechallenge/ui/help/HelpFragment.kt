package com.codechallenge.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codechallenge.R
import kotlinx.android.synthetic.main.fragment_help.*


class HelpFragment : Fragment() {

    private val TAG = javaClass.simpleName


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webview.loadUrl("https://stackoverflow.com/help")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_help, container, false)


    companion object {
        fun newInstance(): HelpFragment {
            val fragment = HelpFragment()
            return fragment
        }
    }
}