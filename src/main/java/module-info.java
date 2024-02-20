module com.example.currency_converter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.currency_converter to javafx.fxml;
    exports com.example.currency_converter;
}