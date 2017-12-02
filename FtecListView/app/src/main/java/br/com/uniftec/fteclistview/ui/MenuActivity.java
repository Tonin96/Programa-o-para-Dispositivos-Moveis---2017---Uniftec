package br.com.uniftec.fteclistview.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import br.com.uniftec.fteclistview.R;

public class MenuActivity extends AbstractActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_menu;
    }

    @Override
    protected void setupView() {
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        drawerLayout = (DrawerLayout)findViewById(R.id.main_drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        mudarContainerPrincipal(new ListaFilmesFragment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawers();

        Fragment fragment = null;

        switch (item.getItemId()) {

            case R.id.menu_filmes:
                fragment = new ListaFilmesFragment();
                break;

            case R.id.menu_musicas:
                fragment = new Fragment();
                break;

            case R.id.menu_mapa:
                mudarContainerPrincipal(new IntinerarioMapFragment());

                return true;

        }

        if(fragment != null) {
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

    private void mudarContainerPrincipal(android.app.Fragment fragment) {
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
    }
}
