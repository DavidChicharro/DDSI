
package boopfest;

import java.sql.SQLException;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class AsignarPersonalAFestival extends javax.swing.JFrame {

    /** Creates new form AsignarPersonalAFestival */
    public AsignarPersonalAFestival() {
        initComponents();
        
        ArrayList<String> horas = obtenerHoras();
        for(String h : horas){
            this.cmbHEntrada.addItem(h);
            this.cmbHSalida.addItem(h);
        }
    
    }

    public ArrayList<String> obtenerHoras(){
        ArrayList<String> horas = new ArrayList<String>();
        String hora;
        String min;
        for(int i=0 ; i<24 ; i++){
            hora="";
            if (i<10)
                hora="0";
            
            hora+=Integer.toString(i);
            
            for(int j=0 ; j<2 ; j++){
                if(j==0)
                    min="00";
                else
                    min="30";
                
                horas.add(hora+":"+min);
            }
        }
        return horas;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        txtFestival = new javax.swing.JTextField();
        btnVolver = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbHEntrada = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbHSalida = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Boop Fest");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Asignar personal a festival");

        jLabel2.setText("Codígo Empleado");

        jLabel3.setText("ID Festival");

        txtEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoActionPerformed(evt);
            }
        });

        txtFestival.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFestivalActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jLabel4.setText("Hora Entrada");

        jLabel5.setText("Hora Salida");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmpleado)
                            .addComponent(txtFestival, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(cmbHEntrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHSalida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnVolver)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                                .addComponent(btnAceptar))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFestival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbHEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbHSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnAceptar))
                .addGap(20, 20, 20))
        );

        pack();
    }//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        Principal ventana = new Principal();
        ventana.show();
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String codEmpleado = this.txtEmpleado.getText();
        String idFestival = this.txtFestival.getText();
        String hEntrada = (String) this.cmbHEntrada.getSelectedItem();
        String hSalida = (String) this.cmbHSalida.getSelectedItem();

        try {
            bd.asignarPersonalFestival(codEmpleado, idFestival, hEntrada, hSalida);
        } catch (SQLException e) {
        }
        
        campoEmpleado = false;
        campoFestival = false;
        txtEmpleado.setText("");
        txtFestival.setText("");
        btnAceptar.setEnabled(false);
    
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoActionPerformed
        if(!this.txtEmpleado.getText().isEmpty())
            campoEmpleado=true;
        
        if(campoEmpleado && campoFestival)
            this.btnAceptar.setEnabled(true);
    }//GEN-LAST:event_txtEmpleadoActionPerformed

    private void txtFestivalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFestivalActionPerformed
        if(!this.txtFestival.getText().isEmpty())
            campoFestival=true;
        
        if(campoEmpleado && campoFestival)
            this.btnAceptar.setEnabled(true);
    }//GEN-LAST:event_txtFestivalActionPerformed
    
    private boolean campoEmpleado = false;
    private boolean campoFestival = false;
    
    BaseDatos bd = new BaseDatos();
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbHEntrada;
    private javax.swing.JComboBox<String> cmbHSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtFestival;
    // End of variables declaration//GEN-END:variables

}
