package com.maromodasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class BuscaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        Button btnIrParaEdicao = findViewById(R.id.btnIrParaEdicao);
        Button btnVoltar = findViewById(R.id.btnVoltarBusca);

        btnIrParaEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuscaActivity.this, EdicaoActivity.class);
                startActivity(intent);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a tela atual e volta para a anterior
            }
        });
    }
}
