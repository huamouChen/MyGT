package com.example.huamouchen.mygt.fragments.performance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.huamouchen.mygt.R
import com.example.huamouchen.mygt.fragments.BaseFragment

/**
 * Created by huamouchen on 08/01/2018.
 */
class ShelfMainFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater?.inflate(R.layout.fragment_shelf_main, null)
        return contentView
    }

    private fun initView(contentView: View) {

    }
}