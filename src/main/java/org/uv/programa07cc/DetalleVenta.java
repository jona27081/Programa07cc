/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa07cc;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author zS20006736
 */
@Entity
@Table(name = "detalleventa")
public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long idProducto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "precio") 
    private double precio; //Decimal
    @Column(name = "cantidad")
    private double cantidad; //Int
    @Column(name = "subtotal")
    private double subtotal; //Decimal
    
    @ManyToOne
    @JoinColumn(name = "id_Venta")
    private Venta venta;
    
    public DetalleVenta(){
        venta=new Venta();
    }
    
    public Venta getVenta() {
       return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }    
    
}
