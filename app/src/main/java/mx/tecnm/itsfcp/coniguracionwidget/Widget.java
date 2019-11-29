package mx.tecnm.itsfcp.coniguracionwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class Widget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setTextViewTextSize(R.id.LblMensaje, TypedValue.COMPLEX_UNIT_SP, ConfiguracionWidget.seekValue);
        views.setInt(R.id.fondow, "setBackgroundColor", Color.parseColor("#"+ConfiguracionWidget.transparencia+ConfiguracionWidget.colorhex));
        views.setTextColor(R.id.LblMensaje, ConfiguracionWidget.colorActualTexto);
        views.setTextViewText(R.id.LblMensaje,ConfiguracionWidget.transparencia);


        //ABRIR ACTIVIDAD
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,appWidgetId,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.fondow,pendingIntent);

        //ABRIR ACTIVIDAD
        Intent intent2 = new Intent(context, Configuracion2.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context,appWidgetId,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.btnPersonalizacion,pendingIntent2);








        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;

        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, Configuracion2.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            views.setOnClickPendingIntent(R.id.btnPersonalizacion, pendingIntent);


                views.setTextViewTextSize(R.id.LblMensaje, TypedValue.COMPLEX_UNIT_SP, Configuracion2.seekValue);
                views.setInt(R.id.fondow, "setBackgroundColor", Color.parseColor("#"+Configuracion2.transparencia+Configuracion2.colorhex));
                views.setTextColor(R.id.LblMensaje, Configuracion2.colorActualTexto);
                views.setTextViewText(R.id.LblMensaje,Configuracion2.transparencia);



            appWidgetManager.updateAppWidget(appWidgetId, views);
        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled

    }



}

