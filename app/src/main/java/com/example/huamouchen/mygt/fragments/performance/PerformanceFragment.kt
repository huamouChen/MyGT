package com.example.huamouchen.mygt.fragments.performance

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.huamouchen.mygt.R
import com.example.huamouchen.mygt.fragments.BaseFragment

/**
 * Created by huamouchen on 2017/12/13.
 */
class PerformanceFragment : BaseFragment() {

    lateinit var v_offtake : View
    lateinit var v_task : View
    lateinit var v_psku : View
    lateinit var v_star : View
    lateinit var v_display : View
    lateinit var v_shelf : View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater?.inflate(R.layout.fragment_performance, null)
        initView(contentView!!)
        return contentView
    }

    private fun initView(contentView: View) {
        v_offtake = contentView.findViewById(R.id.v_offtake)
        v_offtake.setOnClickListener { displayFragment(OfftakeMainFragment()) }

        v_task = contentView.findViewById(R.id.v_task)
        v_task.setOnClickListener { displayFragment(TaskMainFragment()) }

        v_psku = contentView.findViewById(R.id.v_psku)
        v_psku.setOnClickListener { displayFragment(PSKUMainFragment()) }

        v_star = contentView.findViewById(R.id.v_star)
        v_star.setOnClickListener { displayFragment(StarMainFragment()) }

        v_display = contentView.findViewById(R.id.v_display)
        v_display.setOnClickListener { displayFragment(DisplayMainFragment()) }

        v_shelf = contentView.findViewById(R.id.v_shelf)
        v_shelf.setOnClickListener { displayFragment(ShelfMainFragment()) }

    }
}