package com.meijian.qp.custompaths;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by QP on 2018/5/5.
 */

public class PathVIew extends View {
    private Paint paint;

    public PathVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();

        paint.setAntiAlias(true);

        paint.setDither(true);

        paint.setStyle(Paint.Style.STROKE);

        paint.setColor(Color.GREEN);

        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Path path = new Path();

        int width = getWidth();
        int height = getHeight();

        int length = width / 10;
        int bound = height / 10;

        for ( int i = 0 ; i < 11; i++){

            for (int j = 0; j< 11 ; j++){

               canvas.drawLine(j * length, 0 , j * length, height, paint);
            }
            canvas.drawLine(0,i * bound, width, i * bound, paint);
        }
//        path.moveTo(60,60);
//        path.lineTo(460,460);
//        //path.moveTo(60,60);
//        path.lineTo(0,1260);
//        path.lineTo(460,2060);
//        //path.quadTo(660,260,860,460);
//        //path.cubicTo(160,660,960,1060,260,1260);
//        canvas.drawPath(path,paint);
    }
}
