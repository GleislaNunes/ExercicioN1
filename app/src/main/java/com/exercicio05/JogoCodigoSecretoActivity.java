package com.exercicio05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class JogoCodigoSecretoActivity extends AppCompatActivity {
    private TextView feedbackTextView;
    private EditText guessEditText;
    private Button guessButton;
    private RecyclerView secretosRecyclerView;
    private SecretoAdapter secretoAdapter;

    private List<Integer> secretCode;
    private List<Secreto> secretos;

    private int numSecretos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jogo_codigo_secreto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.secretosRecyclerView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        feedbackTextView = findViewById(R.id.feedbackTextView);
        guessEditText = findViewById(R.id.guessEditText);
        guessButton = findViewById(R.id.guessButton);
        secretosRecyclerView = findViewById(R.id.secretosRecyclerView);

        secretos = new ArrayList<>();
        secretoAdapter = new SecretoAdapter(secretos);
        secretosRecyclerView.setAdapter(secretoAdapter);
        secretosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        generateSecretCode();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void generateSecretCode() {
        secretCode = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            secretCode.add(random.nextInt(10));
        }
    }

    private void checkGuess() {
        String guessStr = guessEditText.getText().toString();
        if (guessStr.length() != 4) {
            feedbackTextView.setText("O palpite deve ser um número de 4 dígitos");
            return;
        }

        int guess = Integer.parseInt(guessStr);
        int[] guessDigits = new int[4];
        for (int i = 3; i >= 0; i--) {
            guessDigits[i] = guess % 10;
            guess /= 10;
        }

        int correct = 0;
        int correctPosition = 0;

        for (int i = 0; i < 4; i++) {
            if (guessDigits[i] == secretCode.get(i)) {
                correctPosition++;
            } else if (secretCode.contains(guessDigits[i])) {
                correct++;
            }
        }

        String feedback = "Posição correta: " + correctPosition +
                "\nDígito correto: " + correct +
                "\nIncorreto: " + (4 - correctPosition - correct);
        feedbackTextView.setText(feedback);

        secretos.add(new Secreto(guessStr, feedback));
        secretoAdapter.notifyDataSetChanged();

        numSecretos++;

        if (correctPosition == 4) {
            // Usuário acertou o código secreto de primeira
            Intent successIntent = new Intent(JogoCodigoSecretoActivity.this, SuccessActivity.class);
            successIntent.putExtra("numTentativas", numSecretos);
            startActivity(successIntent);

            // Usuário ganhou
            String secretMessage = "Parabéns! Você descobriu o código secreto!";
            Toast.makeText(JogoCodigoSecretoActivity.this, secretMessage, Toast.LENGTH_SHORT).show();

        } else {

            if (numSecretos == 4) {
                TextView secretCodeTextView = findViewById(R.id.secretCodeTextView);
                secretCodeTextView.setText("Sequência escolhida: " + secretCode.toString());
                secretCodeTextView.setVisibility(View.VISIBLE);
            }

            if (numSecretos == 5) {
                TextView secretCodeTextView = findViewById(R.id.secretCodeTextView);
                secretCodeTextView.setVisibility(View.GONE);
                Toast.makeText(JogoCodigoSecretoActivity.this, "Número máximo de tentativas alcançado!", Toast.LENGTH_SHORT).show();
                resetGame();
            }
        }
    }

    private void resetGame() {
        secretos.clear();
        secretoAdapter.notifyDataSetChanged();

        generateSecretCode();

        numSecretos = 0;
        guessEditText.setText("");
        feedbackTextView.setText("");
    }
}