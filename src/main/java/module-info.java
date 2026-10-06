module com.example.hellofx {
    requires javafx.controls;
    opens com.example.hellofx to javafx.fxml, javafx.base;
    exports com.example.hellofx;
}