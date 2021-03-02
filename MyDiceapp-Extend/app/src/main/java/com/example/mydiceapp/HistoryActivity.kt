package com.example.mydiceapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity: AppCompatActivity() {

    private val mHistory = mutableListOf<Pair<Int, Int>>()
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

    }
    fun onClickBack(view: View) { finish() }
}