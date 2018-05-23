package com.meijian.qp.custompaths.AnimatorPath;

/**
 * Created by QP on 2018/5/5.
 */

public class PathPoint {

    public  static  final int MOVE = 0;

    public static final int LINE = 1;

    public static final int SECOND_CURVE = 2;

    public static final int THIRD_CURVE = 3;

    public  float mX,mY;

    public float mControl10X,mControl10Y;

    public float mControl11X,mControl11Y;

    public  int mOperation;

    public  PathPoint(int mOperation, float mX, float mY){
        this.mX = mX;
        this.mY = mY;
        this.mOperation = mOperation;
    }

    public  PathPoint(float mControl10X, float mControl10Y, float mX, float mY){
        this.mX = mX;
        this.mY = mY;
        this.mControl10X = mControl10X;
        this.mControl10Y = mControl10Y;
        this.mOperation = SECOND_CURVE;
    }

    public  PathPoint(float mControl10X, float mControl10Y, float mControl11X, float mControl11Y, float mX, float mY){
        this.mX = mX;
        this.mY = mY;
        this.mControl10X = mControl10X;
        this.mControl10Y = mControl10Y;
        this.mControl11X = mControl11X;
        this.mControl11Y = mControl11Y;
        this.mOperation = THIRD_CURVE;
    }

    public static PathPoint moveTo(float x, float y){

        return  new PathPoint(MOVE, x, y);
    }

    public static  PathPoint lineTo(float x, float y){

        return new PathPoint(LINE,x,y);
    }

    public static PathPoint secondBesselCurveTo(float c0X, float c0Y, float x, float y){

        return new PathPoint(c0X,c0Y,x,y);
    }

    public static PathPoint thirdBesselCurveTo(float c0X, float c0Y, float c1X, float c1Y, float x, float y){

        return  new PathPoint(c0X,c0Y,c1X,c1Y,x,y);
    }
}
