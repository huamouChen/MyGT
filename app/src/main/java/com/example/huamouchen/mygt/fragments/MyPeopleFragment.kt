package com.example.huamouchen.mygt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.huamouchen.mygt.R

/**
 * Created by huamouchen on 2017/12/13.
 */
class MyPeopleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_my_people, null)
    }
}