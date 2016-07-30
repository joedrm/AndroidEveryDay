package wdy.com.myandroidframework.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wdy on 16/7/6.
 */
public class RoundImageView extends ImageView {

    private Bitmap bitmap;
    private Rect mRect = new Rect();
    private PaintFlagsDrawFilter pdf = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG);
    private Paint mPlaint = new Paint();
    private Path mPath = new Path();

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private void init() {
        mPlaint.setStyle(Paint.Style.STROKE);
        mPlaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPlaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap == null){
            return;
        }
        mRect.set(0, 0, getWidth(), getHeight());
        canvas.save();
        canvas.setDrawFilter(pdf);
        mPath.addCircle(getWidth()/2, getWidth()/2,getHeight()/2,Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);
        canvas.drawBitmap(bitmap, null, mRect, mPlaint);
        canvas.restore();
    }

}
