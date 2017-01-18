package esiea.org.app.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.Login;
import esiea.org.app.R;

public class SignUpActivity extends Activity {

    DatabaseHandler db;
    EditText nameView ;
    EditText usernameView;
    EditText passwordView;
    RadioButton clientView;
    RadioButton consultantView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //connection à la base

        db = new DatabaseHandler(getApplicationContext());
        usernameView = (EditText) findViewById(R.id.usernameValue);
        passwordView = (EditText) findViewById(R.id.passValue);
        clientView = (RadioButton) findViewById(R.id.radioCLient);
        consultantView = (RadioButton) findViewById(R.id.radioCOnsultant);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void submitSignUp(View v)
    {

        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();
        int profil = clientView.isChecked() ? 0 : 1;

        Login login = new Login(username,password,profil);
        db.createUser(login);
        Toast.makeText(this,R.string.registration_success,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
        finish();
    }


}
