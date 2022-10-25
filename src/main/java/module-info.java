module main {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;

    opens project to javafx.fxml;
    exports project;
}
