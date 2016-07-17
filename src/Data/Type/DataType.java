/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package sunseeker.telemetry;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

class DataType implements DataTypeInterface {
    protected Color[] colors = {
        Color.GREEN,
        Color.BLUE,
        Color.RED,
        Color.ORANGE,
        Color.MAGENTA,
        Color.YELLOW,
        Color.CYAN
    };

    protected String name;
    protected String units;

    protected static int colorCount = 0;
    protected Color color;

    protected boolean enabled = true;

    /*
     * Over life of object
     */
    protected double min = 0;
    protected double cur = 0;
    protected double max = 0;

    protected List<Double> data;

    public DataType (String name, String units) {
        this.name  = name;
        this.units = units;

        /*
         * Determine which color we'll be using for this line
         */
        color = colors[colorCount % colors.length];
        colorCount++;

        /*
         * Create a synconized data list
         */
        data = Collections.synchronizedList(new ArrayList<Double>());

        data.add(0.0);
    }

    public String getName () {
        return name;
    }

    public String getUnits () {
        return units;
    }

    public Color getColor() {
        return color;
    }

    public void setEnabled (boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled () {
        return enabled;
    }

    public void putValue (double value) {
        if (data.size() >= MAX_DATA_POINTS)
            data.remove(0);

        cur = value;

        if (data.size() == 1) {
            min = value;
            max = value;
        } else {
            if (value < min)
                min = value;

            if (value > max)
                max = value;
        }

        data.add(value);
    }

    public List<Double> getData () {
        return data;
    }

    public double getMinimumValue () {
        return min;
    }

    public double getCurrentValue () {
        return cur;
    }

    public double getMaximumValue () {
        return max;
    }
}
