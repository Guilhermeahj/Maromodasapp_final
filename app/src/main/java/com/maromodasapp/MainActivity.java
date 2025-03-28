package com.maromodasapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editReference, editAddress;
    private Button btnSave;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao);

        editReference = findViewById(R.id.editReference);
        editAddress = findViewById(R.id.editAddress);
        btnSave = findViewById(R.id.btnSave);

        sharedPreferences = getSharedPreferences("StockReferences", MODE_PRIVATE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveReference();
            }
        });
    }

    private void saveReference() {
        String reference = editReference.getText().toString().trim();
        String address = editAddress.getText().toString().trim();

        if (reference.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        String existingReference = sharedPreferences.getString(address, null);

        if (existingReference != null) {
            new AlertDialog.Builder(this)
                .setTitle("Referência já cadastrada")
                .setMessage("A referência " + existingReference + " já está nesse local. Deseja substituir?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveToPreferences(reference, address);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
        } else {
            saveToPreferences(reference, address);
        }
    }

    private void saveToPreferences(String reference, String address) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(address, reference);
        editor.apply();
        Toast.makeText(this, "Referência salva com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
