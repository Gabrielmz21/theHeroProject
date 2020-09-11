package com.example.theheroproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                buscarHeroe();
            }
        });
    }

    protected void buscarHeroe(){
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        if (editText.length()>=3){
            String nombreHeroe = editText.getText().toString();
            Intent intent = new Intent(this,Resultado.class);
            intent.putExtra("NOMBRE_HEROE",nombreHeroe);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Debe ingresar al menos 3 letras",Toast.LENGTH_LONG).show();
        }
    }
}