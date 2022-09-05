module com.witnip.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.witnip.tictactoe to javafx.fxml;
    exports com.witnip.tictactoe;
}