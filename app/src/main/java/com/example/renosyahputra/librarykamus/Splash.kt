package com.example.renosyahputra.librarykamus

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity(),Runnable {
    lateinit var context: Context
    var dot = 0
    val handler = Handler()
    var progressLoading = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initWidget()
    }

    private fun initWidget(){
        context = this@Splash
        textLoading.setText("Loading")
        handler.postDelayed(this,1000)
    }

    override fun run() {
        if (progressLoading == 8){
            startActivity(Intent(context,MainActivity::class.java))
            finish()
        }
        dot = if (dot == 4) 0 else dot
        var textDot = ""
        for (i in 0..dot-1){
            textDot += "."
        }
        textLoading.setText("Loading${textDot}")
        dot++
        progressLoading++
        handler.postDelayed(this,1000)
    }
}
