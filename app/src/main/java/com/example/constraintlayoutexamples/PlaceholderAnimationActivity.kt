package com.example.constraintlayoutexamples

import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.placeholder_animations_extended.*

class PlaceholderAnimationActivity : AppCompatActivity() {

    var constraintSet1: ConstraintSet = ConstraintSet()
    var constraintSet2: ConstraintSet = ConstraintSet()
    var rootView: ConstraintLayout? = null
    var selectedId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.placeholder_animations_extended)
        rootView = findViewById(R.id.root_view)
        constraintSet1.clone(rootView)
        constraintSet2.clone(constraintSet1)
        constraintSet2.connect(R.id.iv_photographer, ConstraintSet.TOP, R.id.expanding_bg, ConstraintSet.BOTTOM,0)
        constraintSet2.setVisibility(R.id.title_name, View.VISIBLE)

    }
    fun swapView(view: View?) {

        val id = view!!.id
        val transition = ExampleTransition()
        if (selectedId != id) {
            TransitionManager.beginDelayedTransition(root_view, transition)
            selectedId = id
            constraintSet2.applyTo(rootView)
            val size = this.resources.getDimensionPixelSize(R.dimen.placeholder_min_size)
            val layoutparams = view!!.layoutParams
            layoutparams.height = size
            layoutparams.width = size
            view!!.layoutParams = layoutparams
            placeholder.setContentId(view!!.id)
        } else {
            val transition2 = ChangeBounds()
            transition2.interpolator = BounceInterpolator()
            transition2.duration = 800
            TransitionManager.beginDelayedTransition(root_view, transition2)
            selectedId = -1
            placeholder.setContentId(selectedId)
            constraintSet1.applyTo(rootView)

        }

    }
}