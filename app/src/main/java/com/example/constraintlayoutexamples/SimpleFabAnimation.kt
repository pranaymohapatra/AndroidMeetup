package com.example.constraintlayoutexamples

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fab_animation.*

class SimpleFabAnimation : AppCompatActivity() {

    var constraintLayout: ConstraintLayout? = null
    var constraintSetBegin = ConstraintSet()
    var constraintSetEnd = ConstraintSet()
    var constraintFabAnim = ConstraintSet()
    var isFinal = false
    var isOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        constraintLayout = findViewById(R.id.content)
        constraintSetBegin.clone(constraintLayout)
        constraintSetEnd.clone(this, R.layout.fab_animation_final)
        constraintFabAnim.clone(constraintSetEnd)
        constraintFabAnim.constrainCircle(R.id.button1,R.id.fab,this.resources.getDimensionPixelSize(R.dimen.fab_parallax),270f)
        constraintFabAnim.constrainCircle(R.id.button2,R.id.fab,this.resources.getDimensionPixelSize(R.dimen.fab_parallax),315f)
        constraintFabAnim.constrainCircle(R.id.button4,R.id.fab,this.resources.getDimensionPixelSize(R.dimen.fab_parallax),0f)
        tv?.let {
            it.setOnClickListener {
                val transition = ChangeBounds()
                transition.interpolator = BounceInterpolator()
                transition.duration = 800
                androidx.transition.TransitionManager.beginDelayedTransition(constraintLayout!!, transition)
                if (isFinal) {
                    constraintSetBegin.applyTo(constraintLayout!!)
                    isFinal = false
                } else {
                    constraintSetEnd.applyTo(constraintLayout!!)
                    isFinal = true
                }
            }
        }

        fab.setOnClickListener{
            val transition = ChangeBounds()
            transition.interpolator = OvershootInterpolator()
            transition.duration = 500
            androidx.transition.TransitionManager.beginDelayedTransition(constraintLayout!!, transition)
            if (isOpened) {
                constraintSetEnd.applyTo(constraintLayout!!)
                isOpened = false
            } else {
                constraintFabAnim.applyTo(constraintLayout!!)
                isOpened = true
            }
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
