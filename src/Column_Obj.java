import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Column_Obj {

    public Column_Obj(String name) {
        setName(name);
    }

    private final StringProperty name = new SimpleStringProperty(this, "name");
    public StringProperty name() {
        return name ;
    }
    public final String getName() {
        return name().get();
    }
    public final void setName(String howPay) {
        name().set(howPay);
    }

}
