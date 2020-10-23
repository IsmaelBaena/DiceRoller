package com.itb.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView dice1,dice2;
    Button rollButton, restartButton;
    static final int[] dadoResultado = {R.drawable.dice_1, R.drawable.dice_2, R.drawable.dice_3, R.drawable.dice_4, R.drawable.dice_5, R.drawable.dice_6};
    int diceRes1, diceRes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = findViewById(R.id.dice_1);
        dice2 = findViewById(R.id.dice_2);
        rollButton = findViewById(R.id.roll_button);
        restartButton = findViewById(R.id.restart_button);

        rollButton.setOnClickListener(this);
        restartButton.setOnClickListener(this);
        dice1.setOnClickListener(this);
        dice2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.roll_button:
                throwDice(dice1);
                throwDice(dice2);
                break;
            case R.id.restart_button:
                restartDice();
                break;
            case R.id.dice_1:
                throwDice(dice1);
                break;
            case R.id.dice_2:
                throwDice(dice2);
                break;
        }
    }

    public void throwDice(ImageView dice) {
        int num = (int)(Math.random() * 6);
        Toast.makeText(MainActivity.this, "The dice has been rolled", Toast.LENGTH_SHORT).show();
        rollButton.setText(R.string.dice_rolled_text);
        dice.setImageResource(dadoResultado[num]);
        dice.setVisibility(View.VISIBLE);
        if (dice.getId() == dice1.getId()) diceRes1 = num;
        if (dice.getId() == dice2.getId()) diceRes2 = num;
        isJackpot();
    }

    public void restartDice() {
        dice1.setVisibility(View.INVISIBLE);
        dice2.setVisibility(View.INVISIBLE);
        Toast.makeText(MainActivity.this, "App Restarted", Toast.LENGTH_SHORT).show();
    }

    public void isJackpot() {
        if (diceRes1 == 5 && diceRes2 == 5) {
            Toast mensaje = Toast.makeText(MainActivity.this, "JACKPOT!!!!!!!", Toast.LENGTH_SHORT);
            mensaje.setGravity(Gravity.TOP, 0, 0);
            mensaje.show();
        }
    }
}