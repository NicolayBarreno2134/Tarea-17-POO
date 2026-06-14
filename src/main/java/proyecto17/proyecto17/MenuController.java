package proyecto17.proyecto17;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MenuController {

    @FXML private TextField codigoField, nombreField, precioField, stockField;
    @FXML private ComboBox<String> categoriaBox, estadoBox;
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> colCodigo, colNombre, colCategoria, colEstado;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;

    private ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        categoriaBox.getItems().addAll("Calzado", "Ropa", "Accesorios");
        estadoBox.getItems().addAll("Activo", "Inactivo");

        colCodigo.setCellValueFactory(data -> data.getValue().codigoProperty());
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        colCategoria.setCellValueFactory(data -> data.getValue().categoriaProperty());
        colPrecio.setCellValueFactory(data -> data.getValue().precioProperty().asObject());
        colStock.setCellValueFactory(data -> data.getValue().stockProperty().asObject());
        colEstado.setCellValueFactory(data -> data.getValue().estadoProperty());

        tablaProductos.setItems(listaProductos);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void nuevoProducto() {
        limpiarCampos();
        mostrarAlerta("Nuevo producto", "Campos listos para ingresar un nuevo producto.", Alert.AlertType.INFORMATION);
        codigoField.requestFocus();
    }

    @FXML
    private void guardarProducto() {
        if (codigoField.getText().isEmpty() || nombreField.getText().isEmpty() ||
                categoriaBox.getValue() == null || precioField.getText().isEmpty() ||
                stockField.getText().isEmpty() || estadoBox.getValue() == null) {
            mostrarAlerta("Error", "Debe llenar todos los campos antes de guardar.", Alert.AlertType.ERROR);
            return;
        }

        try {
            double precio = Double.parseDouble(precioField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Producto p = new Producto(
                    codigoField.getText(),
                    nombreField.getText(),
                    categoriaBox.getValue(),
                    precio,
                    stock,
                    estadoBox.getValue()
            );
            listaProductos.add(p);
            mostrarAlerta("Éxito", "Producto guardado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio y Stock deben ser valores numéricos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void actualizarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un producto de la tabla para actualizar.", Alert.AlertType.ERROR);
            return;
        }

        try {
            seleccionado.setNombre(nombreField.getText());
            seleccionado.setCategoria(categoriaBox.getValue());
            seleccionado.setPrecio(Double.parseDouble(precioField.getText()));
            seleccionado.setStock(Integer.parseInt(stockField.getText()));
            seleccionado.setEstado(estadoBox.getValue());
            tablaProductos.refresh();
            mostrarAlerta("Éxito", "Producto actualizado correctamente.", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio y Stock deben ser valores numéricos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un producto de la tabla para eliminar.", Alert.AlertType.ERROR);
            return;
        }

        listaProductos.remove(seleccionado);
        mostrarAlerta("Éxito", "Producto eliminado correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void limpiarCampos() {
        codigoField.clear();
        nombreField.clear();
        precioField.clear();
        stockField.clear();
        categoriaBox.setValue(null);
        estadoBox.setValue(null);
    }

    @FXML
    private void cerrarSesion() {
        mostrarAlerta("Cerrar sesión", "Sesión cerrada correctamente.", Alert.AlertType.INFORMATION);
        Stage stage = (Stage) codigoField.getScene().getWindow();
        stage.close();
    }
}
