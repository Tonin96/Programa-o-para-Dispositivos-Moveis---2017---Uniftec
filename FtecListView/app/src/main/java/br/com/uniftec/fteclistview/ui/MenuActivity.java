package br.com.uniftec.fteclistview.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.uniftec.fteclistview.R;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        drawerLayout = (DrawerLayout)findViewById(R.id.main_drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, new ListaFilmesFragment());
        transaction.commit();
        */
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

        if(item.getItemId() == R.id.menu_filmes) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container, new ListaFilmesFragment());
            transaction.commit();

        } else if(item.getItemId() == R.id.menu_musicas) {

        }

        return false;
    }
}
