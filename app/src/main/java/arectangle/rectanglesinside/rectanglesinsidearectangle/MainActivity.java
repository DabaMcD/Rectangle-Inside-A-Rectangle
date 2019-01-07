package arectangle.rectanglesinside.rectanglesinsidearectangle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText pieceLength, pieceHeight, canvasLength, canvasHeight;
    // In this case, the outer rectangle is the canvas, and the inner rect is the piece
    // I may use hard brackets the way you would use parentheses in math, just to clarify complicated sentences.
    private double
            H, // Canvas height
            W, // Canvas width
            C, // Piece length
            c, // Piece height
            B, // Distance between the intersection of [canvas's top and piece's upper left corner] and [the upper right corner of the canvas]
            T, // Horizontal distance between [the bottom left corner of the piece] and [the upper right corner of the canvas]
            J, // Horizontal distance between piece's [upper left and bottom right] corners
            D, // The piece's length along the diagonal, AKA the distance between the piece's [upper left and bottom right] corners
            x, // The angle that the piece makes with the horizontal
            y, // The angle that the line along the piece's diagonal makes with the horizontal sides of the piece
            z; // Variables x and y combined

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
        C = Double.parseDouble(pieceLength.getText().toString());
        c = Double.parseDouble(pieceHeight.getText().toString());
        W = Double.parseDouble(canvasLength.getText().toString());
        H = Double.parseDouble(canvasHeight.getText().toString());

        checkValidValues();

        doStuff();
    }

    private void checkValidValues() {

    }

    private void doStuff() {

    }
}
