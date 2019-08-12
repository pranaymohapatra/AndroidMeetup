package com.example.constraintlayoutexamples

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.placeholder.*
import kotlinx.android.synthetic.main.selector_activity.*

class MainActivity : AppCompatActivity() {

    val buttonClickListener = object : View.OnClickListener {
        override fun onClick(view: View?) {
            val newintent = when (view!!.id) {
                R.id.fabanimation -> Intent(this@MainActivity, SimpleFabAnimation::class.java)
                R.id.simpleparallax -> Intent(this@MainActivity, SimpleParallaxActivity::class.java)
                R.id.placeholder_animation -> Intent(this@MainActivity, PlaceholderAnimationActivity::class.java)
                R.id.simpleanimation -> Intent(this@MainActivity, SimpleAnimActivity::class.java)
                else -> null
            }
            newintent?.let { startActivity(it) }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selector_activity)
        setSupportActionBar(toolbar)
        fabanimation.setOnClickListener(buttonClickListener)
        simpleparallax.setOnClickListener(buttonClickListener)
        simpleanimation.setOnClickListener(buttonClickListener)
        placeholder_animation.setOnClickListener(buttonClickListener)
    }


// 1 placeholder


/*override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.placeholder)
}

    fun swapView(v: View) {
       placeholder.setContentId(v.id)
   }*/




// 2 placeholder example - different templates


 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_placeholder)
    }*/



}