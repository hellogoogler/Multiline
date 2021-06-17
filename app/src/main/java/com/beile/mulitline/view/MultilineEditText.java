package com.beile.mulitline.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.EditText;

import com.beile.multiline.R;

@SuppressLint("AppCompatCustomView")
public class MultilineEditText extends EditText {
    private Paint linePaint;
    private int canvasColor;
    private int paintColor;
    private float density;
    private int lineCount = 0;
    private Context mContext;
    private int margin;

    public MultilineEditText(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.linePaint = new Paint();
        this.mContext = paramContext;
        canvasColor = paramContext.getResources().getColor(R.color.white);
        paintColor = paramContext.getResources().getColor(R.color.color_e4e5e7);
        linePaint.setColor(paintColor);
        WindowManager wm = (WindowManager) paramContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        density = dm.density;
    }

    protected void onDraw(Canvas paramCanvas) {
        paramCanvas.drawColor(this.canvasColor);
        if (getLineCount() != lineCount) {
            lineCount = getLineCount();
            setMinHeight(getLineHeight() * getLineCount() + 10);
        }
        int i = getLineCount();
        int j = getHeight();
        int k = getLineHeight();
        int m = 1 + j / k;
        if (i < m)
            i = m;
        int n = getCompoundPaddingTop();
        for (int i2 = 0; ; i2++) {
            if (i2 >= i) {
                setPadding(10 + (int) this.margin, 0, 0, 0);
                super.onDraw(paramCanvas);
                paramCanvas.restore();
                return;
            }
            n += k;
            paramCanvas.drawLine(0, n - 10 * density, getRight() - 20 * density, n - 10 * density, this.linePaint);
            paramCanvas.save();
        }
    }
}