package com.example.fragmentsproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmentsproject.R;
import com.example.fragmentsproject.fragments.DialogoSexo;
import com.example.fragmentsproject.interfaces.RespuestaDialogoSexo;

public class MainActivity extends AppCompatActivity implements RespuestaDialogoSexo {
    private boolean[] checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.checked=new boolean[getResources().getStringArray(R.array.toppings).length];
    }
    public void mostrarDialogo(View view){
        DialogoSexo dialogoSexo = new DialogoSexo();
        dialogoSexo.show(getSupportFragmentManager(),"Diálogo sexo");
    }
    @Override
    public void onRespuesta(String respuesta) {
        Toast.makeText(this,respuesta,Toast.LENGTH_LONG).show();
    }
    public void saveChecked(boolean[] checked){
        this.checked = checked;
    }
    public boolean[] getChecked(){
        return this.checked;
    }
}