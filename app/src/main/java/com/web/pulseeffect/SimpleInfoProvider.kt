package com.web.pulseeffect

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.widget.RemoteViews

class SimpleInfoProvider : AppWidgetProvider() {


    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)


        appWidgetIds?.forEach { widgetID ->
            val remoteViews  = RemoteViews(context!!.packageName,R.layout.simple_info_layout)
            remoteViews.setTextViewText(R.id.name,"Taki Eddine")
            remoteViews.setTextViewText(R.id.familyName,"Gastalli")
            appWidgetManager?.updateAppWidget(widgetID,remoteViews)
        }

    }

    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?,
        appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }
}