package arectangle.rectanglesinside.rectanglesinsidearectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AnswerView extends View {
    private float C, B, H, W, c;
    private double x, O, E, T;
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

        // Draw the canvas
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, W, H, paint);

        // Do some more transformations
        canvas.save();
        canvas.translate(W - B, 0);

        // Draw the piece(s)
        for(int i = 0; T + E * i <= W; i ++) {
            canvas.save();
            canvas.translate((float) (-E * i), 0);
            canvas.rotate((float) Math.toDegrees(x));
            paint.setColor(Color.rgb(255, 127, 0));
            canvas.drawRect(0, 0, C, c, paint);
            canvas.restore();
        }

        // Reset everything
        canvas.restore();
        canvas.restore();

        super.onDraw(canvas);
    }
    void draw(float C, float B, float H, float W, float c, double x, double E, double O, double T) {
        this.C = C;
        this.B = B;
        this.H = H;
        this.W = W;
        this.c = c;
        this.x = x;
        this.E = E;
        this.O = O;
        this.T = T;
        invalidate();
        requestLayout();
    }
}
