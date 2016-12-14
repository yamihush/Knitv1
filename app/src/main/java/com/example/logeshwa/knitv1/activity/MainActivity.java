package com.example.logeshwa.knitv1.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.logeshwa.knitv1.R;
import com.example.logeshwa.knitv1.fragment.companyfragment;
import com.example.logeshwa.knitv1.fragment.machinedetails;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnAddNewRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new Fragment();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_company) {

            fragment = new companyfragment();


            Toast.makeText(this,"Company Fragmnet",Toast.LENGTH_SHORT).show();

            //svinp.rest();

            // Handle the company  action

        } else if (id == R.id.nav_inward) {
            fragment = new machinedetails();

        } else if (id == R.id.nav_outward) {

        } else if (id == R.id.nav_billing) {

        } else if (id == R.id.nav_production) {

        } else if (id == R.id.nav_send) {

        }
       // Toast.makeText(this,"Came Out",Toast.LENGTH_SHORT).show();
        transaction.replace(R.id.fragmenthome,fragment);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

  /*  private void bindWidgetsWithEvent() {

        btnAddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddRecord();
            }
        });

    }

    private void onAddRecord() {

            Intent intent = new Intent(save_inpt, TableManipulationActivity.class);
            intent.putExtra(Constants.DML_TYPE, Constants.INSERT);
            startActivityForResult(intent, Constants.ADD_RECORD);
        }
*/


    /*public void floatingaddcompany(View v){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = new addCompanyFragment();
        transaction.replace(R.id.fragmenthome,fragment);
        transaction.commit();

        }*/


}
