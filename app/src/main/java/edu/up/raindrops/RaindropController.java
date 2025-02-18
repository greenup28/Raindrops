/**
 * This is the controller that acts as a go between of the seekbar and the surface view.
 *
 * @author Madilynn Greenup
 * @date 2/9/25
 *
 */

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

    /**
     * Communicated with both MainActivity and DROPS as it gets the progress and moved the main drop.
     * It also makes sure that it is user input before changing anything.
     *
     * @param seekBar that checks which of the two seekBars is being used
     * @param progress is the position of the seek bar
     * @param fromUser checks is if it is the starting position or changed from the user
     *
     * Returns nothing
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar == seekBar.findViewById(R.id.UpandDown)){
            _rain.mainY = progress;
            if(fromUser == true) {
                _rain.checkTouchingDrops();
            }
            _rain.invalidate();
        }
        if(seekBar == seekBar.findViewById(R.id.SidetoSide)){
            _rain.mainX = progress;
            if(fromUser == true) {
                _rain.checkTouchingDrops();
            }
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
