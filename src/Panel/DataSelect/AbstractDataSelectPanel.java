/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 2, 2016
 */

package sunseeker.telemetry;

import javax.swing.JPanel;

abstract class AbstractDataSelectPanel extends AbstractPanel {
    protected DataTypeCollectionInterface types;
    
    public void setTypes (DataTypeCollectionInterface types) {
        this.types = types;
    }
}
