package com.exercicio05;

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


public class RandomizadorActivity extends AppCompatActivity {
    private EditText editTextWord;
    private Button buttonAddWord;
    private Button buttonPickWord;
    private RecyclerView recyclerViewWords;

    private TextView pickedWordTextView;
    private WordAdapter wordAdapter;
    private List<Word> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_randomizador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerViewWords), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextWord = findViewById(R.id.editTextWord);
        buttonAddWord = findViewById(R.id.buttonAddWord);
        buttonPickWord = findViewById(R.id.buttonPickWord);
        recyclerViewWords = findViewById(R.id.recyclerViewWords);
        pickedWordTextView = findViewById(R.id.pickedWordTextView);

        wordList = new ArrayList<>();
        wordAdapter = new WordAdapter(wordList);
        recyclerViewWords.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWords.setAdapter(wordAdapter);

        buttonAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWord();
            }
        });

        buttonPickWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickWord();
            }
        });
    }

    private void addWord() {
        String word = editTextWord.getText().toString().trim();
        if (!word.isEmpty()) {
            wordList.add(new Word(word));
            wordAdapter.notifyDataSetChanged();
            editTextWord.getText().clear();
        }
    }

    private void pickWord() {
        if (!wordList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(wordList.size());
            Word pickedWord = wordList.get(randomIndex);

            String message = "Sorteado: " + pickedWord.getWord();
            pickedWordTextView.setText(message);
        } else {
            pickedWordTextView.setText("Nenhum item para sortear.");
        }
    }

}