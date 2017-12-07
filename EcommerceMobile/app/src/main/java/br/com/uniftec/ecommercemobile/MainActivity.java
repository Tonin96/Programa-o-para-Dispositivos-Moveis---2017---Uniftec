package br.com.uniftec.ecommercemobile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import br.com.uniftec.ecommercemobile.adapter.ListaEnderecoAdapter;
import br.com.uniftec.ecommercemobile.task.RemoverUsuarioEnderecoTask;
import br.com.uniftec.ecommercemobile.ui.AbstractActivity;
import br.com.uniftec.ecommercemobile.ui.AlteraContaUsuarioActivity;
import br.com.uniftec.ecommercemobile.ui.CarrinhoActivity;
import br.com.uniftec.ecommercemobile.ui.ListaPedidosFragment;
import br.com.uniftec.ecommercemobile.ui.ListaProdutoFragment;
import br.com.uniftec.ecommercemobile.ui.LoginActivity;

public class MainActivity extends AbstractActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SharedPreferences preferences;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupView() {
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);

        String token = preferences.getString("X-Token", "null");

        Log.d("token retornado", token);

        if(token.equals("null")) {
            Intent intent = null;
            intent =  new Intent(this, LoginActivity.class);

            this.startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawers();

        Fragment fragment = null;
        Intent intent = null;

        switch (item.getItemId()) {

            case R.id.menu_lista_produtos:
                fragment = new ListaProdutoFragment();
                break;

            case R.id.menu_carrinho:
                intent = new Intent(this, CarrinhoActivity.class);

                this.startActivity(intent);
                break;

            case R.id.menu_pedidos:
                fragment = new ListaPedidosFragment();
                break;

            case R.id.menu_logout:
                this.confirmarLogout(this);
                break;
        }

        if (fragment != null) {
            mudarContainerPrincipal(fragment);

            return true;
        }

        return false;
    }

    private void mudarContainerPrincipal(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
    }

    private void confirmarLogout(final Context context) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("Confirmar logout?");

        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                preferences.edit().clear().commit();

                Intent intent = new Intent(context, LoginActivity.class);

                context.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }
}
