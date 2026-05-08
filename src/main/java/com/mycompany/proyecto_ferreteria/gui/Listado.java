package com.mycompany.proyecto_ferreteria.gui;

import com.mycompany.proyecto_ferreteria.service.InventarioService;
import com.mycompany.proyecto_ferreteria.model.Producto; 
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Listado extends javax.swing.JFrame {

    private InventarioService service;
    
    public Listado(InventarioService service) {
        initComponents();
        this.service = service;
        setLocationRelativeTo(null);
        
        cargarTabla();
    }
    
    private void cargarTabla() {
        String[] columnas = {"ID", "Nombre", "Categoría", "Almacén", "Stock Actual", "Stock Mínimo", "Estado"};
        
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        List<Producto> lista = service.getProductos();

        if (lista != null) {
            for (Producto p : lista) {
                String estado = p.isActivo() ? "Activo" : "Inactivo";
                
                Object[] fila = {
                    p.getId(),
                    p.getNombre(),
                    p.getCategoria().getNombre(), 
                    p.getUbicacion().getidAlm(), 
                    p.getStockActual(),
                    p.getStockMinimo(),
                    estado
                };
                modelo.addRow(fila); 
            }
        }

        tblProductos.setModel(modelo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnVolverMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        btnVolverMenu.setText("Volver al Menú");
        btnVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(btnVolverMenu)
                .addGap(144, 144, 144))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(btnVolverMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnVolverMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    // End of variables declaration//GEN-END:variables
}
