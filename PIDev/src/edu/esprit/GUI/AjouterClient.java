/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.GUI;

import edu.esprit.DAO.ClientDAO;

import edu.esprit.entities.Client;
import edu.esprit.util.Controle;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author DarkLord_666
 */
public class AjouterClient extends javax.swing.JFrame {

    /**
     * Creates new form AjouterClient
     */
    public AjouterClient() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nn77 = new javax.swing.JLabel();
        ok22 = new javax.swing.JLabel();
        ok11 = new javax.swing.JLabel();
        nn11 = new javax.swing.JLabel();
        ok77 = new javax.swing.JLabel();
        ok55 = new javax.swing.JLabel();
        ok44 = new javax.swing.JLabel();
        ok66 = new javax.swing.JLabel();
        ok33 = new javax.swing.JLabel();
        nn33 = new javax.swing.JLabel();
        nn44 = new javax.swing.JLabel();
        nn55 = new javax.swing.JLabel();
        nn66 = new javax.swing.JLabel();
        nn22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane8 = new javax.swing.JTextPane();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane6 = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextPane5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPane5KeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(jTextPane5);

        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Email");

        jLabel7.setText("N°cin");

        jTextPane8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPane8KeyTyped(evt);
            }
        });
        jScrollPane8.setViewportView(jTextPane8);

        jLabel1.setText("Nom");

        jTextPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPane2KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTextPane2);

        jLabel6.setText("Login");

        jTextPane4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPane4KeyTyped(evt);
            }
        });
        jScrollPane11.setViewportView(jTextPane4);

        jTextPane6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPane6KeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(jTextPane6);

        jTextPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextPane1KeyTyped(evt);
            }
        });
        jScrollPane9.setViewportView(jTextPane1);

        jLabel4.setText("Adresse");

        jLabel8.setText("Mot de passe");

        jLabel3.setText("Date Naissance");

        jButton2.setText("Quiter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Prenom");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 168, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 11, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 22, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(290, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ok77)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nn77))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ok11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nn11))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ok22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nn22))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ok44)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nn44))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ok55)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nn55))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ok66)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nn66))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ok33)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nn33)))
                    .addGap(89, 89, 89)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ok11)
                        .addComponent(nn11))
                    .addGap(36, 36, 36)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ok22)
                        .addComponent(nn22))
                    .addGap(43, 43, 43)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ok66)
                                .addComponent(nn66))
                            .addGap(6, 6, 6))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nn33))
                                .addComponent(ok33))
                            .addGap(49, 49, 49)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ok44)
                                .addComponent(nn44))
                            .addGap(13, 13, 13)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(nn55)
                                .addComponent(ok55))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nn77)
                        .addComponent(ok77))
                    .addGap(81, 81, 81)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String Nom =jTextPane1.getText();
        String Prenom =jTextPane2.getText();

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        String Date_naiss= jDateChooser1.getDate().toString();
        String Adresse =jTextPane4.getText();
        String email =jTextPane5.getText();
        int cin =Integer.parseInt(jTextPane6.getText());
        String login =jTextField1.getText();
        String pwd =jTextPane8.getText();

        Client cc=new Client();
        ClientDAO dao=new ClientDAO();

        int num = String.valueOf(cin).length();

        if(num==8 )

        {if(dao.findClientbylogin(login)!=null && dao.findClientbylogin(login).getLogin()==null)
            {

                cc.setCin(cin);
                cc.setDate_naiss(Date_naiss);
                cc.setEmail(email);
                cc.setLogin(login);
                cc.setPwd(pwd);
                cc.setAdresse(Adresse);
                cc.setNom(Nom);
                cc.setPrenom(Prenom);
                dao.insertClient(cc);
                JOptionPane.showMessageDialog(this, "Ajout effectué avec succès");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Ajout refuser !Login Existant");
            }

        }
        else
        {
            JOptionPane.showMessageDialog(this, "Ajout refuser !Veuillez vérifier le numéro de cin");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextPane8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane8KeyTyped
        Controle c = new Controle();
        boolean result = c.controlestring(jTextPane8.getText());
        System.out.println("" + result);
        if (result == true) {
            mdp = true;
            ok77.setVisible(true);
            nn77.setVisible(false);
        } else if (result == false) {
            mdp = false;
            ok77.setVisible(false);
            nn77.setVisible(true);
        } else if (jTextPane8.getText() == null) {
            mdp = false;
            ok77.setVisible(false);
            nn77.setVisible(true);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_jTextPane8KeyTyped

    private void jTextPane6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane6KeyTyped
        Controle c = new Controle();
        boolean result = c.controlenumber(jTextPane6.getText());
        System.out.println("" + result);
        if (result == true) {
            cin = true;
            ok55.setVisible(true);
            nn55.setVisible(false);
        } else if (result == false) {
            cin = false;
            ok55.setVisible(false);
            nn55.setVisible(true);
        } else if (jTextPane6.getText() == null) {
            cin = false;
            ok55.setVisible(false);
            nn55.setVisible(true);
        } // TODO add your handling code here:
    }//GEN-LAST:event_jTextPane6KeyTyped

    private void jTextPane5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane5KeyTyped
        Controle c = new Controle();
        boolean result = c.controlemail(jTextPane5.getText());
        System.out.println("" + result);
        if (result == true) {
            email = true;
            ok44.setVisible(true);
            nn44.setVisible(false);
        } else if (result == false) {
            email = false;
            ok44.setVisible(false);
            nn44.setVisible(true);
        } else if (jTextPane5.getText() == null) {
            email = false;
            ok44.setVisible(false);
            nn44.setVisible(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jTextPane5KeyTyped

    private void jTextPane2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane2KeyTyped
        Controle c = new Controle();
        boolean result = c.controlestring(jTextPane2.getText());
        System.out.println("" + result);
        if (result == true) {
            prenom = true;
            ok22.setVisible(true);
            nn22.setVisible(false);
        } else if (result == false) {
            prenom = false;
            ok22.setVisible(false);
            nn22.setVisible(true);
        } else if (jTextPane2.getText() == null) {
            prenom = false;
            ok22.setVisible(false);
            nn22.setVisible(true);
        }
    }//GEN-LAST:event_jTextPane2KeyTyped

    private void jTextPane1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane1KeyTyped
        Controle c = new Controle();
        boolean result = c.controlestring(jTextPane1.getText());
        System.out.println("" + result);
        if (result == true) {
            nom = true;
            ok11.setVisible(true);
            nn11.setVisible(false);
        } else if (result == false) {
            nom = false;
            ok11.setVisible(false);
            nn11.setVisible(true);
        } else if (jTextPane1.getText() == null) {
            nom = false;
            ok11.setVisible(false);
            nn11.setVisible(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPane1KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        Controle c = new Controle();
        boolean result = c.controlelogin(jTextField1.getText());
        System.out.println("" + result);
        if (result == true) {
            login = true;
            ok66.setVisible(true);
            nn66.setVisible(false);
        } else if (result == false) {
            login = false;
            ok66.setVisible(false);
            nn66.setVisible(true);
        } else if (jTextField1.getText() == null) {
            login = false;
            ok66.setVisible(false);
            nn66.setVisible(true);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextPane4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane4KeyTyped
        Controle c = new Controle();
        boolean result = c.controlestring(jTextPane4.getText());
        System.out.println("" + result);
        if (result == true) {
            adresse = true;
            ok33.setVisible(true);
            nn33.setVisible(false);
        } else if (result == false) {
            adresse = false;
            ok33.setVisible(false);
            nn33.setVisible(true);
        } else if (jTextPane4.getText() == null) {
            adresse = false;
            ok33.setVisible(false);
            nn33.setVisible(true);
        }     // TODO add your handling code here:
    }//GEN-LAST:event_jTextPane4KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        dispose();
       
       this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
         try //nouvelle interface SyntheticaClassyLookAndFeel
    {
      UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
    } 
    catch (Exception e) 
    {
      e.printStackTrace();
    }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AjouterClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane jTextPane6;
    private javax.swing.JTextPane jTextPane8;
    private javax.swing.JLabel nn11;
    private javax.swing.JLabel nn22;
    private javax.swing.JLabel nn33;
    private javax.swing.JLabel nn44;
    private javax.swing.JLabel nn55;
    private javax.swing.JLabel nn66;
    private javax.swing.JLabel nn77;
    private javax.swing.JLabel ok11;
    private javax.swing.JLabel ok22;
    private javax.swing.JLabel ok33;
    private javax.swing.JLabel ok44;
    private javax.swing.JLabel ok55;
    private javax.swing.JLabel ok66;
    private javax.swing.JLabel ok77;
    // End of variables declaration//GEN-END:variables
    public String message = "";
    public boolean nom = true;
    public boolean prenom = true;
    public boolean mdp = true;
    public boolean login = true;
    public boolean email = true;
    public boolean adresse = true;
     public boolean cin = true;
}