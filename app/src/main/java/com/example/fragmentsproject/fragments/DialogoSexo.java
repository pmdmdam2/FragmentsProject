package com.example.fragmentsproject.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.fragmentsproject.R;
import com.example.fragmentsproject.interfaces.RespuestaDialogoSexo;

public class DialogoSexo extends DialogFragment {
    private RespuestaDialogoSexo respuesta;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return showListItemsDialog();
    }
    private Dialog showListItemsDialog() {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.selecciona_color)
                .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //el parámetro which contiene el índice
                        //del elemento seleccionado
                        Toast.makeText(getActivity().getApplicationContext(),
                                getResources().getStringArray(R.array.colors_array)[which],
                                Toast.LENGTH_LONG).show();
                    }
                });
        return builder.create();
    }

    private Dialog showSexDialog() {
        this.respuesta = (RespuestaDialogoSexo) getActivity();
        //usamos builder para construir el cuadro de diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //ponemos el título
        builder.setTitle(R.string.pregunta_importante);
        //ponemos la pregunta en el mensaje
        builder.setMessage(R.string.pregunta_sexo);

        //ponemos el comportamiento del botón del sí
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                respuesta.onRespuesta("Chica");
            }
        });
        //ponemos el comportamiento del botón del no
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                respuesta.onRespuesta("Chico");
            }
        });
        return builder.create();
    }
}
