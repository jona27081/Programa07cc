/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa07cc;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zS20006736
 */

@Entity
@Table(name = "venta")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idVenta;
    @Column(name = "fecha")
    private Date fecha; //Poner formato date sql
    @Column(name = "monto")
    private double monto; //Decimal
   
    @OneToMany(mappedBy= "venta" , fetch = FetchType.LAZY) //Forzamos a cargarlo despues, no lo carga en memoria hasta despues, se evita matar a los servidores
    private Set<DetalleVenta> detalle ;    
    
    public Venta() {
        detalle=new HashSet<>();
    }

    public Set<DetalleVenta> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleVenta> detalle) {
        this.detalle = detalle;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void limpiar(){
        idVenta = 0L;
        monto = 0.0;
        detalle.clear();
    }
    
}
