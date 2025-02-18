/**
 * This is the surface view that sets up and randomizes the raindrops drawn.
 *
 * @author Madilynn Greenup
 * @date 2/9/25
 *
 */


package edu.up.raindrops;

import static kotlin.random.RandomKt.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import androidx.core.graphics.ColorUtils;

public class DROPS extends SurfaceView {
    //Initializes the color of the rain drops
    Paint _blue = new Paint(); //normal blue
    Paint _white = new Paint(); //blue white
    Paint _darkBlue = new Paint(); // dark blue
    Paint _seaBlue = new Paint(); //color of the sea
    Paint _skyBlue = new Paint(); //color of the sky
    Paint _snowBlue = new Paint(); //a very light blue
    Paint _rainBlue = new Paint(); //color of the rain
    Paint _bluerBlue = new Paint(); //The darkest blue I could get.
    Paint _notBlue = new Paint(); //A blue that is still blue but boarders on purple.
    Paint _blueGreen = new Paint(); //A blue that could be blue if the green was removed
    Paint _justBlue = new Paint(); //A bright blue color
    Paint _redSeaBlue = new Paint(); //Its the color of the red sea
    Paint _jeanBlue = new Paint(); //The color of blue jeans



    //Initializes the number of drops being drawn.
    float numberOfDrops = 1;

    //Array to hold all of the colors, so when the loop is called, this can cycle through the colors
    Paint[] colors = new Paint[12];
    //Holds the strings of each of the colors to be used when it is blended
    String[] stringColor = new String[12];

    //The main raindrop variables
    int mainDrop;
    float mainX = randomPositionX();
    float mainY = randomPositionY();
    Paint mainColor;
    String mainColorString;
    int mainBlendedColor;

    //Sees if the drop is deleted or not
    Boolean[] notDeleted = new Boolean[12];

    //Arrays to hold the x and y positions of the other drops.
    //If I don't have these, the drops get redrawn in a different position everytime the main drops is moved.
    float[] dropsX = new float[12];

    float[] dropsY = new float[12];

    /**
     * It is the constructor of DROPS. Sets up the surface view, randomizes the number of drops,
     * sets up each color so they show up on screen, and set each color to a place in the array
     * colors.
     *
     * @param context is used with super
     * @param attrs is also used with super
     *
     * Returns nothing
     */

    public DROPS(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        //sets the number of rain drops drawn, new object in order to use the random class

        numberOfDrops = (float) ((Math.random()*7) + 5) ;

        //selects the main raindrop
        selectMainDrop();

        //sets all the drops to be drawn
        for(int i = 0; i < numberOfDrops; i++){
            notDeleted[i] = true;
        }

        //selects the random x and y positions for the other drops
        for(int i = 0; i < numberOfDrops; i++){
            if(i == mainDrop){
            }
            dropsX[i] = randomPositionX();
        }
        for(int i = 0; i < numberOfDrops; i++){
            if(i == mainDrop){
            }
            dropsY[i] = randomPositionX();
        }

        //Initialize drawing styles
        _blue.setColor(0xFF14EFFF); //Light Blue
        _blue.setStyle(Paint.Style.FILL);
        stringColor[0] = "#14EFFF";

        _white.setColor(0xFFD7FAFC); //White Blue
        _white.setStyle(Paint.Style.FILL);


        _darkBlue.setColor(0xFF00548C); //Dark Blue
        _darkBlue.setStyle(Paint.Style.FILL);
        stringColor[1] = "#00548C";

        _seaBlue.setColor(0xFF409FDE); //Sea Blue
        _seaBlue.setStyle(Paint.Style.FILL);
        stringColor[2] = "#409FDE";

        _skyBlue.setColor(0xFF52D8F2); //Sky Blue
        _skyBlue.setStyle(Paint.Style.FILL);
        stringColor[3] = "#52D8F2";

        _snowBlue.setColor(0xFF83D6EB); //Snow Blue
        _snowBlue.setStyle(Paint.Style.FILL);
        stringColor[4] = "#83D6EB";

        _rainBlue.setColor(0xFF0F9CBF); //Rain Blue
        _rainBlue.setStyle(Paint.Style.FILL);
        stringColor[5] = "#0F9CBF";

        _bluerBlue.setColor(0xFF012B36); //Bluer Blue
        _bluerBlue.setStyle(Paint.Style.FILL);
        stringColor[6] = "#012B36";

        _notBlue.setColor(0xFF2900F7); //Not Blue
        _notBlue.setStyle(Paint.Style.FILL);
        stringColor[7] = "#2900F7";

        _blueGreen.setColor(0xFF7CF7D6); // Green Blue
        _blueGreen.setStyle(Paint.Style.FILL);
        stringColor[8] = "#7CF7D6";

        _justBlue.setColor(0xFF00DDFF); // Just Blue
        _justBlue.setStyle(Paint.Style.FILL);
        stringColor[9] = "#00DDFF";

        _redSeaBlue.setColor(0xFFF5586D); // Red Sea Blue
        _redSeaBlue.setStyle(Paint.Style.FILL);
        stringColor[10] = "#F5586D";

        _jeanBlue.setColor(0xFF0B3CB8); //Jean Blue
        _jeanBlue.setStyle(Paint.Style.FILL);
        stringColor[11]= "#0B3CB8";

        //sets up the array so each place is filled with a different color
        colors[0] = _blue;
        colors[1] = _darkBlue;
        colors[2] =_seaBlue;
        colors[3] = _skyBlue;
        colors[4] = _snowBlue;
        colors[5] = _rainBlue;
        colors[6] = _bluerBlue;
        colors[7] = _notBlue;
        colors[8] = _blueGreen;
        colors[9] = _justBlue;
        colors[10] = _redSeaBlue;
        colors[11] = _jeanBlue;

    }

    /**
     * Ensures the proper amount of drops are drawn according to the numberOfDrops.
     * It ensures that the positions are random by calling on the different randomizers for x and y.
     *
     * @param rain is the canvas where everything is being drawn
     *
     * Returns nothing.
     */

    @Override
    public void onDraw(Canvas rain){

        //draws the number of drops generated and each with a different color.
        //Does not draw the main drop so that another method can
        for(int i = 0; i < numberOfDrops; i++){

            if(i == mainDrop){
                drawMainDrop(rain, colors[i]);
                mainColor = colors[i];
                mainColorString = stringColor[i];
            }else {
               if(notDeleted[i] == true) {
                   drawRainDrop(rain, dropsX[i], dropsY[i], colors[i]);
               }
            }

        }


    }

    /**
     * This checks if the main drop is touching any other drops.
     * This does so by using the x and the y's each drop.
     *
     * No parameters
     *
     * Returns nothing.
     */
    public void checkTouchingDrops(){

        for(int i = 0; i < numberOfDrops; i++){

            //Makes this ignore the main drop so it isn't blending itself
            if(i == mainDrop){

            }
             //Right of the main drop and left of the drop being checked
             //Then there is also the end bound, I do this for the rest as well
            else if((((mainX + 30) >= dropsX[i]) && ((mainX + 30) <= (dropsX[i] + 30)))){
                //Top of the main drop and bottom of the drop being checked
                if(((mainY <= (dropsY[i] + 30)) && (mainY >= dropsY[i]))){

                    blendDropColor(i);
                    deleteDrops(i);
                }

            }
            //Left of the main drop and right of the drop being checked
            else if((mainX <= (dropsX[i] + 30)) && (mainX >= dropsX[i])){
                //Bottom of the main drop and top of the drop being checked
                if(((mainY + 30) >= dropsY[i]) && ((mainY + 30) <= (dropsY[i] + 30))){
                    blendDropColor(i);
                    deleteDrops(i);

                }

            }



        }


    }

    public void deleteDrops(int i){
        notDeleted[i] = false;
    }

    /**
     * This blends the colors.
     * First it converts them to ints as the hex is a string and you can't do anything with those.
     * Then the ints are blended together, set back to hex, and sets the main color.
     *
     * This was unnecessarily hard and I had to consult the internet so many times that most of the
     * times it was not helpful. I wanted to cry.
     *
     * No parameters
     *
     * Returns nothing.
     */
    public void blendDropColor(int i){

        //Had to convert the hex code from a string to int.

        int firstColor = Color.parseColor(mainColorString);
        int secondColor = Color.parseColor(stringColor[i]);

        //This only took ints so could not use the hex colors.
        mainBlendedColor = ColorUtils.blendARGB(firstColor, secondColor, 0.5F);

        mainColor.setColor(mainBlendedColor);

    }

    //These getters are here so I can use these values in the controller and set the seekbar to this
    //number in the beginning
    public int getMainX(){
        return (int) mainX;
    }

    public int getMainY(){
        return (int) mainY;
    }

    //Since the main drop has its own method, it can be redrawn when the controller wants it to
    public void drawMainDrop(Canvas rain, Paint color){
        drawRainDrop(rain, mainX, mainY, color);
    }

    //sets the main raindrop
    public void selectMainDrop(){
        mainDrop = (int) (Math.random()*numberOfDrops);
    }

    //when called determines the random position X for each raindrop
    public float randomPositionX(){

        return (float) Math.random()*801;
    }

    //when called determines the random position Y for each raindrop
    public float randomPositionY(){

        return (float) Math.random()*801;
    }

    //draws the raindrop with a diameter of 30 dp and a highlight of 10 dp
    public void drawRainDrop(Canvas rain, float x,float y, Paint paint){
            //Draws the base circle
            rain.drawOval(x, y, x + 30, y + 30, paint);

            //Draws the highlight on the circle
            rain.drawOval(x + 5, y + 5, x + 15, y + 15, _white);

    }
}
