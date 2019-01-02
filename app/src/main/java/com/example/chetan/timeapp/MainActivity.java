package com.example.chetan.timeapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String[] memeTitles;
    ListView list;
    int[] images = {R.drawable.logo1,R.drawable.logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarm();
        Resources res=getResources();
        memeTitles=res.getStringArray(R.array.titles);
        list = (ListView) findViewById(R.id.listView);

        MAdapter adapter = new MAdapter(this,memeTitles,images);
        list.setAdapter(adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String cities = String.valueOf(parent.getItemAtPosition(position));
                        if(position==0) {
                            Intent i = new Intent(MainActivity.this, days_name.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        else if(position==1)
                        {
                            Intent i = new Intent(MainActivity.this,Developers.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    }

    });

}
    public void alarm()
    {

        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Date dat = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY,07);
        cal_alarm.set(Calendar.MINUTE,00);
        cal_alarm.set(Calendar.SECOND,0);
        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }

        Intent myIntent = new Intent(this, MyBroadcastReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        manager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
       // Toast.makeText(this, "notification inserted", Toast.LENGTH_SHORT).show();
    }

class MAdapter extends ArrayAdapter<String>
{    Context context;
    int[] images;
    String[] titleArray;
    MAdapter(Context c,String[] titles,int imgs[]){
        super(c,R.layout.single_row,R.id.textView,titles);
        this.context=c;
        this.images=imgs;
        this.titleArray=titles;
    }
    public View getView(int position,View convertView, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.single_row,parent,false);
        ImageView myImage= (ImageView) row.findViewById(R.id.imageView);
        TextView myTitle = (TextView) row.findViewById(R.id.textView);
        myImage.setImageResource(images[position]);
        myTitle.setText(titleArray[position]);
        return row;
    }
}}