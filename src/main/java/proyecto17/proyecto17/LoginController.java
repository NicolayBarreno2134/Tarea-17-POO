package proyecto17.proyecto17;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController {

    @FXML private TextField usuarioTextField;
    @FXML private PasswordField contraseñaTextField;
    @FXML private ComboBox<String> rolComboBox;
    @FXML private Label lblError;

    @FXML
    private void initialize() {
        rolComboBox.getItems().addAll("Administrador", "Vendedor", "Cajero");
    }

    @FXML
    private void ingresar(ActionEvent event) throws IOException {
        String usuario = usuarioTextField.getText();
        String contraseña = contraseñaTextField.getText();
        String rol = rolComboBox.getValue();

        if (usuario.isEmpty() || contraseña.isEmpty() || rol == null) {
            lblError.setText("Por favor complete todos los campos.");
            return;
        }

        if (usuario.equals("admin") && contraseña.equals("1234") && rol.equals("Administrador")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("MiTienda - Sistema de Ventas");
            stage.show();
        } else {
            lblError.setText("Credenciales incorrectas o rol inválido.");
        }
    }
}

