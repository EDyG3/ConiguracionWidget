package mx.tecnm.itsfcp.coniguracionwidget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ConfiguracionWidget extends AppCompatActivity {
    public int widgetId = 0;
    private Button btnAceptar;
    private Button btnCancelar;
    public Button btnColor;
    TextView tV;
    SeekBar sB;
    public static float seekValue;
    public static int colorActual;
    public static String colorhex = "ffffff";
    LinearLayout fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_widget);

        Intent intentOrigen = getIntent();
        Bundle params = intentOrigen.getExtras();

        widgetId = params.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        setResult(RESULT_CANCELED);

        fondo = (LinearLayout) findViewById(R.id.fondo);
        btnAceptar = (Button)findViewById(R.id.BtnAceptar);
        btnCancelar = (Button)findViewById(R.id.BtnCancelar);
        tV = findViewById(R.id.txtTexto2);
        sB = findViewById(R.id.seekBar);
        btnColor = findViewById(R.id.btnColor);
        colorActual = ContextCompat.getColor(ConfiguracionWidget.this, R.color.colorPrimary);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Devolvemos como resultado: CANCELAR (RESULT_CANCELED)
                finish();
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Actualizamos el widget tras la configuración
                AppWidgetManager appWidgetManager =
                        AppWidgetManager.getInstance(ConfiguracionWidget.this);
                Widget.updateAppWidget(ConfiguracionWidget.this, appWidgetManager, widgetId);

                //Devolvemos como resultado: ACEPTAR (RESULT_OK)
                Intent resultado = new Intent();
                resultado.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
                setResult(RESULT_OK, resultado);
                finish();
            }
        });

        //METODO DE LA SEEKBAR

        sB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekValue = progress;


                //Progreso de la seekBar en valor entero
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                String temp = "Cargando...";
                tV.setText(temp);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String temp = "Texto de muestra";
                tV.setText(temp);
                tV.setTextSize(seekValue);
            }
        });

        //Boton de selección de color

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });
    }


    //METODO DE ELECCIÓN DE COLOR
    public void openColorPicker (){
        AmbilWarnaDialog colorPicker = new  AmbilWarnaDialog(this, colorActual, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                colorActual = color;
                colorhex = Integer.toHexString(color);


            }
        });
        colorPicker.show();
    }
}
