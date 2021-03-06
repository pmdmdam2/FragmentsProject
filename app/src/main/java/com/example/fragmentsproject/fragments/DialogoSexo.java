package com.example.fragmentsproject.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.fragmentsproject.R;
import com.example.fragmentsproject.activities.MainActivity;
import com.example.fragmentsproject.interfaces.RespuestaDialogoSexo;

import java.util.ArrayList;

/**
 * Cuadros de diálogo con distintos niveles de personalización
 * @author Rafa
 * @version 1.0
 */
public class DialogoSexo extends DialogFragment {
    public static final int DEFAULT_TYPE=0;
    public static final int CUSTOM_TYPE=1;
    public static final int LIST_TYPE=2;
    public static final int MULTIPLE_TYPE=3;
    private int type;
    private RespuestaDialogoSexo respuesta;
    private boolean[] selected;
    public DialogoSexo(int type){
        this.type = type;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        this.selected = ((MainActivity) getActivity()).getChecked();
        switch(this.type){
            case DEFAULT_TYPE:
                return showSexDialog();
            case CUSTOM_TYPE:
                return showCustomDialog();
            case LIST_TYPE:
                return showListItemsDialog();
            case MULTIPLE_TYPE:
                return showMultipleOptionsDialog();
            default:
                return this.showSexDialog();
        }
    }

    private Dialog showCustomDialog() {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(getActivity());
        //se obtiene el inflador de interfaces
        LayoutInflater inflater =
                getActivity().getLayoutInflater();
        // se infla la interfaz del cuadro de diálogo y se
        // asigna null a la vista padre del layout porque la
        // misma sería el propio cuadro de diálogo

        builder.
                setView(inflater.
                        inflate(R.layout.dialog_signin, null))
                // se añaden los botones de acción
                .setPositiveButton(R.string.signin, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                //aquí el código necesario para la
                                //aprobar el login
                            }
                        })
                .setNegativeButton(R.string.cancel, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface
                                                        dialog, int id) {
                            //se cancela el cuadro de diálogo
                                DialogoSexo.this.
                                        getDialog().cancel();
                            }
                        });
        return builder.create();
    }

    private Dialog showMultipleOptionsDialog() {
        ArrayList mSelectedItems = new ArrayList();

        //elementos de la lista seleccionados
        AlertDialog.Builder builder = new
                AlertDialog.Builder(getActivity());
        //se establece el título del cuadro de diálogo
        builder.setTitle(R.string.elige_ingrediente)
                // se asigna la lista de elementos que se pueden
                // seleccionar (null para ninguno),
                // y se asocia el método de evento que recibirá el
                //callbacks cuando los elementos sean seleccionados
                .setMultiChoiceItems(R.array.toppings, selected,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // se agrega el elemento elegido a
                                    // la lista
                                    mSelectedItems.add(which);
                                } else if
                                (mSelectedItems.contains(which)) {
                                    // se elimina el elemento no
                                    // elegido de la lista
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                                selected[which] = isChecked;
                                ((MainActivity) getActivity()).saveChecked(selected);
                            }
                        })
                // se definen los botones de acción
                .setPositiveButton(R.string.ok, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface
                                                        dialog, int id) {
                                //se tratan los elementos
                                //seleccionados
                            }
                        })
                .setNegativeButton(R.string.cancel, new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface
                                                        dialog, int id) {
                                //se descartan los elementos
                                //seleccionados
                            }
                        });
        return builder.create();
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
