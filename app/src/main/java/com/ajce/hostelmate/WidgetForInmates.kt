package com.ajce.hostelmate

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class WidgetForInmates : AppWidgetProvider() {
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        // There may be multiple widgets active, so update all of them
        if (appWidgetIds != null) {
            for (appWidgetId in appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }

    override fun onEnabled(context: Context?) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context?) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        fun updateAppWidget(context: Context?, appWidgetManager: AppWidgetManager?,
                            appWidgetId: Int) {
            val widgetText = "Widget will be updated when you report an issue"
            // Construct the RemoteViews object
            val views = RemoteViews(context?.packageName, R.layout.widget_for_inmates)
            views.setTextViewText(R.id.tvWidgetText, widgetText)

            // Instruct the widget manager to update the widget
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }
}