package com.bidbuzz.androidApp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.bidbuzz.androidApp.fragments.BidVBox;
import com.bidbuzz.utils.AuthUser;
import com.bidbuzz.utils.ReceiveBroadcast;
import com.bidbuzz.utils.VolleyService;
import com.bidbuzz.utils.interfaces.ServerCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.android.volley.VolleyLog.TAG;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ReceiveBroadcast bReceiver = null;
    Boolean bReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bReceiver = new ReceiveBroadcast(){
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String value =  intent.getExtras().getString("token");
                Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT).show();
                TextView txt = findViewById(R.id.text_view);
                txt.setText(value);
            }
        };
        LocalBroadcastManager.getInstance(AppController.getContext()).registerReceiver(bReceiver, new IntentFilter("com.bidbuzz.androidApp.MAIN"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        populateLiveBids();
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_login) {
            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            myIntent.putExtra("key", "value"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void populateLiveBids(){
        VolleyService mVolleyService = new VolleyService(new ServerCallback() {
            @Override
            public void onSuccess(String requestType, JSONObject response) {
                try{
                    Log.d(TAG, "Volley requester " + requestType);
                    Log.d(TAG, "Volley JSON post" + response);
                    FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    JSONArray results = response.getJSONArray("results");
                    int i=0;
                    for(i=0;i<results.length();i++){
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("aa",response);
                        bundle.putString("ii", Integer.toString(i));
                        BidVBox bid_v_box = new BidVBox();
                        bid_v_box.setArguments(bundle);
                        fragmentTransaction.add(R.id.live_bids_container ,bid_v_box);
                    }

                    fragmentTransaction.commit();
                    Toast.makeText(getApplicationContext(),response.toString(), Toast.LENGTH_SHORT).show();
                    AuthUser user = AuthUser.getInstance();
                    user.setToken(response.getString("token"));
                }catch(JSONException e){

                }

            }

            @Override
            public void onError(String requestType, VolleyError error) {
                Log.d(TAG, "Volley requester " + requestType);
                Log.d(TAG, error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Volley JSON post" + "That didn't work!");
            }
        },this);
        mVolleyService.getDataVolley("login", "https://bidbuzz.in/api/bid/?is_active=true&ordering=start_date_time");
    }
}
