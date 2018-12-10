package com.example.csmyp7.lab4;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";
    private SensorManager senMa;
    private Sensor accel, gyro, mag, temp, light;

    TextView xAccel, yAccel, zAccel, xGyro, yGyro, zGyro, xMag, yMag, zMag, myTemp, myFTemp, a, g, m, t, myLight, myDate;
    CalendarView myCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //a=findViewById(R.id.textView16);
        //g=findViewById(R.id.textView18);
        //=findViewById(R.id.textView23);
        //t=findViewById(R.id.textView27);

        xAccel= findViewById(R.id.textView1);
        yAccel=findViewById(R.id.textView2);
        zAccel=findViewById(R.id.textView3);

        xGyro=findViewById(R.id.textView4);
        yGyro=findViewById(R.id.textView5);
        zGyro=findViewById(R.id.textView6);

        xMag=findViewById(R.id.textView7);
        yMag=findViewById(R.id.textView8);
        zMag=findViewById(R.id.textView9);

        myTemp=findViewById(R.id.textView10);
        myFTemp=findViewById(R.id.textView);

        myLight=findViewById(R.id.textView17);

        myDate=findViewById(R.id.tvCal);
        myCal=findViewById(R.id.cal);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat md = new SimpleDateFormat("dd MMM yyyy");
        String thisDate = md.format(c);

        Date d =Calendar.getInstance().getTime();
        SimpleDateFormat dy = new SimpleDateFormat("MMM dd yyyy");
       // myCal.setDate(valueOf(dy));



        Calendar currentDate = Calendar.getInstance();
        myDate.setText(thisDate);
        Log.d(TAG, valueOf(currentDate));
        Log.d(TAG, "Date: " + currentDate.MONTH+" "+currentDate.DAY_OF_MONTH+" "+currentDate.YEAR);
        Log.d(TAG, thisDate);
        //
        //myCal.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
         //   myCal.setOnClickListener(myDate.setText());
        //})

        senMa = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accel = senMa.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//accelerometer value int 1
        if(accel !=null) {
            senMa.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            xAccel.setText("0");
            yAccel.setText("0");
            zAccel.setText("0");
        }

        gyro=senMa.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyro !=null) {
            senMa.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            xGyro.setText("0");
            yGyro.setText("0");
            zGyro.setText("0");
        }

        mag=senMa.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(mag!=null){
            senMa.registerListener(this, mag, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            xMag.setText("0");
            yMag.setText("0");
            zMag.setText("0");
        }

        temp=senMa.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(temp!=null){
            senMa.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, temp.toString());
        }else{
            myTemp.setText("0");
            myFTemp.setText("0");
        }

        light=senMa.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light!=null){
            senMa.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            myLight.setText("0");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sen=event.sensor;
        if(sen.getType()==Sensor.TYPE_ACCELEROMETER){
            xAccel.setText(valueOf(event.values[0]));
            yAccel.setText(valueOf(event.values[1]));
            zAccel.setText(valueOf(event.values[2]));
            //Log.d(TAG, "X: " + event.values[0] + " Y: "+event.values[1] + " Z: " + event.values[2]);
        }else if(sen.getType()==Sensor.TYPE_GYROSCOPE){
            xGyro.setText(valueOf(event.values[0]));
            yGyro.setText(valueOf(event.values[1]));
            zGyro.setText(valueOf(event.values[2]));
        }else if(sen.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            xMag.setText(valueOf(event.values[0]));
            yMag.setText(valueOf(event.values[1]));
            zMag.setText(valueOf(event.values[2]));
        }else if(sen.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE){
            float celc =(event.values[0]*9)/5+32;//convert to fahrenheit
            myTemp.setText(valueOf(event.values[0])+"°C");
            myFTemp.setText(valueOf(celc)+"°F");
            //Log.d(TAG, "Temp: " +valueOf(event.values[0])+" and thats " + celc);
        }else if(sen.getType()==Sensor.TYPE_LIGHT){
            myLight.setText(valueOf(event.values[0])+" lx");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
