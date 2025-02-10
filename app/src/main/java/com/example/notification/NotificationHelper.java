package com.example.notification;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper  extends MainActivity {
    public static final String CHANNEL_ID_LOW = "low_importance_channel";
    public static final String CHANNEL_ID_DEFAULT = "default_importance_channel";
    public static final String CHANNEL_ID_HIGH = "high_importance_channel";
    public static final String CHANNEL_NAME = "monke";

    public static void createNotificationChannels(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            NotificationChannel channelLow = new NotificationChannel(CHANNEL_ID_LOW, CHANNEL_NAME, notificationManager.IMPORTANCE_LOW);
            NotificationChannel channelDefault = new NotificationChannel(CHANNEL_ID_DEFAULT, CHANNEL_NAME,notificationManager.IMPORTANCE_LOW);
            NotificationChannel channelHigh = new NotificationChannel(CHANNEL_ID_HIGH, CHANNEL_NAME, notificationManager.IMPORTANCE_LOW);


            if(notificationManager != null){
                notificationManager.createNotificationChannel(channelLow);
                notificationManager.createNotificationChannel(channelDefault);
                notificationManager.createNotificationChannel(channelHigh);
            }



        }
    }
    static void sendNotification(String title, String message, String CHANNEL_ID, int obrazek, MainActivity Obiekt, int style, int czanel){
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(Obiekt.getResources(), obrazek);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            CharSequence name = "Kanał Powiadomień";
            String description = "Opis Kanału Powiadomień";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notifiactionManager = Obiekt.getSystemService(NotificationManager.class);
            notifiactionManager.createNotificationChannel(channel);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(Obiekt.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED){
                Obiekt.requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }


        Intent intent = new Intent(Obiekt, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(Obiekt, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(Obiekt, CHANNEL_ID)
                .setSmallIcon(R.drawable.monka)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
                switch (style){
                    case 1:
                                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                                break;
                    case 2:
                                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
                                break;
                    case 3:
                                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                                inboxStyle.addLine(message);
                                inboxStyle.addLine("monka monka monkaaaaaaaaaaaaa");
                                builder.setStyle(inboxStyle);
                                break;

                }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Obiekt);
        notificationManager.notify(czanel, builder.build());
    }
//    static void sendNotification(String title, String message, String CHANNEL_ID, MainActivity Obiekt){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
//            CharSequence name = "Kanał Powiadomień";
//            String description = "Opis Kanału Powiadomień";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            NotificationManager notifiactionManager = Obiekt.getSystemService(NotificationManager.class);
//            notifiactionManager.createNotificationChannel(channel);
//        }
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
//            if(Obiekt.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
//                    != PackageManager.PERMISSION_GRANTED){
//                Obiekt.requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
//                return;
//            }
//        }
//
//
//        Intent intent = new Intent(Obiekt, MainActivity.class);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(Obiekt, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(Obiekt, CHANNEL_ID)
//                .setSmallIcon(R.drawable.monka)
//                .setContentTitle("Nowe powiadomienie")
//                .setContentText("Tekst Powiadomeinia")
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
//
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true);
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Obiekt);
//        notificationManager.notify(NotificationId, builder.build());
//        NotificationId ++;
//    }

}