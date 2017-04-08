package com.example.usuarioupt.sensorapp;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
    implements SensorEventListener{

    ConstraintLayout fondo;
    Button activador;
    Sensor s;
    SensorManager sensorM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //_
        fondo = (ConstraintLayout)findViewById(R.id.fondo);
        activador =  (Button)findViewById(R.id.btn_sensor);

        activador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorM = (SensorManager)getSystemService(SENSOR_SERVICE);
                s = sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                sensorM.registerListener(MainActivity.this
                        ,s,SensorManager.SENSOR_DELAY_NORMAL);
                activador.setText("ACTIVADO");
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float valor = Float.parseFloat(String.valueOf(event.values[0]));

        if(valor <= 2){
            fondo.setBackgroundColor(Color.BLUE);
        }
        else{
            fondo.setBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void  onPause(){
        super.onPause();
        sensorM.unregisterListener(MainActivity.this
                ,sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY));
        activador.setText("Activar Sensor");
    }
}
