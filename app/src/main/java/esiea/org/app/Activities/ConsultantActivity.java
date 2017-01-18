package esiea.org.app.Activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import android.view.View;


import org.w3c.dom.Text;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.Profile;
import esiea.org.app.R;

public class ConsultantActivity extends AppCompatActivity {

    DatabaseHandler db;
    Profile profileConsultant;
    private TextView firstname;
    private TextView lastname;
    private TextView age;
    private TextView city;
    private TextView job;
    private TextView email;
    private TextView competence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant);
        db = new DatabaseHandler(getApplicationContext());
        Intent intent = getIntent();
        int id= intent.getIntExtra("id",0);
        profileConsultant = db.getProfileById(id);

        firstname = (TextView) findViewById(R.id.firstname);
        lastname = (TextView) findViewById(R.id.lastname);
        email = (TextView) findViewById(R.id.email);
        age = (TextView) findViewById(R.id.age);
        city = (TextView) findViewById(R.id.city);
        job = (TextView) findViewById(R.id.job);
        competence = (TextView) findViewById(R.id.competence);


        if(profileConsultant.getFirstname()!=null)
            firstname.setText(profileConsultant.getFirstname());
        if(profileConsultant.getLastname()!=null)
            lastname.setText(profileConsultant.getLastname());
        if(profileConsultant.getCity()!=null)
            city.setText(profileConsultant.getCity());
        if(profileConsultant.getJob()!=null)
            job.setText(profileConsultant.getJob());
        if(profileConsultant.getEmail()!=null)
            email.setText(profileConsultant.getEmail());
        age.setText(profileConsultant.getAge()+"");
        if(profileConsultant.getCompetence()!=null)
            competence.setText(profileConsultant.getCompetence());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(profileConsultant.getIsFilled()=="YES")
        {
            firstname.setText(profileConsultant.getFirstname());
            lastname.setText(profileConsultant.getLastname());
            age.setText(profileConsultant.getAge());
            city.setText(profileConsultant.getCity());
            job.setText(profileConsultant.getJob());
        }
    }

    public void modifier(View view){
        Intent intent = new Intent(this,ModifierProfilActivity.class);
        intent.putExtra("id",profileConsultant.getId());
        startActivity(intent);
        finish();
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
                new AlertDialog.Builder(ConsultantActivity.this)
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
