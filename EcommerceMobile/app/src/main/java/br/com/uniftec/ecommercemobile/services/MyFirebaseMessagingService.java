package br.com.uniftec.ecommercemobile.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import br.com.uniftec.ecommercemobile.MainActivity;
import br.com.uniftec.ecommercemobile.R;
import br.com.uniftec.ecommercemobile.model.Produto;
import br.com.uniftec.ecommercemobile.model.ProdutoResponse;
import br.com.uniftec.ecommercemobile.task.CarregarProdutosTask;
import br.com.uniftec.ecommercemobile.ui.LoginActivity;
import br.com.uniftec.ecommercemobile.ui.ProdutoActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService implements CarregarProdutosTask.CarregarProdutosDelegate {

    private static final String TAG = "Message";
    private RemoteMessage remoteMessage;
    private Produto produto;
    private String produtoId;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        this.remoteMessage = remoteMessage;
        this.produtoId = remoteMessage.getData().get("produtoId");

        boolean validacao = true;
        try {
            double d = Double.parseDouble(produtoId);
        }
        catch(NumberFormatException nfe) {
            validacao = false;
        }

        if(validacao) {
            carregarProdutos();
        }
    }


    @Override
    public void carregarProdutosSucesso(List<ProdutoResponse> listEcommerceResponse) {
        for (ProdutoResponse produtoResponse : listEcommerceResponse) {
            if (Objects.equals(produtoResponse.getId(), Long.valueOf(produtoId))) {
                this.produto = produtoResponse.getProduto();
            }
        }
        if (this.produto == null) {
            return;
        }

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notification);
        contentView.setImageViewBitmap(R.id.image, getBitmapFromURL(produto.getImagemPrincipal().getUrl()));
        contentView.setTextViewText(R.id.title, produto.getNome());
        contentView.setTextViewText(R.id.text, "Incrivel Promoção");

        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_heart_black_18dp)
                        .setLargeIcon(getBitmapFromURL(produto.getImagemPrincipal().getUrl()))
                        .setContent(contentView);
        // Creates an explicit intent for an Activity in your app

        SharedPreferences preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);

        String token = preferences.getString("X-Token", "null");

        Intent resultIntent = new Intent(this, ProdutoActivity.class);
        if(token.equals("null")) {
            Intent intent = null;
            intent = new Intent(this, LoginActivity.class);

            resultIntent = intent;
        }

        resultIntent.putExtra(ProdutoActivity.PRODUTO_PARAMETER, this.produto);


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
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(1, mBuilder.build());
    }


    @Override
    public void carregarProdutosFalha(String mensagem) {
        Log.d("Falha", "falha");
    }

    private void carregarProdutos() {
        CarregarProdutosTask task = new CarregarProdutosTask(this);
        Object[] parametros = new Object[2];

        parametros[0] = null;
        parametros[1] = null;
        task.execute(parametros);
    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

