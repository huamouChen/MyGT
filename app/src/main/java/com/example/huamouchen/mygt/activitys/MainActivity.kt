package com.example.huamouchen.mygt.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.huamouchen.mygt.R
import com.example.huamouchen.mygt.widgets.FlowRadioGroup

class MainActivity : BaseActivity() {


    lateinit var rg: FlowRadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRootView(R.layout.activity_main)

        rg = findViewById(R.id.rg_tab)
        rg.check(R.id.rb_performance)
    }
}
