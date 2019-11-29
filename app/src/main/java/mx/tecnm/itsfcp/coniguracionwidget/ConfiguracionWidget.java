package mx.tecnm.itsfcp.coniguracionwidget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ConfiguracionWidget extends AppCompatActivity {
    public int widgetId = 0;
    private Button btnAceptar;
    private Button btnCancelar;
    public Button btnColor;
    public Button btnColorTexto;
    public CheckBox cbEnable;
    TextView tV;
    TextView tV2;
    SeekBar sB;
    SeekBar sB2;
    public static float seekValue = 20;
    public static int seekValue2 = 0;
    public static int formulaTransparencia;
    public static String transparencia = "ff";
    public static int colorActual;
    public static String colorhex = "ffffff";
    public static int colorActualTexto = 999999999;
    public static String colorCompleto = "ffffff";
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
        cbEnable = findViewById(R.id.cbEnable);
        tV = findViewById(R.id.txtTexto2);
        tV2 = findViewById(R.id.txtTexto3);
        sB = findViewById(R.id.seekBar);
        sB2 = findViewById(R.id.seekBar2);
        btnColor = findViewById(R.id.btnColor);
        btnColorTexto = findViewById(R.id.btnColorTexto);
        colorActual = ContextCompat.getColor(ConfiguracionWidget.this, R.color.colorPrimary);
        sB2.setEnabled(false);





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

        //METODO DE LA SEEKBAR TAMAÑO DE TEXTO

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


        //METODO DE LA SEEKBAR TRANSPARENCIA

        if (cbEnable.isChecked()){

        }
        else {
            transparencia = "ff";
        }

        cbEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbEnable.isChecked()){
                    sB2.setEnabled(true);
                    sB2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                            seekValue2 = progress;
                            String temp = String.valueOf(seekValue2);
                            tV2.setText(temp);
                            tV2.setTextSize(seekValue);

                            //Progreso de la seekBar en valor entero
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            String temp = String.valueOf(seekValue2);
                            tV2.setText(temp);

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                            if (seekValue2 >0 && seekValue2 <= 90){
                                formulaTransparencia = 100-seekValue2;
                                transparencia = String.valueOf(formulaTransparencia);

                            }
                            else if (seekValue2 >90 && seekValue2<100){
                                formulaTransparencia = 100-seekValue2;
                                transparencia = "0" + String.valueOf(formulaTransparencia);


                            }
                            else if (seekValue2==0){
                                transparencia = "ff";
                            }
                            else{
                                transparencia="00";

                            }

                            String temp = String.valueOf(seekValue2);
                            tV2.setText(temp);

                        }
                    });

                }

                else {
                    sB2.setEnabled(false);
                    sB2.setProgress(0);
                    transparencia="ff";

                }
            }
        });




        //Boton de selección de color Fondo

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        //Boton de selección de color Texto

        btnColorTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker2();
            }
        });
    }


    //METODO DE ELECCIÓN DE COLOR FONDO
    public void openColorPicker (){
        AmbilWarnaDialog colorPicker = new  AmbilWarnaDialog(this, colorActual, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                colorActual = color;
                colorCompleto = Integer.toHexString(color);
                colorhex = colorCompleto.substring(2,8);


            }
        });
        colorPicker.show();
    }

    //METODO DE ELECCIÓN DE COLOR Texto
    public void openColorPicker2 (){
        AmbilWarnaDialog colorPicker2 = new  AmbilWarnaDialog(this, colorActualTexto, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                colorActualTexto = color;



            }
        });
        colorPicker2.show();
    }
}
