package com.ammach.cleancity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ConnexionActivity extends AppCompatActivity {


    EditText editEmail;
    EditText editPasswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);


        getSupportActionBar().hide();
        initViews();
    }

    private void initViews() {

        editEmail= (EditText) findViewById(R.id.editEmail);
        editPasswd= (EditText) findViewById(R.id.editPasswd);
    }

    public void seConnecter(View view) {
        startActivity(new Intent(this,HomeActivity.class));
    }

    public void createCompte(View view) {
        startActivity(new Intent(this,InscriptionActivity.class));
    }
}
