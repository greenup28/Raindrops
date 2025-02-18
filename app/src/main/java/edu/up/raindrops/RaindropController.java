package edu.up.raindrops;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;



public class RaindropController implements SeekBar.OnSeekBarChangeListener{


    private DROPS _rain;
    public RaindropController(DROPS drops) {

        _rain = drops;

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar == seekBar.findViewById(R.id.UpandDown)){
            _rain.mainY = progress;
            _rain.checkTouchingDrops();
            _rain.invalidate();
        }
        if(seekBar == seekBar.findViewById(R.id.SidetoSide)){
            _rain.mainX = progress;
            _rain.checkTouchingDrops();
            _rain.invalidate();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
