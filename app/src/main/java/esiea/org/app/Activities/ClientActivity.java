package esiea.org.app.Activities;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.Login;
import esiea.org.app.Adapter.ListAdapter;
import esiea.org.app.Model.Profile;
import esiea.org.app.R;
import esiea.org.app.Service.DownloadSourceService;

public class ClientActivity extends AppCompatActivity implements OnRecyclerClickListener {

    private RecyclerView listView;
    private ListAdapter adapter;
    private List<Profile> profileList;
    private DatabaseHandler db;
    private LinearLayoutManager mLayoutManager;
    private Profile clientProfile;

    public class MyReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP ="com.myapp.intent.action.TEXT_TO_DISPLAY";

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(),"Le telechargement est terminé",Toast.LENGTH_SHORT).show();
            String text = intent.getStringExtra(DownloadSourceService.SOURCE_URL);
            // send text to display
            db = new DatabaseHandler(getApplicationContext());
            addData(db,text);
            db.close();

        }
    }

    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        db = new DatabaseHandler(getApplicationContext());


        String url = "https://api.myjson.com/bins/4oi1q";

        // on initialise notre broadcast
        receiver = new MyReceiver();
        // on lance le service
        Intent msgIntent = new Intent(this, DownloadSourceService.class);
        msgIntent.putExtra(DownloadSourceService.URL,url);
        startService(msgIntent);


        Intent intent = getIntent();
        int id= intent.getIntExtra("id",0);
       // clientProfile = db.getProfileById(id);

        // instantiate
        listView = (RecyclerView) findViewById(R.id.list);
        profileList = new ArrayList<Profile>();
        profileList = db.getConsultants();


        Iterator<Profile> it = profileList.iterator();
        while(it.hasNext())
        {
            Profile profileConsultant = it.next();
            if(profileConsultant.getIsFilled().equals("NO"))
                it.remove();
        }
        listView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);

        adapter = new ListAdapter(profileList,this);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ClientActivity.this, profileList.get(i).getFirstname(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),ProfilActivity.class);
                intent.putExtra("user", profileList.get(i).getId());
                startActivity(intent);

            }
        });*/


    }

    public void addData(final DatabaseHandler db, String txt) {
                        try {
                            JSONArray json = new JSONArray(txt);

                            Login login ;
                            Profile profile = new Profile();
                            for(int i=0;i<json.length();i++)
                            {
                                JSONObject obj = json.getJSONObject(i);
                                //remplire les donneés

                                login = new Login(obj.getString("username"),obj.getString("password"),obj.getInt("profil"));
                                profile.setId(obj.getInt("id"));
                                profile.setFirstname(obj.getString("firstname"));
                                profile.setLastname(obj.getString("lastname"));
                                profile.setEmail(obj.getString("email"));
                                profile.setAge(obj.getInt("age"));
                                profile.setCity(obj.getString("city"));
                                profile.setJob(obj.getString("job"));
                                profile.setIsFilled("YES");
                                login.setProfile(profile);
                                login.setId(profile.getId());
                                db.createUser(login);

                            }

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                new AlertDialog.Builder(ClientActivity.this)
                        .setTitle("Déconnection")
                        .setMessage("voulez vous vous déconnecter ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
                return true;

            case R.id.java:
                // Login chose the "Settings" item, show the app settings UI...
                return true ;

            case R.id.cSharp:
                // Login chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.cpp:
                // Login chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.c:
                // Login chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.python:
                // Login chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // on déclare notre Broadcast Receiver
        IntentFilter filter = new IntentFilter(MyReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause ();
        // on désenregistre notre broadcast
        unregisterReceiver(receiver);
    }

    @Override
    public void onRecyclerClickListener(int idProfil) {
        Intent intent = new Intent(getApplicationContext(),ProfilActivity.class);
        intent.putExtra("id", idProfil);
        startActivity(intent);
    }
}




















