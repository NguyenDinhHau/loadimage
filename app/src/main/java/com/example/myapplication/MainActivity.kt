package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_load.setOnClickListener {
            Picasso.with(this)
                    .load("https://img2.thuthuatphanmem.vn/uploads/2018/12/25/nhung-hinh-anh-gai-xinh-cuc-dep_012909400.jpg")
                    .error(R.drawable.anh1)
                    .placeholder(R.drawable.vu_3)
                    .into(img_view)
        }
    }
}
