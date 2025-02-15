/**
 * This is the main that used the rain.xml and it's content.
 *
 * @author Madilynn Greenup
 * @date 2/9/25
 *
 */

package edu.up.raindrops;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.rain);

        DROPS drops = findViewById(R.id.Rain);
        RaindropController controller = new RaindropController(drops);

        SeekBar upAndDown = findViewById(R.id.UpandDown);
        SeekBar leftAndRight = findViewById(R.id.SidetoSide);

        upAndDown.setOnSeekBarChangeListener(controller);
        leftAndRight.setOnSeekBarChangeListener(controller);

        upAndDown.setProgress(drops.getMainY());
        leftAndRight.setProgress(drops.getMainX());


    }
}