package com.meijian.qp.custompaths;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.meijian.qp.custompaths.AnimatorPath.AnimatorPath;
import com.meijian.qp.custompaths.AnimatorPath.PathEvaluator;
import com.meijian.qp.custompaths.AnimatorPath.PathPoint;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton fab;
    private AnimatorPath path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fab = (FloatingActionButton)findViewById(R.id.fab);

        setPath();

        fab.setOnClickListener(this);
    }

    private void setPath() {

        path = new AnimatorPath();
        path.moveTo(0,0);
        path.lineTo(400,400);
        path.lineTo(0,1260);
        path.lineTo(1000,1000);
        path.lineTo(0,0);
        //path.lineTo(460,2060);
        //path.secondBesselCurveTo(600,200,800,400);
        //path.thirdBesselCurveTo(100,600,900,1000,200,1200);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fab:
                startAnimatorPath(fab,"fab", path);
                break;
        }
    }

    private void startAnimatorPath(View view, String fab1, AnimatorPath path) {

        ObjectAnimator animator = ObjectAnimator.ofObject(this, fab1, new PathEvaluator(), path.getPoints().toArray());
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(1000);
        animator.start();
    }

    public  void setFab(PathPoint newLoc){

        fab.setTranslationX(newLoc.mX);
        fab.setTranslationY(newLoc.mY);
    }

}
