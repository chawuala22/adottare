package com.example.attore.ui.add;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attore.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddFragment extends Fragment {

    DatabaseReference root;
    EditText namepet,edadpet,descripet,namepersona,direccion,email,ubicacion;
    Button inscribirse;

    private AddViewModel mViewModel;


    public static AddFragment newInstance() {
        return new AddFragment();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);


        root= FirebaseDatabase.getInstance().getReference();
        //instanciando las edittext
        namepet = v.findViewById(R.id.namepet);
        edadpet= v.findViewById(R.id.agepet);
        descripet= v.findViewById(R.id.descpet);
        namepersona= v.findViewById(R.id.nombre);
        direccion= v.findViewById(R.id.direccion);
        email= v.findViewById(R.id.email);
        ubicacion= v.findViewById(R.id.ciudad);
        inscribirse= v.findViewById(R.id.btninscribirse);


        inscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre_mascota =namepet.getText().toString();
                String edad_mascota =edadpet.getText().toString();
                String desc_mascota =descripet.getText().toString();
                String nombre_persona =namepersona.getText().toString();
                String direccion_persona =direccion.getText().toString();
                String email_persona =email.getText().toString();
                String ubicacion_persona =ubicacion.getText().toString();


                load_data_register(nombre_mascota, edad_mascota, desc_mascota, nombre_persona, direccion_persona, email_persona, ubicacion_persona);

            }
        });



        return v;
    }

    private void load_data_register(String nombre_mascota, String edad_mascota, String desc_mascota, String nombre_persona, String direccion_persona, String email_persona, String ubicacion_persona) {
        Map<String, Object> datosinscripcion  = new HashMap<>();
        datosinscripcion.put("npet",nombre_mascota);
        datosinscripcion.put("apet",edad_mascota);
        datosinscripcion.put("descpet",desc_mascota);
        datosinscripcion.put("npers",nombre_persona);
        datosinscripcion.put("dirpers",direccion_persona);
        datosinscripcion.put("epers",email_persona);
        datosinscripcion.put("ubiper",ubicacion_persona);

        root.child("Inscripcion").push().setValue(datosinscripcion);

        Toast.makeText(getContext(),"Registro exitoso", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        // TODO: Use the ViewModel
    }

}