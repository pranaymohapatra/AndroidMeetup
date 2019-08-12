package com.example.constraintperformance

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.FrameMetrics
import android.view.Window
import android.view.Window.OnFrameMetricsAvailableListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.reviewticket.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_linear)
        setContentView(R.layout.reviewticket)
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
                "Metrics",
                "LAYOUT_MEASURE_DURATION: " + metrics!!.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION) / Math.pow(
                    10.0,
                    6.0
                )
            )

            Log.d("Metrics", "DRAW_DURATION: " + metrics!!.getMetric(FrameMetrics.DRAW_DURATION) / Math.pow(10.0, 6.0))



            Log.d(
                "Metrics",
                "TOTAL_DURATION: " + metrics!!.getMetric(FrameMetrics.TOTAL_DURATION) / Math.pow(10.0, 6.0)
            )

        }, 1000)

        btn_go_to_payment.setOnClickListener { startActivity(Intent(this, ConstrainedActivity::class.java)) }
    }
}
