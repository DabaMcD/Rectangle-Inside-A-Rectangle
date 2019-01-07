package arectangle.rectanglesinside.rectanglesinsidearectangle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText pieceLength, pieceHeight, canvasLength, canvasHeight;
    private double pl, ph, cl, ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieceLength = findViewById(R.id.pieceLength);
        pieceHeight = findViewById(R.id.pieceHeight);
        canvasLength = findViewById(R.id.canvasLength);
        canvasHeight = findViewById(R.id.canvasHeight);
    }

    public void onGoClick(View v) {
        pl = Double.parseDouble(pieceLength.getText().toString());
        ph = Double.parseDouble(pieceHeight.getText().toString());
        cl = Double.parseDouble(canvasLength.getText().toString());
        ch = Double.parseDouble(canvasHeight.getText().toString());
    }
}
