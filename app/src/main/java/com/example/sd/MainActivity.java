package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre, et_contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_contenido = (EditText) findViewById(R.id.et_contenido);
    }

    // metodo para boton guardar.
    public void Guardar(View view){
        String nombre = et_nombre.getText().toString();
        String contenido = et_contenido.getText().toString();

        try {
            //indicamos donde se encuentra la tarjeta SD
            File tarjetaSD = Environment.getExternalStorageDirectory();
            Toast.makeText(this, tarjetaSD.getPath(), Toast.LENGTH_SHORT).show();
            // creamos el archivo con el nombre que nos indica el usuario, y el PATH de la tarjeta SD.
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);
            // ahora hay que abrir el archivo.
            OutputStreamWriter crearArchivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));
            // guardamos los datos en el archivo
            crearArchivo.write(contenido);
            // limpiamos el buffer
            crearArchivo.flush();
            crearArchivo.close();

            Toast.makeText(this, "Se ha guardado correctamente", Toast.LENGTH_SHORT).show();
            et_nombre.setText("");
            et_contenido.setText("");

        }catch (IOException e){
            Toast.makeText(this, "No se puede guardar el archivo", Toast.LENGTH_LONG).show();
        }
    }
}