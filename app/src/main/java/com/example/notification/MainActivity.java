package com.example.notification;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private static final String CHANNEL_ID = "My_channel_id";
    private Button haha;
    private Button dwa;
    private Button fourth;
    private Bitmap bitmap;
    private int monka = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.monka);
        haha = findViewById(R.id.guzik);
        haha.setOnClickListener( v -> {
            NotificationHelper.sendNotification("Tytuł", "Zawartość", NotificationHelper.CHANNEL_ID_HIGH, R.drawable.monka,this, 1, monka);
        monka++;
        });
        dwa = findViewById(R.id.guzik3);
        dwa.setOnClickListener( v -> {
        NotificationHelper.sendNotification("Tytuł", "Zawartość", NotificationHelper.CHANNEL_ID_DEFAULT, R.drawable.monka,this, 2, monka);
            monka++;
        });
        fourth = findViewById(R.id.guzik4);
        fourth.setOnClickListener( v -> {
            NotificationHelper.sendNotification("Tytuł", "Zawartość", NotificationHelper.CHANNEL_ID_LOW, R.drawable.monka,this, 3, monka);
            monka++;
        });
    }

    void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            CharSequence name = "Kanał Powiadomień";
            String description = "Opis Kanału Powiadomień";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notifiactionManager = getSystemService(NotificationManager.class);
            notifiactionManager.createNotificationChannel(channel);
        }
    }

    void sendNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }


        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.monka)
                .setContentTitle("Nowe powiadomienie")
                .setContentText("Tekst Powiadomeinia")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    void sendNotificationLong(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }


        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.monka)
                .setContentTitle("Nowe powiadomienie")
                .setContentText("Tekst Powiadomeinia")
//
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }


}
