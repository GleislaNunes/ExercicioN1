package com.exercicio05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CodificadorDecodificadorActivity extends AppCompatActivity {
    EditText mensagemEditText;
    Button codificarButton, decodificarButton;
    TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_codificador_decodificador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mensagemEditText = findViewById(R.id.mensagemEditText);
        codificarButton = findViewById(R.id.codificarButton);
        decodificarButton = findViewById(R.id.decodificarButton);
        resultadoTextView = findViewById(R.id.resultadoTextView);

        codificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = mensagemEditText.getText().toString();
                String mensagemCodificada = codificar(mensagem, 12);
                resultadoTextView.setText(mensagemCodificada);
            }
        });

        decodificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagemCodificada = resultadoTextView.getText().toString();
                String mensagemDecodificada = decodificar(mensagemCodificada, 12);
                resultadoTextView.setText(mensagemDecodificada);
            }
        });
    }

    private String codificar(String texto, int deslocamento) {
        StringBuilder resultado = new StringBuilder();

        for (char ch : texto.toCharArray()) {
            if (Character.isLetter(ch)) {
                char deslocado = (char) (((ch - 'a' + deslocamento) % 26) + 'a');
                resultado.append(deslocado);
            } else {
                resultado.append(ch);
            }
        }

        return resultado.toString();
    }

    private String decodificar(String texto, int deslocamento) {
        return codificar(texto, 26 - deslocamento);
    }
}

