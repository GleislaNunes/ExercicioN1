package com.exercicio05;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaTelaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda_tela);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button codificadorButton = findViewById(R.id.codificadorButton);
        Button jogoButton = findViewById(R.id.jogoButton);
        Button codigoButton = findViewById(R.id.codigoButton);
        Button randomizadorButton = findViewById(R.id.randomizadorButton);

        codificadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaTelaActivity.this, CodificadorDecodificadorActivity.class));
            }
        });

        jogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaTelaActivity.this, JogoPedraPapelTesouraActivity.class));
            }
        });

        codigoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaTelaActivity.this, JogoCodigoSecretoActivity.class));
            }
        });

        randomizadorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SegundaTelaActivity.this, RandomizadorActivity.class));
            }
        });
    }
}