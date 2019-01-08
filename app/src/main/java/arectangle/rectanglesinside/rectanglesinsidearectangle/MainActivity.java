package arectangle.rectanglesinside.rectanglesinsidearectangle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText pieceLength, pieceHeight, canvasLength, canvasHeight;
    private TextView errorText;
    // In this case, the outer rectangle is the canvas, and the inner rect is the piece
    // I may use hard brackets the way you would use parentheses in math, just to clarify complicated sentences.
    private double
            H, // Canvas height
            W, // Canvas width
            C, // Piece length
            c, // Piece height, AKA the hypotenuse of the triangle made by [the right side of the piece] and [the bottom right corner of the canvas]
            a, // Bottom side of the triangle made by [the right side of the piece] and [the bottom right corner of the canvas]
            b, // Right side of the triangle made by [the right side of the piece] and [the bottom right corner of the canvas]
            B, // Distance between the intersection of [canvas's top and piece's upper left corner] and [the upper right corner of the canvas]
            T, // Horizontal distance between [the bottom left corner of the piece] and [the upper right corner of the canvas]
            J, // Horizontal distance between piece's [upper left and bottom right] corners
            D, // The piece's length along the diagonal, AKA the distance between the piece's [upper left and bottom right] corners
            x, // The angle that the piece makes with the horizontal
            y, // The angle that the line along the piece's diagonal makes with the top and bottom of the piece
            z; // The angle that the line along the piece's diagonal makes with the top of the canvas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieceLength = findViewById(R.id.pieceLength);
        pieceHeight = findViewById(R.id.pieceHeight);
        canvasLength = findViewById(R.id.canvasLength);
        canvasHeight = findViewById(R.id.canvasHeight);
        errorText = findViewById(R.id.errorText);
    }
    public void onGoClick(View v) {
        errorText.setVisibility(View.INVISIBLE);

        C = Double.parseDouble(pieceLength.getText().toString());
        c = Double.parseDouble(pieceHeight.getText().toString());
        W = Double.parseDouble(canvasLength.getText().toString());
        H = Double.parseDouble(canvasHeight.getText().toString());

        calculateStuff();

        if(!checkValidValues()) {
            showAnswers();
        }
    }
    private boolean checkValidValues() {
        if(C <= 0 || c <= 0 || W <= 0 || H <= 0) {
            aboveZeroError();
            return true;
        } else if(C < H) {
            tooShortError();
            return true;
        } else if(T > W) {
            noneFitError();
            return true;
        }
        return false;
    }
    private void calculateStuff() {
        // Just define the stuff
        D = Math.sqrt(c*c + C*C); // Simple pythagorean theorem
        J = Math.sqrt(D*D - H*H); // Don't ya just love triangles
        y = Math.asin(c / D);
        z = Math.asin(H / D);
        x = z - y;
        a = c * Math.sin(x);
        b = c * Math.cos(x);
        B = J + a;
        T = B + a;
    }
    private void aboveZeroError() {
        errorText.setText("All values must be more than or equal to zero");
        errorText.setVisibility(View.VISIBLE);
    }
    private void tooShortError() {
        if(C < H) {
            errorText.setText("The piece length must be more than the canvas height");
            errorText.setVisibility(View.VISIBLE);
        }
    }
    private void noneFitError() {
        if(T > W) {
            errorText.setText("The piece cannot fit in the canvas at all");
            errorText.setVisibility(View.VISIBLE);
        }
    }
    private void showAnswers() {
        Intent intent = new Intent(getBaseContext(), AnswerActivity.class);
        intent.putExtra("C", C);
        intent.putExtra("B", B);
        intent.putExtra("c", c);
        intent.putExtra("H", H);
        intent.putExtra("W", W);
        intent.putExtra("x", x);
        startActivity(intent);
    }
}
