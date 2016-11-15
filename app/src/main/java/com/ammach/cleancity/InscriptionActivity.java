package com.ammach.cleancity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
    }

    public void createCompte(View view) {
        Toast.makeText(InscriptionActivity.this, "khdama", Toast.LENGTH_SHORT).show();
    }
}
