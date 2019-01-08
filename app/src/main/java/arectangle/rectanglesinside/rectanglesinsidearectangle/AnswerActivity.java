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
        answerView.draw();
    }
}
