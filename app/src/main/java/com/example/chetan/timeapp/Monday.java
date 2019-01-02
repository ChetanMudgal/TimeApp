package com.example.chetan.timeapp;

        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.PopupMenu;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;

        import static com.example.chetan.timeapp.database.from;
        import static com.example.chetan.timeapp.Monday.s0;

/**
 * Created by HP on 04-10-2017.
 */

public class Monday extends AppCompatActivity {
   public static ListView list;
    Aadapter aadapter ;
    public static String s0;
    int p ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_activity1);
        //representing monday taken from database column day
        s0=" day='0'";
        list= (ListView) findViewById(R.id.list1);

        Cursor c = getContentResolver().query(database.CONTENT_URI,null,s0,null,null) ;
        aadapter = new Aadapter(this,c) ;
        list.setAdapter( aadapter);
        FloatingActionButton fab;

        fab =(FloatingActionButton) findViewById(R.id.floatingActionButton1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Monday.this,save_data.class);
                //i.putExtra("id",0);
                Bundle b = new Bundle();
                b.putInt("DAY",0);
                b.putInt("id",0) ;
                i.putExtras(b) ;
                startActivity(i);
            }
        });

    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor c = getContentResolver().query(database.CONTENT_URI,null,s0,null,null) ;
        aadapter = new Aadapter(this,c);
        list.setAdapter( aadapter);
    }
//for setting the notification
//    public void alarm()
//    {
//
//        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//        Date dat = new Date();
//        Calendar cal_alarm = Calendar.getInstance();
//        Calendar cal_now = Calendar.getInstance();
//        cal_now.setTime(dat);
//        cal_alarm.setTime(dat);
//        cal_alarm.set(Calendar.HOUR_OF_DAY,07);
//        cal_alarm.set(Calendar.MINUTE,00);
//        cal_alarm.set(Calendar.SECOND,0);
//        if(cal_alarm.before(cal_now)){
//            cal_alarm.add(Calendar.DATE,1);
//        }
//
//        Intent myIntent = new Intent(this, MyBroadcastReciever.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
//
//        manager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);
//
//    }
}
//make the class of all the parameter of save data xmlfile

class Singlerow1
{
    String subject,teacher,type,room,timings;


    public Singlerow1(String subject,String teacher,String type,String room,String timings ) {
        this.subject=subject;
        this.room=room;
        this.teacher=teacher;
        this.timings=timings;
        this.type=type;
    }
}

class Aadapter extends BaseAdapter {
    ArrayList<Singlerow1> list;  //array list of each type of data
    Context context;
    Cursor cursor;
    String [] data=new String[6];

    int _id;
    ImageButton imageButton;
    Aadapter(Context c,Cursor cc){
        context=c;
        cursor=cc;
    }

    @Override
    public int getCount() {
        if(cursor!=null&&cursor.getCount()!=0)
            return cursor.getCount() ;
        else
            return 0 ;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
           //p=position;
        //inflating the xml file of the card view on the list view
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_card_view,parent,false);
         imageButton = (ImageButton) row.findViewById(R.id.imageb);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show();
                showPopupMenu(view,position);

            }
        });

// getting the id of the respective text views

        TextView title= (TextView) row.findViewById(R.id.textView3);
        TextView title1= (TextView) row.findViewById(R.id.textView11);
        TextView title2= (TextView) row.findViewById(R.id.textView12);
        TextView title3= (TextView) row.findViewById(R.id.textView13);
        TextView title4= (TextView) row.findViewById(R.id.textView14);
        cursor.moveToPosition(position) ;
        _id=cursor.getInt(cursor.getColumnIndex(database._ID));

        //here the data string is used to notify the data

        data[0]=cursor.getString(cursor.getColumnIndex(database.subject));
        data[1]=cursor.getString(cursor.getColumnIndex(database.teachername));
        data[2]=cursor.getString(cursor.getColumnIndex(database.type));
        data[3]=cursor.getString(cursor.getColumnIndex(database.room_no));
        data[4]=cursor.getString(cursor.getColumnIndex(database.from));
        data[5]=cursor.getString(cursor.getColumnIndex(database.to));
        title.setText(cursor.getString(cursor.getColumnIndex(database.subject)));
        title1.setText(cursor.getString(cursor.getColumnIndex(database.teachername)));
        title2.setText(cursor.getString(cursor.getColumnIndex(database.type)));
        title3.setText(cursor.getString(cursor.getColumnIndex(database.room_no)));
        String a=(cursor.getString(cursor.getColumnIndex(database.from)));
        String b=(cursor.getString(cursor.getColumnIndex(database.to)));
        String d=a+"-"+b;
        title4.setText(d);

        return row;
    }
    private void showPopupMenu(View view,int position) {
        // inflate menu

        PopupMenu popup =null;
            popup = new PopupMenu(view.getContext(), view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.main_menu, popup.getMenu());

        //passing the data string to the MyMenuItemClickListener
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position,context,_id,data));
        popup.show();
    }



}
class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

    int position;
    Context c;
    int id;
    String[] data=new String[6];
    public MyMenuItemClickListener(int positon, Context context, int id,String[] data) {
        this.position = positon;
        this.c = context;
        this.id = id;
        this.data=data;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.menu_edit:  //to edit the data

                Intent i = new Intent(c,save_data.class) ; //to move again from current class to savadata class
                Bundle b = new Bundle() ;
                b.putInt("id",1) ;
                Cursor cc = c.getContentResolver().query(database.CONTENT_URI,null,s0,null,null) ;
                cc.moveToPosition(position) ;
                b.putInt("pos",cc.getInt(cc.getColumnIndex(database._ID))) ;
                i.putExtras(b) ;
                c.startActivity(i);


                return true;
            case R.id.menu_delete:
                Log.d("check ID",position+"");

                //Cursor
                        cc = c.getContentResolver().query(database.CONTENT_URI,null,s0,null,null) ;
                cc.moveToPosition(position) ;
                deleteCard(cc.getInt(cc.getColumnIndex(database._ID)),c);

                 cc = c.getContentResolver().query(database.CONTENT_URI,null,s0,null,null) ;
                Aadapter aadapter = new Aadapter(c,cc);
                Monday.list.setAdapter( aadapter);
                // c.getContentResolver().delete(database.CONTENT_URI, null,null);
               return true;
            case R.id.menu_notify:
                //to notify the whole time table before the first class
                Intent share=new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT,"Teacher: "+data[1]+"\nSubject: "+data[0]+"\nType: "+data[2]+"\nRoom No."+data[3]+"\nFrom: "+data[4]+"\nTo: "+data[5]);
                c.startActivity(share);
                return true;

         }
        return false;
    }

    public void deleteCard(int id,Context c){
       String where="_ID = ? ";
       String [] s={id+""};
         c.getContentResolver().delete(database.CONTENT_URI,where,s);
    }


}


