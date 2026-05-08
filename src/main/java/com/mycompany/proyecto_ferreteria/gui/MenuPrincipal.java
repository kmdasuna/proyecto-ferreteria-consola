package com.mycompany.proyecto_ferreteria.gui;

import com.mycompany.proyecto_ferreteria.service.InventarioService;


public class MenuPrincipal extends javax.swing.JFrame {
    
    private InventarioService service;
    
    public MenuPrincipal() {
        initComponents();
        this.service = new InventarioService();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMenuPrincipal = new javax.swing.JLabel();
        btnVerInventario = new javax.swing.JButton();
        btnNuevoProducto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMenuPrincipal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMenuPrincipal.setText("Menú Principal");

        btnVerInventario.setText("Ver Inventario");
        btnVerInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerInventarioActionPerformed(evt);
            }
        });

        btnNuevoProducto.setText(" Nuevo Producto");
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnVerInventario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(btnNuevoProducto)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(lblMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerInventario)
                    .addComponent(btnNuevoProducto))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerInventarioActionPerformed
        Listado ventanaListado = new Listado(this.service);
        ventanaListado.setVisible(true);
    }//GEN-LAST:event_btnVerInventarioActionPerformed

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        Registro ventanaRegistro = new Registro(this.service);
        ventanaRegistro.setVisible(true);
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnVerInventario;
    private javax.swing.JLabel lblMenuPrincipal;
    // End of variables declaration//GEN-END:variables
}
