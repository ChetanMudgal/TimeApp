package com.example.chetan.timeapp;

import android.content.Context;
import android.content.Intent;
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

public class days_name extends AppCompatActivity {
    String[] data={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    ListView l;
    int[] images = {R.drawable.monday,R.drawable.tuesday,R.drawable.wednesday,R.drawable.thursday,R.drawable.friday,R.drawable.saturday,R.drawable.sunday};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_name);
        l=(ListView) findViewById(R.id.listView1);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.single_row_fordays,R.id.textView2,data);
        NAdapter adapter = new NAdapter(this,data,images);
        l.setAdapter(adapter);
        l.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String cities = String.valueOf(parent.getItemAtPosition(position));
                        if(position==0) {
                            Intent i = new Intent(days_name.this, Monday.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        else if(position==1)
                        {
                            Intent i = new Intent(days_name.this, Tuesday.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        else if(position==2)
                        {
                            Intent i = new Intent(days_name.this, Wednsday.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        else if(position==3)
                        {
                            Intent i = new Intent(days_name.this, Thursday.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        else if(position==4)
                        {
                            Intent i = new Intent(days_name.this, Friday.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        else if(position==5)
                        {
                            Intent i= new Intent(days_name.this,Saturday.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        else if(position==6)
                        {
                            Intent i= new Intent(days_name.this,Sunday.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }

                    }

                });

    }
    class NAdapter extends ArrayAdapter<String>
    {    Context context;
        int[] images;
        NAdapter(Context c,String[] data,int imgs[]){
            super(c,R.layout.single_row_fordays,R.id.textView2,data);
            this.context=c;
            this.images=imgs;

        }
        public View getView(int position,View convertView, ViewGroup parent){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.single_row_fordays,parent,false);
            ImageView myImage= (ImageView) row.findViewById(R.id.imageView2);
            TextView myTitle = (TextView) row.findViewById(R.id.textView2);
            myImage.setImageResource(images[position]);
            myTitle.setText(data[position]);
            return row;
        }
    }
}
