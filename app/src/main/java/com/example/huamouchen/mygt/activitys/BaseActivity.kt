package com.example.huamouchen.mygt.activitys



import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.huamouchen.mygt.R

/**
 * Created by huamouchen on 2017/12/13.
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context
    var appBar: AppBarLayout? = null
    var fl_main: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        mContext = this
        initView()
    }

    private fun initView() {
        appBar = findViewById(R.id.appBar)
        fl_main = findViewById(R.id.fl_main)
        setToolBar()
    }

    // 设置 tool bar
    private fun setToolBar() {
        val toolBar = LayoutInflater.from(mContext).inflate(R.layout.tool_bar_main, null) as Toolbar
        // 设置 size
        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var height = displayMetrics.heightPixels
        var toolBarHeight =  Math.round(height * 0.06).toInt()
        val layoutParams = Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, toolBarHeight)
        toolBar.layoutParams = layoutParams
        // 返回按钮 设置点击监听
        toolBar.findViewById<TextView>(R.id.tv_left).setOnClickListener { onBackPressed() }
        appBar!!.addView(toolBar)
    }

    // 设置 内容
    fun setRootView(layoutID: Int) {
        val view = LayoutInflater.from(mContext).inflate(layoutID, null)
        var layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
        view.layoutParams = layoutParams
        fl_main!!.addView(view)
    }
}