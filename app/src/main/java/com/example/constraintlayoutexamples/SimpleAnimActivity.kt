package com.example.constraintlayoutexamples

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class SimpleAnimActivity : AppCompatActivity() {
    private var layout: ConstraintLayout? = null
    private val constraintSetOld = ConstraintSet()
    private val constraintSetNew = ConstraintSet()
    private var altLayout: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_anim_act1)

        layout = findViewById(R.id.layout)

        constraintSetOld.clone(layout!!)
        constraintSetNew.clone(this, R.layout.simple_anim_act2)
    }

    fun swapView(v: View) {
        val changeBounds = ChangeBounds()
        changeBounds.interpolator = AnticipateOvershootInterpolator()
        changeBounds.duration = 800
        TransitionManager.beginDelayedTransition(layout, changeBounds)

        if (!altLayout) {
            constraintSetNew.applyTo(layout!!)
            altLayout = true
        } else {
            constraintSetOld.applyTo(layout!!)
             altLayout = false
        }
    }
}