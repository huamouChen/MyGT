package com.example.huamouchen.mygt.fragments.my_store

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.huamouchen.mygt.R
import com.example.huamouchen.mygt.fragments.BaseFragment
import com.example.huamouchen.mygt.fragments.MemoFragment

/**
 * Created by huamouchen on 2017/12/14.
 */
class MyStoreDetailFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_my_store_detail, null)
        initView(view)
        return view
    }

    private fun initView(contentView: View) {
        contentView.findViewById<TextView>(R.id.tv).setOnClickListener {
            displayFragment(MemoFragment())
        }
    }
}