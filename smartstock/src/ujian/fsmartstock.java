
package ujian;
import static ujian.dbkoneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adolb
 */
public class fsmartstock extends javax.swing.JFrame {
DefaultTableModel dtm = new DefaultTableModel();
 boolean isTambahMode = false;
 private boolean isUbahMode = false;
    /**
     * Creates new form fMahasiswa
     * @throws java.sql.SQLException
     */
    public fsmartstock() throws SQLException {
    initComponents(); 

    DefaultTableModel1.setModel(dtm); 

    dtm.addColumn("ID BARANG");
    dtm.addColumn("NAMA BARANG");
    dtm.addColumn("STOCK");
    dtm.addColumn("HARGA");
    dtm.addColumn("TANGGAL MASUK");
    dtm.addColumn("TANGGAL KELUAR");

    lsDtbrg();
    fieldEnabled(false);
    tombol(false);
    CTAMBAH.setEnabled(true);
    CUBAH.setEnabled(true);
    CHAPUS.setEnabled(true);
    CTUTUP.setEnabled(true);
    CTAMPILKAN.setEnabled(true);
}

    private void fieldEnabled(boolean opsi) {
        TXID.setEditable(opsi);
        TXNAMABARANG.setEditable(opsi);
        TXSTOCK.setEditable(opsi);
        TXHARGA.setEditable(opsi);
        jDateChooser1.setEnabled(opsi);
        jDateChooser2.setEnabled(opsi);
    }

    private void tombol(boolean opsi) {
        CTAMBAH.setEnabled(opsi);
        CUBAH.setEnabled(opsi);
        CHAPUS.setEnabled(opsi);
        CTUTUP.setEnabled(opsi);
        CTAMPILKAN.setEnabled(opsi);
    }

    private void clearForm() {
        TXID.setText("");
        TXNAMABARANG.setText("");
        TXSTOCK.setText("");
        TXHARGA.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
    }

    private void lsDtbrg() throws SQLException {
        Connection cnn = koneksi();
        dtm.getDataVector().removeAllElements();
        dtm.fireTableDataChanged();
        if (!cnn.isClosed()) {
            PreparedStatement ps = cnn.prepareStatement("SELECT * FROM barang;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] dta = new Object[6];
                dta[0] = rs.getString("idbarang");
                dta[1] = rs.getString("namabarang");
                dta[2] = rs.getString("stok");
                dta[3] = rs.getString("harga");
                dta[4] = rs.getString("tanggalmasuk");
                dta[5] = rs.getString("tanggalkeluar");
                dtm.addRow(dta);
            }
            cnn.close();
        }
    }
        private void storeData() throws SQLException {
    Connection cnn = koneksi();
    if (!cnn.isClosed()) {
        PreparedStatement ps = cnn.prepareStatement("INSERT INTO barang(idbarang, namabarang, stok, harga, tanggalmasuk, tanggalkeluar) VALUES (?, ?, ?, ?, ?, ?)");

        ps.setString(1, TXID.getText());
        ps.setString(2, TXNAMABARANG.getText());
        ps.setInt(3, Integer.parseInt(TXSTOCK.getText()));
        ps.setDouble(4, Double.parseDouble(TXHARGA.getText())); 
        

        java.util.Date tglMasuk = jDateChooser1.getDate();
        java.util.Date tglKeluar = jDateChooser2.getDate();

        
        if (tglMasuk == null || tglKeluar == null) {
            JOptionPane.showMessageDialog(this, "Tanggal masuk dan keluar harus diisi!");
            return;
        }

        ps.setDate(5, new java.sql.Date(tglMasuk.getTime()));
        ps.setDate(6, new java.sql.Date(tglKeluar.getTime()));

        ps.executeUpdate();
        cnn.close();

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
    }
}

    private void updateData() throws SQLException {
    Connection cnn = koneksi();
    if (!cnn.isClosed()) {
        PreparedStatement ps = cnn.prepareStatement(
            "UPDATE barang SET namabarang=?, stok=?, harga=?, tanggalmasuk=?, "
                    + "tanggalkeluar=? WHERE idbarang=?"
        );

        ps.setString(1, TXNAMABARANG.getText());
        ps.setInt(2, Integer.parseInt(TXSTOCK.getText()));
        ps.setDouble(3, Double.parseDouble(TXHARGA.getText()));

        java.util.Date tglMasuk = jDateChooser1.getDate();
        java.util.Date tglKeluar = jDateChooser2.getDate();

        if (tglMasuk == null || tglKeluar == null) {
            JOptionPane.showMessageDialog(this, "Tanggal masuk dan keluar harus diisi!");
            return;
        }

        ps.setDate(4, new java.sql.Date(tglMasuk.getTime()));
        ps.setDate(5, new java.sql.Date(tglKeluar.getTime()));

        ps.setString(6, TXID.getText()); 

        ps.executeUpdate();
        cnn.close();

        JOptionPane.showMessageDialog(this, "Data berhasil diperbarui.");
    }
}

   private void destroyData() throws SQLException {
    Connection cnn = koneksi();
    if (!cnn.isClosed()) {
        PreparedStatement ps = cnn.prepareStatement("DELETE FROM barang WHERE idbarang=?;");
        ps.setString(1, TXID.getText());
        ps.executeUpdate();
        cnn.close();

        JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
    }
}

    /**
     * Creates new form fsmartstock
     */
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TXID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TXSTOCK = new javax.swing.JTextField();
        TXNAMABARANG = new javax.swing.JTextField();
        TXHARGA = new javax.swing.JTextField();
        CTAMBAH = new javax.swing.JButton();
        CUBAH = new javax.swing.JButton();
        CHAPUS = new javax.swing.JButton();
        CTUTUP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DefaultTableModel1 = new javax.swing.JTable();
        CTAMPILKAN = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setText("SMARTSTOCK");

        jLabel3.setText("IDBARANG");

        jLabel4.setText("NAMABARANG");

        jLabel5.setText("STOCK");

        jLabel6.setText("TANGGAL MASUK");

        jLabel7.setText("HARGA");

        jLabel8.setText("TANGGAL KELUAR");

        TXHARGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXHARGAActionPerformed(evt);
            }
        });

        CTAMBAH.setBackground(new java.awt.Color(0, 255, 51));
        CTAMBAH.setText("Tambah");
        CTAMBAH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CTAMBAHActionPerformed(evt);
            }
        });

        CUBAH.setBackground(new java.awt.Color(204, 255, 51));
        CUBAH.setText("Ubah");
        CUBAH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUBAHActionPerformed(evt);
            }
        });

        CHAPUS.setBackground(new java.awt.Color(255, 0, 0));
        CHAPUS.setText("Hapus");
        CHAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHAPUSActionPerformed(evt);
            }
        });

        CTUTUP.setBackground(new java.awt.Color(255, 153, 0));
        CTUTUP.setText("Tutup");
        CTUTUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CTUTUPActionPerformed(evt);
            }
        });

        DefaultTableModel1.setBackground(new java.awt.Color(153, 255, 204));
        DefaultTableModel1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Idbarang", "namabarang", "stock", "harga", "tanggal masuk", "tanggal keluar"
            }
        ));
        jScrollPane1.setViewportView(DefaultTableModel1);

        CTAMPILKAN.setBackground(new java.awt.Color(0, 204, 204));
        CTAMPILKAN.setText("Tampilkan");
        CTAMPILKAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CTAMPILKANActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6))
                    .addComponent(jLabel8))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(CTAMBAH)
                            .addGap(35, 35, 35)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(CUBAH)
                                    .addGap(32, 32, 32)
                                    .addComponent(CHAPUS)
                                    .addGap(31, 31, 31)
                                    .addComponent(CTUTUP)
                                    .addGap(18, 18, 18)
                                    .addComponent(CTAMPILKAN))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(TXSTOCK, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TXHARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXNAMABARANG, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(TXID, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TXID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TXNAMABARANG, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TXSTOCK, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXHARGA, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CTAMBAH)
                    .addComponent(CUBAH)
                    .addComponent(CHAPUS)
                    .addComponent(CTUTUP)
                    .addComponent(CTAMPILKAN))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXHARGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXHARGAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXHARGAActionPerformed

    private void CTAMBAHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CTAMBAHActionPerformed
    if (!isTambahMode) {
        isTambahMode = true;
        fieldEnabled(true);
        clearForm();
        CTAMBAH.setText("Simpan");
        CUBAH.setEnabled(false);
        CHAPUS.setEnabled(false);
        CTAMPILKAN.setEnabled(false);
    } else {
        // Validasi form
        if (TXID.getText().isEmpty() || TXNAMABARANG.getText().isEmpty() ||
            TXSTOCK.getText().isEmpty() || TXHARGA.getText().isEmpty() ||
            jDateChooser1.getDate() == null || jDateChooser2.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        try (Connection conn = dbkoneksi.koneksi()) {
            String sql = "INSERT INTO barang (idbarang, namabarang, stok, harga, tanggalmasuk,"
                    + " tanggalkeluar) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, TXID.getText());
            pst.setString(2, TXNAMABARANG.getText());
            pst.setInt(3, Integer.parseInt(TXSTOCK.getText()));
            pst.setDouble(4, Double.parseDouble(TXHARGA.getText()));
            pst.setDate(5, new java.sql.Date(jDateChooser1.getDate().getTime()));
            pst.setDate(6, new java.sql.Date(jDateChooser2.getDate().getTime()));
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            lsDtbrg(); // Refresh tabel
            clearForm();
            fieldEnabled(false);

            // Kembali ke mode awal
            isTambahMode = false;
            CTAMBAH.setText("Tambah");
            CUBAH.setEnabled(true);
            CHAPUS.setEnabled(true);
            CTAMPILKAN.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + e.getMessage());
        }
    }


    }//GEN-LAST:event_CTAMBAHActionPerformed

    private void CUBAHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUBAHActionPerformed
              int selectedRow = DefaultTableModel1.getSelectedRow();

    if (!isUbahMode) {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel terlebih dahulu!");
            return;
        }

        TXID.setText(dtm.getValueAt(selectedRow, 0).toString());
        TXNAMABARANG.setText(dtm.getValueAt(selectedRow, 1).toString());
        TXSTOCK.setText(dtm.getValueAt(selectedRow, 2).toString());
        TXHARGA.setText(dtm.getValueAt(selectedRow, 3).toString());

        TXID.setEditable(false);
        TXNAMABARANG.setEditable(false);
        jDateChooser1.setEnabled(false);
        jDateChooser2.setEnabled(false);
        TXSTOCK.setEditable(true);
        TXHARGA.setEditable(true);

        isUbahMode = true;
        CUBAH.setText("Simpan Ubah");
        CTAMBAH.setEnabled(false);
        CHAPUS.setEnabled(false);
        CTAMPILKAN.setEnabled(false);
    } else {
        if (TXSTOCK.getText().isEmpty() || TXHARGA.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Stok dan Harga tidak boleh kosong!");
            return;
        }

        try (Connection conn = dbkoneksi.koneksi()) {
            String sql = "UPDATE barang SET stok=?, harga=? WHERE idbarang=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(TXSTOCK.getText()));
            pst.setDouble(2, Double.parseDouble(TXHARGA.getText()));
            pst.setString(3, TXID.getText());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Stok dan harga berhasil diubah!");

            lsDtbrg(); 
            clearForm();
            fieldEnabled(false);

            isUbahMode = false;
            CUBAH.setText("Ubah");
            CTAMBAH.setEnabled(true);
            CHAPUS.setEnabled(true);
            CTAMPILKAN.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah: " + e.getMessage());
        }
    }

    }//GEN-LAST:event_CUBAHActionPerformed

    private void CTUTUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CTUTUPActionPerformed
       int confirm = JOptionPane.showConfirmDialog(this, "Apakah kau mau mati?", "Konfirmasi Keluar", JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        dispose(); 
    }

    }//GEN-LAST:event_CTUTUPActionPerformed

    private void CTAMPILKANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CTAMPILKANActionPerformed
       try {
        lsDtbrg(); 
        JOptionPane.showMessageDialog(this, "Data berada di bawa button button!");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal menampilkan data: " + ex.getMessage());
    }
    }//GEN-LAST:event_CTAMPILKANActionPerformed

    private void CHAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHAPUSActionPerformed
                                       
    int selectedRow = DefaultTableModel1.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus dari tabel!");
        return;
    }

    String idBarang = dtm.getValueAt(selectedRow, 0).toString(); // ambil ID dari baris terpilih

    int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin menghapus data dengan ID: " + idBarang + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try (Connection conn = dbkoneksi.koneksi()) {
            String sql = "DELETE FROM barang WHERE idbarang = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, idBarang);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            lsDtbrg(); // Refresh tabel
            clearForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + e.getMessage());
        }
    }


    }//GEN-LAST:event_CHAPUSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fsmartstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fsmartstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fsmartstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fsmartstock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new fsmartstock().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(fsmartstock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CHAPUS;
    private javax.swing.JButton CTAMBAH;
    private javax.swing.JButton CTAMPILKAN;
    private javax.swing.JButton CTUTUP;
    private javax.swing.JButton CUBAH;
    private javax.swing.JTable DefaultTableModel1;
    private javax.swing.JTextField TXHARGA;
    private javax.swing.JTextField TXID;
    private javax.swing.JTextField TXNAMABARANG;
    private javax.swing.JTextField TXSTOCK;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JTable tbrg;

}
