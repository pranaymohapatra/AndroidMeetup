package com.example.constraintlayoutexamples

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Guideline
import kotlinx.android.synthetic.main.parallax_effect_simple.*


class SimpleParallaxActivity : AppCompatActivity() {

    var guideline: Guideline? = null
    var baselayout: ConstraintLayout? = null
    var layoutParams: ConstraintLayout.LayoutParams? = null
    var pixel = 0
    var screenHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parallax_effect_simple)
        pixel = this.getResources().getDimensionPixelSize(R.dimen.fab_parallax)
        baselayout = findViewById(R.id.baselayout)
        guideline = baselayout!!.findViewById(R.id.guideline)
        layoutParams = guideline!!.layoutParams as ConstraintLayout.LayoutParams
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        screenHeight = metrics.heightPixels
        baselayout?.let {
            it.setOnTouchListener { view, motionEvent ->
                Log.d("MotionEvent", motionEvent.actionMasked.toString())
                when (motionEvent.actionMasked) {
                    MotionEvent.ACTION_MOVE -> {
                        layoutParams!!.guideEnd = screenHeight-motionEvent.y.toInt()
                        guideline!!.layoutParams = layoutParams
                        true
                    }
                    else -> true
                }
            }
        }
        profileView.setOnClickListener {
            androidx.transition.TransitionManager.beginDelayedTransition(baselayout!!)
            if (layoutParams!!.guideEnd != screenHeight) {
                layoutParams!!.guideEnd = screenHeight
            } else {
                layoutParams!!.guideEnd = pixel
            }
            guideline!!.layoutParams = layoutParams
        }
    }
}