package arectangle.rectanglesinside.rectanglesinsidearectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AnswerView extends View {
    // If you want to know what these one-letter variables mean, consult MainActivity lines 16-30
    private float C, B, L, W, c;
    private double x, // Is in radians
            O, E, T, a;
    private Paint paint;
    private int orange = Color.rgb(255, 127, 0);

    public AnswerView(Context context) {
        super(context);
        init();
    }
    public AnswerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public AnswerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextAlign(Paint.Align.CENTER);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        // This setTextSize command can't be moved to the constructor because it dependent on L, which is not defined before the constructor is run
        // The text size should never change after this
        paint.setTextSize(L / 15);
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(L / 200);
        canvas.drawPaint(paint); // Makes a dark gray background

        // Draw stuff on upper canvas
        canvas.save();

        // Do some translating and scaling
        canvas.translate(Screen.width / 4f, Screen.height / 4f);
        canvas.scale(Screen.width / (2 * L), Screen.width / (2 * L));

        drawCanvas(canvas); // Draw canvas and canvas measurements
        canvas.translate(L - B, 0); // Do some more transformations
        drawPiecesAndMeasurements(canvas); // Draw the piece(s) and show measurements of the length that multiple pieces take up
        drawAngleMeasurement(canvas); // Draw the angle & angle text
        drawOffsetMeasurement(canvas); // Draw the piece offset measurement

        canvas.restore();

        // Draw stuff on lower canvas
        canvas.save();

        // Do some translating and scaling
        canvas.translate(Screen.width / 4f, Screen.height * 3f / 4f);
        canvas.scale(Screen.width / (2 * L), Screen.width / (2 * L));
        drawCanvas(canvas);
        drawCanvasMeasurements(canvas);
        drawSinglePieceWithMeasurements(canvas);

        canvas.restore();

        super.onDraw(canvas);
    }
    void draw(float C, float B, float L, float W, float c, double x, double E, double O, double T, double a) {
        this.C = C;
        this.B = B;
        this.L = L;
        this.W = W;
        this.c = c;
        this.x = x;
        this.E = E;
        this.O = O;
        this.T = T;
        this.a = a;
        invalidate();
        requestLayout();
    }
    private void drawPiecesAndMeasurements(Canvas canvas) {
        paint.setColor(orange);
        int i = 0; // This is a while loop so that the variable "i" can be used later
        while(T + E * i <= L) {
            canvas.save();
            canvas.translate((float) (-E * i), 0);
            canvas.save();
            canvas.rotate((float) Math.toDegrees(x));
            canvas.drawRect(0, 0, C, c, paint);
            canvas.restore();

            // The vertical line of the measurement between the leftmost edge of a piece and the rightmost side of the canvas
            canvas.drawLine((float) -a, (float) (-a * i * 1.25 - a * 0.5), (float) -a, (float) (-a * i * 1.25 - a * 2), paint);

            canvas.translate(0, (float) (-a * i * 1.25 - a * 1.25)); // Represents the vertical level at which the horizontal line of the measurement is drawn
            // The horizontal line of the measurement
            canvas.drawLine((float) -a, 0, (float) (B + (E * i)), 0, paint);

            // The measurement text of the measurement
            drawTextAndRect(String.valueOf(Math.round((a + B + (E * i)) * 100d) / 100d), (float) ((-a + B + (E * i)) / 2), 0, canvas);

            canvas.restore();

            i ++;
        }
        canvas.drawLine(B, (float) (-a * 0.5), B, (float) (-a * (i - 1) * 1.25 - a * 2), paint); // Line going up vertically from upper right corner of canvas
    }
    private void drawCanvas(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, L, W, paint);
    }
    private void drawCanvasMeasurements(Canvas canvas) {
        // Vertical measurement of width
        paint.setColor(orange);
        canvas.drawLine((float) (L * -0.05), 0, (float) (L * -0.2), 0, paint);
        canvas.drawLine((float) (L * -0.05), W, (float) (L * -0.2), W, paint);
        float horizontalMidpoint = (float) (L * -0.125);
        canvas.drawLine(horizontalMidpoint, 0, horizontalMidpoint, W, paint);

        // Rotate so as to show text properly
        canvas.save();
        canvas.translate(horizontalMidpoint, W / 2);

        // For some WEIRD reason,
        // if I rotate the canvas by more than 90 degrees or less than -90 degrees,
        // the text gets super blurry.
        // I can't explain it at all.
        // As soon as it gets to 90 it gets blurry.
        // That's why I put 89.999 instead of 90.
        // Floats are accurate to 6 or 7 digits, so don't add too many nines.
        canvas.rotate(89.999f);

        drawTextAndRect(String.valueOf(W), 0, 0, canvas);
        canvas.restore();

        // Horizontal measurement of length
        canvas.drawLine(0, (float) (-L * 0.05), 0, (float) (-L * 0.2), paint);
        canvas.drawLine(L, (float) (-L * 0.05), L, (float) (-L * 0.2), paint);
        float verticalMidpoint = (float) (-L * 0.125);
        canvas.drawLine(0, verticalMidpoint, L, verticalMidpoint, paint);
        drawTextAndRect(String.valueOf(L), L / 2, verticalMidpoint, canvas);
    }
    private void drawSinglePieceWithMeasurements(Canvas canvas) {
        canvas.save();
        canvas.translate(L - B, 0);
        canvas.rotate((float) Math.toDegrees(x));
        paint.setColor(orange);
        canvas.drawRect(0, 0, C, c, paint);

        // Draw lines for measurement of longer dimension
        canvas.drawLine(0, c, 0, (float) (c + L * 0.15), paint); // Upper/left line
        canvas.drawLine(C, c, C, (float) (c + L * 0.15), paint);// Lower/right line
        float midPoint = (float) (c + L * 0.075);
        canvas.drawLine(0, midPoint, C, midPoint, paint); // Line connecting the other two

        // Draw text and white rect
        canvas.save();
        canvas.translate(C / 2, midPoint);
        paint.setColor(Color.WHITE);
        canvas.drawRect(-paint.measureText(String.valueOf(C)) * 3f / 4f, -paint.getTextSize() / 2f, paint.measureText(String.valueOf(C)) * 3f / 4f, paint.getTextSize() / 2f, paint);
        paint.setColor(orange);
        canvas.translate(0, paint.getTextSize() / 3f);
        canvas.drawText(String.valueOf(C), 0, 0, paint);
        canvas.restore();

        // Draw lines for measurement of shorter dimension
        paint.setColor(orange);
        canvas.drawLine(C, 0, (float) (C + L * 0.2), 0, paint);
        canvas.drawLine(C, c, (float) (C + L * 0.2), c, paint);
        midPoint = C + L * 0.1f;
        canvas.drawLine(midPoint, 0, midPoint, c, paint);

        canvas.save();
        canvas.translate(midPoint, (float) (c / 2d));
        canvas.rotate(-90f);
        drawTextAndRect(String.valueOf(c), 0, 0, canvas);
        canvas.restore();

        canvas.restore();
    }
    private void drawAngleMeasurement(Canvas canvas) {
        float arcRad = B / 6;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(arcRad / 6);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawArc(-arcRad, -arcRad, arcRad, arcRad, 0, (float) Math.toDegrees(x), false, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(String.valueOf(Math.round(Math.toDegrees(x) * 100d) / 100d) + "°", (float) (Math.cos(x / 2) * arcRad * 1.5), (float) (Math.sin(x / 2) * arcRad * 1.5 + paint.getTextSize() * 2 / 3), paint);
        paint.setTextAlign(Paint.Align.CENTER);
    }
    private void drawOffsetMeasurement(Canvas canvas) {
        paint.setStrokeWidth(L / 200);
        canvas.save();
        canvas.translate((float) (B - a), B);
        canvas.rotate((float) Math.toDegrees(x));
        canvas.translate(0, c);
        canvas.drawLine(0, 0, 0, c, paint);
        canvas.drawLine((float) -O, (float) 0, (float) -O, c, paint);
        canvas.drawLine((float) -O, c / 2, 0, c / 2, paint);
        drawTextAndRect(String.valueOf(Math.round(O * 100d) / 100d), (float) (-O / 2), c / 2, canvas);
        canvas.restore();
    }
    private void drawTextAndRect(String text, float x, float y, Canvas canvas) {
        canvas.save();
        canvas.translate(x, y);
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(-paint.measureText(text) * 3f / 4f, -paint.getTextSize() / 2f, paint.measureText(text) * 3f / 4f, paint.getTextSize() / 2f, paint);
        paint.setColor(orange);
        canvas.translate(0, paint.getTextSize() / 3f);

        paint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText(text, 0 - paint.measureText(text) / 2, 0, paint);
        canvas.restore();
    }
}
