package arectangle.rectanglesinside.rectanglesinsidearectangle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText pieceLength, pieceWidth, canvasLength, canvasWidth;
    private TextView errorText;
    // In this case, the outer rectangle is the canvas, and the inner rect is the piece
    // I may use hard brackets the way you would use parentheses in math, just to clarify complicated sentences.
    private double
            L, // Canvas length AKA longer dimension
            W, // Canvas width AKA shorter dimension
            O, // The offset between two adjacent pieces in the dimension in which the pieces are oriented
            E, // The offset between two adjacent pieces in the horizontal dimension
            C, // Piece length
            c, // Piece height, AKA the hypotenuse of the triangle made by [the lower right side of the piece] and [the bottom right corner of the canvas]
            a, // Bottom side of the triangle made by [the lower right side of the piece] and [the bottom right corner of the canvas]
            b, // Right side of the triangle made by [the lower right side of the piece] and [the bottom right corner of the canvas]
            B, // Distance between the intersection of [canvas's top and piece's upper corner] and [the upper right corner of the canvas]
            T, // Horizontal distance between [the left corner of the piece] and [the upper right corner of the canvas]
            J, // Horizontal distance between piece's [upper left and bottom right] corners
            D, // The piece's length along the diagonal, AKA the distance between the piece's [upper and bottom] corners
            x, // The angle (radians) which the piece makes with the horizontal
            y, // The angle (radians) that the line along the piece's diagonal makes with the top and bottom of the piece
            z; // The angle (radians) that the line along the piece's diagonal makes with the top of the canvas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieceLength = findViewById(R.id.pieceLength);
        pieceWidth = findViewById(R.id.pieceWidth);
        canvasLength = findViewById(R.id.canvasLength);
        canvasWidth = findViewById(R.id.canvasWidth);
        errorText = findViewById(R.id.errorText);
    }
    public void onGoClick(View v) {
        errorText.setVisibility(View.INVISIBLE);

        if(pieceLength.getText().toString().equals("") || pieceWidth.getText().toString().equals("") || canvasLength.getText().toString().equals("") || canvasWidth.getText().toString().equals("")) {
            boxEmptyError();
            return;
        }
        C = Double.parseDouble(pieceLength.getText().toString());
        c = Double.parseDouble(pieceWidth.getText().toString());
        W = Double.parseDouble(canvasWidth.getText().toString());
        L = Double.parseDouble(canvasLength.getText().toString());

        calculateStuff();

        if(!checkValidValues()) {
            showAnswers();
        }
    }
    private boolean checkValidValues() {
        if(C <= 0 || c <= 0 || W <= 0 || L <= 0) {
            aboveZeroError();
            return true;
        } else if(Math.sqrt(C*C + c*c) < W) {
            tooShortError();
            return true;
        } else if(T > L) {
            noneFitError();
            return true;
        } else if(c > W) {
            tooTallError();
            return true;
        } else if(c > C || W > L) {
            wrongDimsError();
            return true;
        }
        return false;
    }
    private void calculateStuff() {
        // Just define the stuff
        D = Math.sqrt(c*c + C*C);
        J = Math.sqrt(D*D - W*W);
        y = Math.asin(c / D);
        z = Math.asin(W / D);
        x = z - y;
        a = c * Math.sin(x);
        b = c * Math.cos(x);
        B = J + a;
        T = B + a;
        E = c / Math.sin(x);
        O = c / Math.tan(x);
    }
    private void aboveZeroError() {
        errorText.setText(R.string.aboveZeroErrorMessage);
        errorText.setVisibility(View.VISIBLE);
    }
    private void tooShortError() {
        errorText.setText(R.string.tooShortErrorMessage);
        errorText.setVisibility(View.VISIBLE);
    }
    private void noneFitError() {
            errorText.setText(R.string.noneFitErrorMessage);
            errorText.setVisibility(View.VISIBLE);
    }
    private void boxEmptyError() {
        errorText.setText(R.string.boxEmptyErrorMessage);
        errorText.setVisibility(View.VISIBLE);
    }
    private void tooTallError() {
        errorText.setText(R.string.tooTallErrorMessage);
        errorText.setVisibility(View.VISIBLE);
    }
    private void wrongDimsError() {
        errorText.setText(R.string.wrongDimsErrorMessage);
        errorText.setVisibility(View.VISIBLE);
    }
    private void showAnswers() {
        Intent intent = new Intent(getBaseContext(), AnswerActivity.class);
        intent.putExtra("C", C);
        intent.putExtra("B", B);
        intent.putExtra("c", c);
        intent.putExtra("L", L);
        intent.putExtra("W", W);
        intent.putExtra("x", x);
        intent.putExtra("E", E);
        intent.putExtra("O", O);
        intent.putExtra("T", T);
        intent.putExtra("a", a);
        startActivity(intent);
    }
}
