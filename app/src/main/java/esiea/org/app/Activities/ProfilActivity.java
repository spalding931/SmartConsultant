package esiea.org.app.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.Profile;
import esiea.org.app.R;

public class ProfilActivity extends AppCompatActivity {

    private DatabaseHandler db ;
    private Profile p;
    private TextView name;
    private TextView age;
    private TextView city;
    private TextView job;
    private TextView competence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseHandler(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Intent intent = getIntent();
        int id= intent.getIntExtra("id",0);
        p = db.getProfileById(id);

        name = (TextView) findViewById(R.id.name);
        age = (TextView) findViewById(R.id.agevalue);
        city = (TextView) findViewById(R.id.city);
        job = (TextView) findViewById(R.id.job);
        competence = (TextView) findViewById(R.id.competence);

        name.setText(p.getFirstname()+" "+p.getLastname());
        age.setText(p.getAge()+"");
        city.setText(p.getCity());
        job.setText(p.getJob());
        competence.setText(p.getCompetence());
    }

    public void contact(View v)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("mailto:?to="+p.getEmail()));
        startActivity(Intent.createChooser(intent, "Send via..."));
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
                new AlertDialog.Builder(ProfilActivity.this)
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
}
