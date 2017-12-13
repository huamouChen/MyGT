package com.example.huamouchen.mygt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import rx.lang.kotlin.subscribeBy
import rx.lang.kotlin.toObservable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
