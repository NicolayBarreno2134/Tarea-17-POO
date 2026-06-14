package proyecto17.proyecto17;

import javafx.beans.property.*;

public class Producto {
    private StringProperty codigo;
    private StringProperty nombre;
    private StringProperty categoria;
    private DoubleProperty precio;
    private IntegerProperty stock;
    private StringProperty estado;

    public Producto(String codigo, String nombre, String categoria, double precio, int stock, String estado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.categoria = new SimpleStringProperty(categoria);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.estado = new SimpleStringProperty(estado);
    }

    public StringProperty codigoProperty() {
        return codigo;
    }
    public StringProperty nombreProperty() {
        return nombre;
    }
    public StringProperty categoriaProperty() {
        return categoria;
    }
    public DoubleProperty precioProperty() {
        return precio;
    }
    public IntegerProperty stockProperty() {
        return stock;
    }
    public StringProperty estadoProperty() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }
    public void setPrecio(double precio) {
        this.precio.set(precio);
    }
    public void setStock(int stock) {
        this.stock.set(stock);
    }
    public void setEstado(String estado) {
        this.estado.set(estado);
    }
}
