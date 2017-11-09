package br.com.uniftec.ecommercemobile.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import br.com.uniftec.ecommercemobile.MainActivity;
import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.ui.ProdutoActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "Message";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_heart_black_18dp)
                        .setContentTitle("My notification")
                        .setContentText(remoteMessage.getNotification().getBody());
        // Creates an explicit intent for an Activity in your app

        /*Produto produto = new Produto();
        produto.setId(10);
        produto.setTitulo("Produto: " + produto.getId());
        produto.setPreco((double) 50);
        produto.setPreco_desconto(produto.getPreco() / 2);
        produto.setDescricao("O Produto: " + produto.getTitulo() + " é muito legal.");
        produto.setImagem_principal("ft_4gx0m4rifoqxbz9lejqq6wypqyo");*/

        Intent resultIntent = new Intent(this, MainActivity.class);
        //resultIntent.putExtra(ProdutoActivity.PRODUTO_PARAMETER, produto);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }
}

