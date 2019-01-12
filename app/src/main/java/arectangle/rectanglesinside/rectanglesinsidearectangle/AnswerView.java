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
        // Do some translating and scaling
        canvas.save();
        canvas.translate(Screen.width / 4, Screen.height / 4);
        canvas.scale(Screen.width / (2 * W), Screen.width / (2 * W));

        // The text size should never change after this
        paint.setTextSize((float) (b / 2));
        paint.setTypeface(Typeface.MONOSPACE);

        // Draw the canvas
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, W, H, paint);

        // Do some more transformations
        canvas.save();
        canvas.translate(W - B, 0);

        // Draw the piece(s)
        paint.setColor(Color.rgb(255, 127, 0));
        paint.setTextAlign(Paint.Align.CENTER);
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

            double verticalHalfPoint = (float) (-a * i * 1.25 - a * 1.25); // Represents the vertical level at which the horizontal line of the measurement is drawn
            // The horizontal line of the measurement
            canvas.drawLine((float) -a, (float) verticalHalfPoint, (float) (B + (E * i)), (float) verticalHalfPoint, paint);

            // The measurement text of the measurement
            double horizontalHalfPoint = (float) ((-a + B + (E * i)) / 2);
            canvas.drawText(String.valueOf(Math.round((a + B + (E * i)) * 100d) / 100d), (float) horizontalHalfPoint, (float) (verticalHalfPoint + paint.getTextSize() / 3), paint);

            canvas.restore();

            i ++;
        }

        canvas.drawLine(B, 0, B, (float) (-a * (i - 1) * 1.25 - a * 2), paint);

        float arcRad = B / 6;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(arcRad / 6);
        canvas.drawArc(-arcRad, -arcRad, arcRad, arcRad, 0, (float) Math.toDegrees(x), false, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(String.valueOf(Math.round(Math.toDegrees(x) * 100d) / 100d) + "°", (float) (Math.cos(x / 2) * arcRad * 1.5), (float) (Math.sin(x / 2) * arcRad * 1.5 + paint.getTextSize() * 2 / 3), paint);

        // Reset everything
        canvas.restore();
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
}
