/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package sunseeker.telemetry;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Dimension;

class GraphPanel extends AbstractGraphPanel {
    final protected int SCALE_HASH_SIZE = 1;

    protected Graphics2D artist;

    protected int label = 25;

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        artist = (Graphics2D) g;

        artist.setFont(new Font("Monospaced", Font.PLAIN, 8));
        /*
         * These give us our scale
         */
        drawAxes();
    }

    protected void drawAxes () {
        artist.setStroke(new BasicStroke(
            AXIS_WIDTH,
            BasicStroke.CAP_SQUARE,
            BasicStroke.JOIN_MITER
        ));

        /*
         * Draw the x-axis
         */
        artist.drawLine(
            0, xAxisInset,
            PANEL_WIDTH, xAxisInset
        );

        /*
         * Draw the y-axis
         */
        artist.drawLine(
            Y_AXIS_INSET, 0,
            Y_AXIS_INSET, PANEL_HEIGHT
        );

        drawScales();
    }

    protected void drawScales () {
        drawXScale();
        drawYScale();
    }

    protected void drawXScale () {
        int value    = X_AXIS_SCALE;
        int halfHash = SCALE_HASH_SIZE / 2;

        for (; value < PANEL_WIDTH; value += X_AXIS_SCALE) {
            artist.drawLine(
                AXIS_INSET + value, xAxisInset - SCALE_HASH_SIZE,
                AXIS_INSET + value, xAxisInset + SCALE_HASH_SIZE
            );
        }
    }

    protected void drawYScale () {
        int posOffset = xAxisInset - Y_AXIS_SCALE;
        int negOffset = xAxisInset + Y_AXIS_SCALE;

        while (posOffset > 0 || negOffset < PANEL_HEIGHT) {
            if (posOffset > 0) {
                drawYScaleHash(posOffset);

                posOffset -= Y_AXIS_SCALE;

                drawLabel(label + 25,
                    posOffset
                );
            }

            if (negOffset < PANEL_HEIGHT) {
                drawYScaleHash(negOffset);

                negOffset += Y_AXIS_SCALE;

                drawLabel(-1 * label,
                    negOffset
                );
            }

            label += 25;
            
        }
        label = 25;
    }

    protected void drawYScaleHash (int offset) {
        artist.drawLine(
            Y_AXIS_INSET - SCALE_HASH_SIZE, offset,
            Y_AXIS_INSET + SCALE_HASH_SIZE, offset
        );
    }

    /*
    * Draw labels on y-axis
    */
    protected void drawLabel (int label, int counter) {
        if((label % 20) == 0)
            artist.drawString(toString(label), 5, counter);
    }

    public static String toString (int label) {
        if(label < 0)
            return label + " ";

        return " " + label + " ";
    }
}
