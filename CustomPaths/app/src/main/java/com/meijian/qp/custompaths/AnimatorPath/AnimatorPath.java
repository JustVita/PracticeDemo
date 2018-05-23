package com.meijian.qp.custompaths.AnimatorPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by QP on 2018/5/5.
 */

public class AnimatorPath {

    private List<PathPoint> mPoints = new ArrayList<>();

    public void moveTo(float x, float y){

        mPoints.add(PathPoint.moveTo(x,y));
    }

    public void lineTo(float x, float y){

        mPoints.add(PathPoint.lineTo(x,y));
    }

    public void secondBesselCurveTo(float c0X,float c0Y, float x, float y){

        mPoints.add(PathPoint.secondBesselCurveTo(c0X,c0Y,x,y));
    }

    public void  thirdBesselCurveTo(float c0X, float c0Y, float c1X, float c1Y, float x, float y){

        mPoints.add(PathPoint.thirdBesselCurveTo(c0X,c0Y,c1X,c1Y,x,y));
    }

    public Collection<PathPoint> getPoints(){

        return  mPoints;
    }
}
