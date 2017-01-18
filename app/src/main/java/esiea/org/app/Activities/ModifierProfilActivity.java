package esiea.org.app.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.Competence;
import esiea.org.app.Model.Profile;
import esiea.org.app.R;

public class ModifierProfilActivity extends AppCompatActivity {

    private EditText firstname ;
    private EditText lastname ;
    private EditText email ;
    private EditText job ;
    private EditText city ;
    private EditText age ;
    private Spinner listComptences ;
    private Switch isFilled;
    private static final String [] listValues = {"Java JEE","Mobile",".NET","Full Stack","Front End"};
    //private EditText competence ;

    private Profile p ;
    DatabaseHandler db;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_profil);

        db = new DatabaseHandler(getApplicationContext());
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        firstname =(EditText)findViewById(R.id.firstname);
        lastname =(EditText)findViewById(R.id.lastName);
        email =(EditText)findViewById(R.id.email);
        job =(EditText)findViewById(R.id.job);
        city =(EditText)findViewById(R.id.city);
        age =(EditText)findViewById(R.id.age);
        listComptences = (Spinner) findViewById(R.id.listCompetences);
        isFilled = (Switch) findViewById(R.id.show);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listValues);
        listComptences.setAdapter(adapter);

        p = db.getProfileById(id);

        if(p.getFirstname()!=null)
            firstname.setText(p.getFirstname());
        if(p.getLastname()!=null)
            lastname.setText(p.getLastname());
        if(p.getCity()!=null)
            city.setText(p.getCity());
        if(p.getJob()!=null)
            job.setText(p.getJob());
        if(p.getEmail()!=null)
            email.setText(p.getEmail());
        age.setText(p.getAge()+"");
        isFilled.setChecked(p.getIsFilled().contains("YES")? true : false);



        //competence =(EditText)findViewById(R.id.competence);
        //p.set(firstname.getText().toString());

    }

    public void modifier(View v)
    {
        String competence  = listComptences.getSelectedItem().toString();
        p= new Profile();
        p.setId(id);
        p.setFirstname(firstname.getText().toString());
        p.setLastname(lastname.getText().toString());
        p.setEmail(email.getText().toString());
        p.setJob(job.getText().toString());
        p.setCity(city.getText().toString());
        p.setAge(Integer.parseInt(age.getText().toString()));
        p.setCompetence(competence);
        p.setIsFilled(isFilled.isChecked()?"YES":"NO");
        db.updateProfile(p);
        finish();
        Intent intent = new Intent(this, ConsultantActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

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
                new AlertDialog.Builder(ModifierProfilActivity.this)
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
