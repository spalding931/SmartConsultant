package esiea.org.app.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

import esiea.org.app.Database.DatabaseHandler;
import esiea.org.app.Model.Login;
import esiea.org.app.R;
import esiea.org.app.Security.Encrypt;

public class SignInActivity extends Activity {

    DatabaseHandler db;
    EditText usernameEdit;
    EditText passEdit;
    TextView errorText;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        db = new DatabaseHandler(getApplicationContext());
        usernameEdit = (EditText) findViewById(R.id.usernameValue);
        passEdit = (EditText) findViewById(R.id.passValue);
        errorText = (TextView) findViewById(R.id.labelError);


       /* ImageView icon = new ImageView(this); // Create an icon

        icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_email_black_18dp));

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();
        actionButton.setBackgroundColor(getResources().getColor(R.color.red));
        actionButton

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this).attachTo(actionButton).build();*/
    }

    public void submitSignIn(View v)
    {
        String username = usernameEdit.getText().toString();
        String password = passEdit.getText().toString();
        Login u = isFound(username,password);
        if(u!=null)
        {
            Intent intent;
            if(u.getType() == 0) // Client
            {
                intent = new Intent(this,ClientActivity.class);
                intent.putExtra("id",u.getId());
            }
            else
            {
               intent = new Intent(this,ConsultantActivity.class);
                intent.putExtra("id",u.getId());
            }
            startActivity(intent);
            finish();
        }
        else
        {
            errorText.setText(R.string.error_connection);
            passEdit.setText("");
        }
    }

    public void goToSignUp(View v)
    {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public Login isFound(String username, String password)
    {
        List <Login> loginList = db.getAllUsers();
        Iterator it = loginList.iterator();
        password = Encrypt.md5Encrypt(password);
        while (it.hasNext())
        {
            Login u = (Login) it.next();
            if (u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }
}
