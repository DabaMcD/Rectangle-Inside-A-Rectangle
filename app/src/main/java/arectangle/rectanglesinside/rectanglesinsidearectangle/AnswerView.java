package arectangle.rectanglesinside.rectanglesinsidearectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AnswerView extends View {
    public AnswerView(Context context) {
        super(context);
    }
    public AnswerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public AnswerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);
    }
    void draw() {
        invalidate();
        requestLayout();
    }
}
