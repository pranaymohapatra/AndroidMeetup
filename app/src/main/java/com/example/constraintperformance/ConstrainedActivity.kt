package com.example.constraintperformance

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.FrameMetrics
import android.view.Window
import android.view.Window.OnFrameMetricsAvailableListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.reviewticket.*


class ConstrainedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.reviewticket_constraint)
        var metrics: FrameMetrics? = null
        val handler = Handler()
        window.addOnFrameMetricsAvailableListener(object : OnFrameMetricsAvailableListener {
            override fun onFrameMetricsAvailable(window: Window, frameMetrics: FrameMetrics, i: Int) {
                metrics = FrameMetrics(frameMetrics)
                // Layout measure duration in nanoseconds
                val layoutMeasureDurationNs =
                    metrics!!.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION);
            }
        }, handler)

        handler.postDelayed({
            Log.d(
                "Metrics_Constrained",
                "LAYOUT_MEASURE_DURATION: " + metrics!!.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION) / Math.pow(
                    10.0,
                    6.0
                )
            )

            Log.d("Metrics_Constrained", "DRAW_DURATION: " + metrics!!.getMetric(FrameMetrics.DRAW_DURATION) / Math.pow(10.0, 6.0))



            Log.d(
                "Metrics_Constrained",
                "TOTAL_DURATION: " + metrics!!.getMetric(FrameMetrics.TOTAL_DURATION) / Math.pow(10.0, 6.0)
            )

        }, 1000)
    }
}
