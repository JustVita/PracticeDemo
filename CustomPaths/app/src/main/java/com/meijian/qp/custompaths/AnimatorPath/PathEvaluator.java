package com.meijian.qp.custompaths.AnimatorPath;

import android.animation.TypeEvaluator;

/**
 * Created by QP on 2018/5/5.
 */

public class PathEvaluator implements TypeEvaluator<PathPoint>{
    @Override
    public PathPoint evaluate(float fraction, PathPoint startValue, PathPoint endValue) {
        float x,y;
        float oneMiunsT = 1- fraction;
        if (endValue.mOperation == PathPoint.THIRD_CURVE){
            x = startValue.mX*oneMiunsT*oneMiunsT*oneMiunsT
                    + 3*endValue.mControl10X*fraction*oneMiunsT*oneMiunsT
                    + 3*endValue.mControl11X*fraction*fraction*oneMiunsT
                    + endValue.mX*fraction*fraction*fraction;

            y = startValue.mY*oneMiunsT*oneMiunsT*oneMiunsT
                    + 3*endValue.mControl10Y*fraction*oneMiunsT*oneMiunsT
                    + 3*endValue.mControl11Y*fraction*fraction*oneMiunsT
                    + endValue.mY*fraction*fraction*fraction;
        }else if (endValue.mOperation == PathPoint.SECOND_CURVE){

            x = oneMiunsT*oneMiunsT*startValue.mX
                    + 2*fraction*oneMiunsT*endValue.mControl10X
                    + fraction*fraction*endValue.mX;

            y = oneMiunsT*oneMiunsT*startValue.mY
                    + 2*fraction*oneMiunsT*endValue.mControl10Y
                    + fraction*fraction*endValue.mY;

        }else if (endValue.mOperation == PathPoint.LINE){

            x = startValue.mX + fraction * (endValue.mX - startValue.mX);

            y = startValue.mY + fraction * (endValue.mY - startValue.mY);
        }else {

            x = endValue.mX;

            y = endValue.mY;
        }
        return PathPoint.moveTo(x,y);
    }
}
