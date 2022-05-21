module hslu.sweng.fs22.team2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.controlsfx.controls;

    opens hslu.sweng.fs22.team2 to javafx.fxml;
    opens hslu.sweng.fs22.team2.ui to javafx.fxml;

    exports hslu.sweng.fs22.team2;
}
