/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author moren
 */

public class ItemDTO implements Serializable {

    private Long idItem;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private Long idProducto;
    private String nombreProducto;
    private String imagenUrl;

    public ItemDTO() {
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    @Override
    public String toString() {
        return "ItemDTO{"
                + "idItem=" + idItem
                + ", cantidad=" + cantidad
                + ", precioUnitario=" + precioUnitario
                + ", idProducto=" + idProducto
                + ", nombreProducto='" + nombreProducto + '\''
                + ", imagenUrl='" + imagenUrl + '\''
                + '}';
    }
}
