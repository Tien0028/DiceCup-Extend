package com.example.mydiceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val TAG: String = "xyz"
    // mapping from 1..6 to drawables, the first index is unused
    private val diceId = intArrayOf(0, R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6)

    private val mRandomGenerator = Random()

    private val mHistory = mutableListOf<Pair<Int, Int>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnRoll.setOnClickListener{ v -> onClickRoll()}
        imgDice1.setOnClickListener { v -> onClickRoll() }
        imgDice2.setOnClickListener { v -> onClickRoll() }
        imgDice3.setOnClickListener { v-> onClickRoll() }
        imgDice4.setOnClickListener { v-> onClickRoll() }
        imgDice5.setOnClickListener { v-> onClickRoll() }
        imgDice6.setOnClickListener { v-> onClickRoll() }
        btnClear.setOnClickListener { v -> onClickClear() }
        Log.d(TAG, "OnCreate")

        if(savedInstanceState !=null){
            Log.d(TAG, "saved state not null")
            val history = savedInstanceState.getSerializable("history") as Array<Pair<Int, Int>>
            history.forEach{ p -> mHistory.add(p)}
            updateHistory()
            if(mHistory.size > 0)
                updateDicesWith(mHistory[mHistory.size-1])
        }

    }

    private fun updateDicesWith(p: Pair<Int, Int>) {
        imgDice1.setImageResource( diceId[p.first] )
        imgDice2.setImageResource( diceId[p.second] )
    }

    private fun onClickClear() {
        Log.d(TAG, "Clear")
        mHistory.clear()
        updateHistory()
    }

    private fun updateHistory() {
        var s = ""
        mHistory.forEach { p ->  val e1 = p.first; val e2 = p.second; s += "$e1 - $e2 \n" }
        tvHistory.text = s
    }

    private fun onClickRoll() {
        val e1 = mRandomGenerator.nextInt(6) + 1
        val e2 = mRandomGenerator.nextInt(6) + 1

        val p = Pair(e1,e2)
        // update dices
        imgDice1.setImageResource( diceId[e1] )
        imgDice2.setImageResource( diceId[e2] )

        //update history
        mHistory.add(p)

        updateDicesWith(p)
        if (mHistory.size > 5) mHistory.removeAt(0)
        updateHistory()
        Log.d(TAG, "Roll")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "History saved")
        outState.putSerializable("history", mHistory.toTypedArray())

    }
}