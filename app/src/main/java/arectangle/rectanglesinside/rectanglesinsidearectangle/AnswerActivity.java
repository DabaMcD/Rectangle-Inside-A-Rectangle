package arectangle.rectanglesinside.rectanglesinsidearectangle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class AnswerActivity extends AppCompatActivity {
    AnswerView answerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Screen.height = displayMetrics.heightPixels;
        Screen.width = displayMetrics.widthPixels;

        answerView = findViewById(R.id.answerView);
        answerView.draw(
                Float.parseFloat(getIntent().getStringExtra("C")),
                Float.parseFloat(getIntent().getStringExtra("B")),
                Float.parseFloat(getIntent().getStringExtra("H")),
                Float.parseFloat(getIntent().getStringExtra("W")),
                Float.parseFloat(getIntent().getStringExtra("c")),
                Float.parseFloat(getIntent().getStringExtra("x"))
        );
    }
}
