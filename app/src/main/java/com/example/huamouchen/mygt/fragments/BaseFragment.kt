package com.example.huamouchen.mygt.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.huamouchen.mygt.R
import com.example.huamouchen.mygt.activitys.MainActivity

/**
 * Created by huamouchen on 2017/12/14.
 */
open class BaseFragment : Fragment() {


    lateinit var fl_content: FrameLayout

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater!!.inflate(R.layout.fragment_base, null)
        initView(contentView)
        return contentView
    }

    private fun initView(view: View) {
        fl_content = view.findViewById(R.id.fl_root)
    }


    // 添加要显示的 主体内容
    fun addContentView(layoutId: Int) {
        val contentView = LayoutInflater.from(context).inflate(layoutId, null)
        val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        fl_content.addView(contentView, layoutParams)
    }

    /*
    * 获取 Main Activity
    * */

    fun getMainActivity() : MainActivity? {
        if (activity is MainActivity) {
            return activity as MainActivity
        }
        return null
    }

    /*
    * 显示 Fragment
    * */
    fun displayFragment(fragment: Fragment) {
        displayFragment(fragment, true)
    }

    fun displayFragment(fragment: Fragment, isAddToStack: Boolean) {
        if (getMainActivity() != null) {
            getMainActivity()!!.displayFragment(fragment, isAddToStack)
        }
    }

    /*
    * 返回上一个Fragment
    * */
    fun popPreviousFragment() {
        if (getMainActivity() != null) {
            getMainActivity()!!.onBackPressed()
        }
    }
}