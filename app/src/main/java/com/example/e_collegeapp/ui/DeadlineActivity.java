package com.example.e_collegeapp.ui;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.e_collegeapp.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeadlineActivity extends AppCompatActivity {
     @BindView(R.id.buttonview)
     Button btnview;

     @BindView(R.id.textViewDeadline)
     TextView txtdeadline;

     DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline);
        ButterKnife.bind(this);
        btnview.setOnClickListener(clickListener);
    }
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String date = year+"/"+(month+1)+"/"+dayOfMonth;
            txtdeadline.setText(date);
        }
    };
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showdatepickerDialog();
            ViewShowDeadline();

        }
    };
    void showdatepickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(this, onDateSetListener, yy, mm, dd);
        datePickerDialog.show();
    }
   void ViewShowDeadline(){
       NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
       if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
           NotificationChannel notificationChannel = new NotificationChannel("myId","myName",NotificationManager.IMPORTANCE_HIGH);
           notificationManager.createNotificationChannel(notificationChannel);
       }
       NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myId");
       builder.setSmallIcon(R.drawable.ic_email);
       builder.setContentTitle("This is Title");
       builder.setContentText("This is Text");
       Intent intent = new Intent(DeadlineActivity.this,InfoActivity .class);
       PendingIntent pendingIntent = PendingIntent.getActivity(this, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);
       builder.setContentIntent(pendingIntent);
       builder.setStyle(new NotificationCompat.BigTextStyle());
       builder.addAction(android.R.drawable.ic_menu_add, "Add",pendingIntent);
       builder.addAction(android.R.drawable.ic_menu_delete, "Delete",pendingIntent);
       Notification notification = builder.build();
       notificationManager.notify(101, notification);

   }
}
