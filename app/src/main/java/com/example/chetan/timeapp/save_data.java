package com.example.chetan.timeapp;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class save_data extends AppCompatActivity {
    Button b;
    EditText time;
    EditText time1,subject,from,to,teachername,room_no;
    int pos;
    Spinner s;
    int iu , idrow,day ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        subject = (EditText) findViewById(R.id.editText) ;

        from=(EditText) findViewById(R.id.editText4);
        to=(EditText) findViewById(R.id.editText5);
        teachername=(EditText) findViewById(R.id.editText2);
        room_no=(EditText) findViewById(R.id.editText3);
        Bundle bb = getIntent().getExtras() ;
        iu = bb.getInt("id") ;
        pos = bb.getInt("pos") ;
        day = bb.getInt("DAY") ;
        Log.i("posii",""+pos) ;
            //if iu ==1 then editing is performed
        if(iu==1)
        {
            Cursor c = getContentResolver().query(database.CONTENT_URI,null,"_id = "+pos,null,null) ;
            c.moveToFirst() ;

            //new data will be feed to databse
            subject.setText(c.getString(c.getColumnIndex(database.subject)));
            from.setText(c.getString(c.getColumnIndex(database.from)));
            to.setText(c.getString(c.getColumnIndex(database.to)));
            teachername.setText(c.getString(c.getColumnIndex(database.teachername)));
            room_no.setText(c.getString(c.getColumnIndex(database.room_no)));

           // Log.i("S",c.getString(c.getColumnIndex(database.subject))) ;
        }
        time = (EditText) findViewById(R.id.editText4);
        s= (Spinner) findViewById(R.id.spinner);
        //when we click on edit text then time picker will open for choosing the time
        time.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(save_data.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String hour=i+"",minute=i1+"";
                        if(i/10==0)
                            hour="0"+hour;
                        if(i1/10==0)
                            minute="0"+minute;
                        time.setText(hour + ":" + minute);


                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        //till what time class will be i.e for what time class
        time1 = (EditText) findViewById(R.id.editText5);
        time1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(save_data.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        String hour=i+"",minute=i1+"";
                        if(i/10==0)
                            hour="0"+hour;
                        if(i1/10==0)
                            minute="0"+minute;
                        time1.setText(hour + ":" + minute);

                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


 // when save button is clicked following method is executed
    public void savedata(View view) {

        if(iu==0) {
            //for inserting new data
            ContentValues values = new ContentValues();
            String sub=((EditText) findViewById(R.id.editText)).getText().toString();
            String fr=((EditText) findViewById(R.id.editText4)).getText().toString();
            String t= ((EditText) findViewById(R.id.editText5)).getText().toString();
            String tname=((EditText) findViewById(R.id.editText2)).getText().toString();
            String rn=((EditText) findViewById(R.id.editText3)).getText().toString();
            if(TextUtils.isEmpty(sub)||TextUtils.isEmpty(fr)||TextUtils.isEmpty(t)||TextUtils.isEmpty(tname)||TextUtils.isEmpty(rn))
                Toast.makeText(this, "Fill All Entries", Toast.LENGTH_SHORT).show();
            else
            {
            values.put(database.subject, sub);
            values.put(database.type, (s.getSelectedItem().toString()));
            values.put(database.from, fr);
            values.put(database.to, t);
            values.put(database.teachername, tname);
            values.put(database.room_no, rn);
            values.put(database.day, day);
            getContentResolver().insert(database.CONTENT_URI, values);
           // finish();
        }
        }

        else
        {
            //for updating the previous saved data
            ContentValues values = new ContentValues();
            String sub=((EditText) findViewById(R.id.editText)).getText().toString();
            String fr=((EditText) findViewById(R.id.editText4)).getText().toString();
            String t= ((EditText) findViewById(R.id.editText5)).getText().toString();
            String tname=((EditText) findViewById(R.id.editText2)).getText().toString();
            String rn=((EditText) findViewById(R.id.editText3)).getText().toString();
            if(TextUtils.isEmpty(sub)||TextUtils.isEmpty(fr)||TextUtils.isEmpty(t)||TextUtils.isEmpty(tname)||TextUtils.isEmpty(rn))
                Toast.makeText(this, "Fill All Entries", Toast.LENGTH_SHORT).show();
            else
            {
            values.put(database.subject, sub );
            values.put(database.type, (s.getSelectedItem().toString()));
            values.put(database.from, fr);
            values.put(database.to, t);
            values.put(database.teachername,tname );
            values.put(database.room_no,rn );
            // values.put(database.day, day);
            getContentResolver().update(database.CONTENT_URI,values,"_id = "+ pos,null) ;
        }
        }
        finish();

    }
}