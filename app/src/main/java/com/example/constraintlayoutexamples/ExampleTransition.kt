package com.example.constraintlayoutexamples

import android.content.Context
import android.util.AttributeSet
import android.view.animation.*
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionSet

class ExampleTransition : TransitionSet {

    constructor() {
        TransitionSet()
        init()
    }

    constructor(context: Context, attributes: AttributeSet) {
        TransitionSet(context, attributes)
        init()
    }

    private fun init() {
        duration = 1000
        ordering = ORDERING_SEQUENTIAL
        addTransition(TransitionSet()
                .addTransition(Fade(Fade.OUT))
                .addTransition(ChangeBounds()
                    .setInterpolator(OvershootInterpolator()))
        )
        addTransition(Fade(Fade.IN).setInterpolator(AccelerateDecelerateInterpolator()))

    }
}