package com.example.huamouchen.mygt.fragments.my_store

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.huamouchen.mygt.R

/**
 * Created by huamouchen on 2017/12/14.
 */
class MyStoreDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_my_store_detail, null)
        return view
    }
}