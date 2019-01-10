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
    private double x, O, E;
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
        canvas.save();
        canvas.translate(Screen.width / 4, Screen.height / 4);
        canvas.scale(Screen.width / (2 * W), Screen.width / (2 * W));
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, W, H, paint);
        canvas.save();
        canvas.translate(W - B, 0);
        canvas.rotate((float) Math.toDegrees(x));
        paint.setColor(Color.rgb(255, 127, 0));
        canvas.drawRect(0, 0, C, c, paint);
        canvas.restore();
        canvas.restore();

        super.onDraw(canvas);
    }
    void draw(float C, float B, float H, float W, float c, double x, double E, double O) {
        this.C = C;
        this.B = B;
        this.H = H;
        this.W = W;
        this.c = c;
        this.x = x;
        this.E = E;
        this.O = O;
        invalidate();
        requestLayout();
    }
}
