package com.web.pulseeffect


import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Display
import android.view.View
import android.widget.RemoteViews
import com.web.pulseeffect.ui.RippleBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class RippleWidgetProvider : AppWidgetProvider() {

    private var progress = 0
    private val handler = Handler(Looper.getMainLooper())
   // private lateinit var remoteViews: RemoteViews
    private var rippleBackground: RippleBackground? = null

    @SuppressLint("RemoteViewLayout")
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

//        rippleBackground = RippleBackground(context)
//
//        appWidgetIds?.forEach { widgetId ->
//            remoteViews = RemoteViews(context!!.packageName, R.layout.ripple_widget_layout)
//            appWidgetManager?.updateAppWidget(widgetId,remoteViews)
//        }

    }



    @SuppressLint("RemoteViewLayout")
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        Log.d("TAG","onReceive Called")

        rippleBackground  = RippleBackground(context)
        val componentName = ComponentName(context!!,RippleWidgetProvider::class.java)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(componentName)
        appWidgetIds?.forEach { widgetId ->
            val  remoteViews = RemoteViews(context.packageName,R.layout.ripple_widget_layout)
            appWidgetManager.updateAppWidget(widgetId,remoteViews)
            rippleBackground!!.startRippleAnimation()
            handler.post(object : Runnable {
                override fun run() {
                    progress++
                    if (progress <= 100) {
                        remoteViews.setTextViewText(R.id.play,"$progress%")
                        remoteViews.setViewVisibility(R.id.play,View.VISIBLE)
                        remoteViews.setViewVisibility(R.id.playIcon,View.GONE)
                        handler.postDelayed(this, 1000) // 1000ms = 1 second
                    } else {
                        progress = 0 // Reset progress to 0
                        remoteViews.setTextViewText(R.id.play,"")
                        remoteViews.setViewVisibility(R.id.play,View.GONE)
                        remoteViews.setViewVisibility(R.id.playIcon,View.VISIBLE)
                        rippleBackground!!.stopRippleAnimation()
                    }
                }
            })
        }


    }
}