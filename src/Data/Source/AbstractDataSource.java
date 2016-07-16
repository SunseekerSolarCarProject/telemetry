/**
 * Sunseeker Telemetry
 *
 * @author Alec Carpenter <alecgunnar@gmail.com>
 * @date July 9, 2016
 */

package sunseeker.telemetry;

import java.util.List;
import java.util.ArrayList;

abstract class AbstractDataSource implements DataSourceInterface {
    protected DataTypeCollectionInterface types;

    protected List<String> typeNames;

    public AbstractDataSource () {
        types     = new DataTypeCollection();
        typeNames = new ArrayList<String>();
    }

    public DataTypeCollectionInterface getTypes () {
        return types;
    }

    protected void registerDataType (String name, String unit) {
        types.add(new DataType(name, unit));
        typeNames.add(name);
    }

    protected void putValue (String type, double value) {
        if (!typeNames.contains(type))
            return;

        types.get(typeNames.indexOf(type)).putValue(value);
    }
}
