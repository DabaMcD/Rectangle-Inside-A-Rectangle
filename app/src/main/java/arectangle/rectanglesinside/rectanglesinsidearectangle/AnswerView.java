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
    private float C, B, H, W, c;
    private double x, O, E, T, a, b;
    private Paint paint;
    private int orange = Color.rgb(255, 127, 0);

    public AnswerView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    public AnswerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    public AnswerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.DKGRAY);
        canvas.drawPaint(paint);

        // Do some translating and scaling
        canvas.save();
        canvas.translate(Screen.width / 4f, Screen.height / 4f);
        canvas.scale(Screen.width / (2 * W), Screen.width / (2 * W));

        // Draw canvas and canvas measurements
        drawCanvasAndMeasurements(canvas);

        // The text size should never change after this
        paint.setTextSize(c / 2);
        paint.setTypeface(Typeface.MONOSPACE);

        // Do some more transformations
        canvas.translate(W - B, 0);

        // Draw the piece(s) and show measurements of the length that multiple pieces take up
        drawPiecesAndPieceMeasurements(canvas);

        // Draw the angle & angle text
        drawAngleMeasurement(canvas);

        // Draw the piece offset measurement
        drawOffsetMeasurement(canvas);

        canvas.restore();

        super.onDraw(canvas);
    }
    void draw(float C, float B, float H, float W, float c, double x, double E, double O, double T, double a, double b) {
        this.C = C;
        this.B = B;
        this.H = H;
        this.W = W;
        this.c = c;
        this.x = x;
        this.E = E;
        this.O = O;
        this.T = T;
        this.a = a;
        this.b = b;
        invalidate();
        requestLayout();
    }
    private void drawPiecesAndPieceMeasurements(Canvas canvas) {
        paint.setColor(orange);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(B / 100);
        int i = 0; // This is a while loop so that the variable "i" can be used later
        while(T + E * i <= W) {
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
        canvas.drawLine(B, 0, B, (float) (-a * (i - 1) * 1.25 - a * 2), paint);
    }
    private void drawCanvasAndMeasurements(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, W, H, paint);
        paint.setColor(orange);
        canvas.drawLine((float) (W * 1.05), 0, (float) (W * 1.2), 0, paint);
    }
    private void drawAngleMeasurement(Canvas canvas) {
        float arcRad = B / 6;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(arcRad / 6);
        canvas.drawArc(-arcRad, -arcRad, arcRad, arcRad, 0, (float) Math.toDegrees(x), false, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(String.valueOf(Math.round(Math.toDegrees(x) * 100d) / 100d) + "°", (float) (Math.cos(x / 2) * arcRad * 1.5), (float) (Math.sin(x / 2) * arcRad * 1.5 + paint.getTextSize() * 2 / 3), paint);
    }
    private void drawOffsetMeasurement(Canvas canvas) {
        paint.setStrokeWidth(B / 100);
        paint.setTextAlign(Paint.Align.CENTER);
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
        canvas.drawRect(-paint.measureText(text) * 3 / 4, -paint.getTextSize() / 2, paint.measureText(text) * 3 / 4, paint.getTextSize() / 2, paint);
        paint.setColor(orange);
        canvas.translate(0, paint.getTextSize() / 3);
        canvas.drawText(text, 0, 0, paint);
        canvas.restore();
    }
}
