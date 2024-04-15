package com.exercicio05;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class JogoPedraPapelTesouraActivity extends AppCompatActivity {
    private ImageView playerChoiceImageView, computerChoiceImageView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jogo_pedra_papel_tesoura);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playerChoiceImageView = findViewById(R.id.playerChoiceImageView);
        computerChoiceImageView = findViewById(R.id.computerChoiceImageView);
        resultTextView = findViewById(R.id.resultTextView);

        Button rockButton = findViewById(R.id.rockButton);
        Button paperButton = findViewById(R.id.paperButton);
        Button scissorsButton = findViewById(R.id.scissorsButton);

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogar(1); // 1 representa a escolha da pedra
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogar(2); // 2 representa a escolha do papel
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jogar(3); // 3 representa a escolha da tesoura
            }
        });
    }

    private void jogar(int playerChoice) {
        int computerChoice = new Random().nextInt(3) + 1;


        playerChoiceImageView.setImageResource(getImageResource(playerChoice));
        computerChoiceImageView.setImageResource(getImageResource(computerChoice));

        exibirResultado(playerChoice, computerChoice);
    }

    private int getImageResource(int choice) {
        switch (choice) {
            case 1:
                return R.drawable.ic_rock;
            case 2:
                return R.drawable.ic_paper;
            case 3:
                return R.drawable.ic_scissors;
            default:
                return R.drawable.ic_question_mark;
        }
    }

    private void exibirResultado(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            resultTextView.setText(getString(R.string.draw));
        } else if ((playerChoice == 1 && computerChoice == 3) ||
                (playerChoice == 2 && computerChoice == 1) ||
                (playerChoice == 3 && computerChoice == 2)) {
            resultTextView.setText(getString(R.string.win));
        } else {
            resultTextView.setText(getString(R.string.loss));
        }

        resultTextView.setVisibility(View.VISIBLE);
    }
}

