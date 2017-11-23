package br.com.uniftec.ecommercemobile;

import android.content.Context;
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
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.uniftec.ecommercemobile.ui.AbstractActivity;
import br.com.uniftec.ecommercemobile.ui.AlteraContaUsuarioActivity;
import br.com.uniftec.ecommercemobile.ui.CarrinhoActivity;
import br.com.uniftec.ecommercemobile.ui.ListaPedidosFragment;
import br.com.uniftec.ecommercemobile.ui.ListaProdutoFragment;
import br.com.uniftec.ecommercemobile.ui.LoginActivity;

public class MainActivity extends AbstractActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SharedPreferences user_preferences;

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

        user_preferences = this.getSharedPreferences("usuario_preferences", Context.MODE_PRIVATE);

        String token = user_preferences.getString("X-Token", "null");

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
                intent =  new Intent(this, CarrinhoActivity.class);

                this.startActivity(intent);
                break;

            case R.id.menu_pedidos:
                fragment = new ListaPedidosFragment();
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
}
