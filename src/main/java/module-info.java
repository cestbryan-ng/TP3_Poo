module ui.tp3_poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;
    requires java.sql;

    opens ui.tp3_poo to javafx.fxml, com.fasterxml.jackson.databind, javafx.graphics;
    opens classes to com.fasterxml.jackson.databind;
    exports ui.tp3_poo;
}