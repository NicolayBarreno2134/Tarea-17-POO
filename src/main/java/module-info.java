module proyecto17.proyecto17 {
    requires javafx.controls;
    requires javafx.fxml;


    opens proyecto17.proyecto17 to javafx.fxml;
    exports proyecto17.proyecto17;
}