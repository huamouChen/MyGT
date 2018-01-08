package com.example.huamouchen.mygt.fragments.my_store

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.huamouchen.mygt.R
import com.example.huamouchen.mygt.fragments.BaseFragment

/**
 * Created by huamouchen on 2017/12/13.
 */
class MyStoreFragment : BaseFragment() {

    lateinit var tv: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater!!.inflate(R.layout.fragment_my_store, null)
        initView(contentView)
        return contentView
    }

    private fun initView(contentView: View) {
        tv = contentView.findViewById(R.id.tv)
        tv.setOnClickListener {
            val fragment = MyStoreDetailFragment()
            displayFragment(fragment)
        }
    }


}