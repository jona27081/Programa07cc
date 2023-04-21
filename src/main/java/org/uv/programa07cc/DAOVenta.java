/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.programa07cc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zS20006736
 */
public class DAOVenta implements IDAOGeneral<Venta, Long> {

    @Override
    public Venta create(Venta v) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();

        session.save(v);
        for (DetalleVenta dt : v.getDetalle()) {
            session.save(dt);
        }
        JOptionPane.showMessageDialog(null, "Fecha: " + v.getFecha() + "   " + "Total: " + v.getMonto(), "ID de venta creada: " + v.getIdVenta(), JOptionPane.INFORMATION_MESSAGE);

        t.commit();
        session.close();
        return v;
    }

    /*

    @Override
    public Venta update(Long id, Venta vN) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        Venta v = findById(id);

        if (v != null) {
            session.update(vN);
            t.commit();
            session.close();
            JOptionPane.showMessageDialog(null, "Venta Actualizada");
        }

        return vN;
    }

    @Override
    public List<Venta> findAll() {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * FROM Ventas", Venta.class);
        List<Venta> lv = query.getResultList();
        t.commit();
        session.close();

        if (lv == null) {
            JOptionPane.showMessageDialog(null, "La base de datos se encuentra vacia", "Error", JOptionPane.ERROR_MESSAGE);
            session.close();
            return null;
        }

        return lv;
    }*/
    @Override
    public boolean delete(Long id) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        Venta v = findById(id);
        boolean b = false;

        if (v != null) {

            for (DetalleVenta dt : v.getDetalle()) {
                session.delete(dt);
            }
            session.delete(v);
            t.commit();
            session.close();
            JOptionPane.showMessageDialog(null, "Fecha: " + v.getFecha() + "   " + "Total: " + v.getMonto(), "ID de venta eliminada: " + v.getIdVenta(), JOptionPane.INFORMATION_MESSAGE);
            b = true;

        }

        return b;
    }

    @Override
    public Venta update(Long id, Venta vN) {
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        Venta vO = findById(id);

        if (vO != null) {
            vO.setFecha(vN.getFecha());
            vO.setMonto(vN.getMonto());
            for (DetalleVenta dt : vO.getDetalle()) {
                session.delete(dt);
            }
            vO.setDetalle(vN.getDetalle());
            session.update(vO);
            for (DetalleVenta dt : vO.getDetalle()) {
                dt.setVenta(vO);
                session.save(dt);
            }
            t.commit();
            JOptionPane.showMessageDialog(null, "Venta Actualizada");
            session.close();
        }

        return vO;
    }

    @Override
    public List<Venta> findAll() {
        List<Venta> ventas = null;
        Session session = HibernateUtil.getSession();
        Transaction t = session.beginTransaction();
        ventas = session.createQuery("from Venta").list();
        for (Venta venta : ventas) {
            List<DetalleVenta> detalle = null;
            detalle = session.createQuery("from DetalleVenta where id=" + venta.getIdVenta()).list();
            for (DetalleVenta detalleVenta : detalle) {
                venta.getDetalle().add(detalleVenta);
            }
        }
        t.commit();
        return ventas;
    }

    @Override
    public Venta findById(Long id) {
        ArrayList<DetalleVenta> listaDetalleVenta = new ArrayList<>();
        Session session = HibernateUtil.getSession();
        Venta v = session.get(Venta.class, id);

        if (v == null) {
            JOptionPane.showMessageDialog(null, "Venta no se encuentra en la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
            session.close();
            return null;
        }

        for (DetalleVenta dt : v.getDetalle()) {
            listaDetalleVenta.add(dt);
        }

        session.close();
        return v;
    }

}
