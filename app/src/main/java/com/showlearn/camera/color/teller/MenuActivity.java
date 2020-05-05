package com.showlearn.camera.color.teller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private FirebaseAuth firebaseAuth;
    String[] languages = new String[]{"Türkçe", "İngilizce"};
    Spinner languageList;
    static String currentLanguage = "turkish";

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Database Bağlantısı
        firebaseAuth = FirebaseAuth.getInstance();

        // Bir spinner oluştur OnItemSelectedListener’ını tanımla
        languageList = (Spinner) findViewById(R.id.languageList);
        languageList.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,languages);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        languageList.setAdapter(arrayAdapter);
    }

    public void logOut(View view)
    {
        firebaseAuth.signOut();

        Intent intentToSignup = new Intent(MenuActivity.this,SignUpActivity.class);
        startActivity(intentToSignup);
        finish();
    }

    public void goActivity(View view)
    {
        Intent intentToColor = new Intent(MenuActivity.this,MainPermissionActivity.class);
        startActivity(intentToColor);
    }

    public void showInfo(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);

        alert.setTitle("Bilgi");
        alert.setMessage("Ekrana tek dokunmayla rengi söyleme\nEkrana çift dokunmayla flaşı açma/kapatma");

        alert.setPositiveButton("Tamam", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        alert.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        if(adapterView.getId() == R.id.languageList)
        {
            if(i == 0)
            {
                currentLanguage = "turkish";
            }

            else if(i == 1)
            {
                currentLanguage = "english";
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
