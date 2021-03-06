
package GUI;

import Model.Appearances;
import Model.SistemaSolar;
import javax.media.j3d.Canvas3D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;


public class ControlWindow extends JFrame {
  private SistemaSolar universe;

  public ControlWindow(Canvas3D canvas1, Canvas3D canvas2, SistemaSolar anUniverse) {
    super();
    universe = anUniverse;
    initComponents();
    setLocation (920, 100);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        closeApp(0);
      }
    });
    Visualization visualization = new Visualization (this, false, canvas1,0);
    visualization.setVisible(true);
    Visualization visualization2 = new Visualization (this, false, canvas2,1);
    visualization2.setVisible(true);
    pack();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        primitiveGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        camaraPanel = new javax.swing.JPanel();
        PersCam = new javax.swing.JRadioButton();
        NaveCam = new javax.swing.JRadioButton();
        LunaCam = new javax.swing.JRadioButton();
        commandsPanel = new javax.swing.JPanel();
        exitApp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Control");
        setMinimumSize(new java.awt.Dimension(300, 345));
        getContentPane().setLayout(new java.awt.GridLayout(5, 0));

        camaraPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Primitivas"));
        primitiveGroup.add(PersCam);
        PersCam.setSelected(true);
        PersCam.setText("Perspectiva");
        PersCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PersCamActionPerformed(evt);
            }
        });
        primitiveGroup.add(NaveCam);
        NaveCam.setText("Nave");
        NaveCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NaveCamActionPerformed(evt);
            }
        });
        primitiveGroup.add(LunaCam);
        LunaCam.setText("Luna");
        LunaCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LunaCamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout camaraPanelLayout = new javax.swing.GroupLayout(camaraPanel);
        camaraPanel.setLayout(camaraPanelLayout);
        camaraPanelLayout.setHorizontalGroup(
            camaraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, camaraPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(NaveCam, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LunaCam, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, camaraPanelLayout.createSequentialGroup()
                .addContainerGap(128, Short.MAX_VALUE)
                .addComponent(PersCam, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        camaraPanelLayout.setVerticalGroup(
            camaraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(camaraPanelLayout.createSequentialGroup()
                .addGroup(camaraPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LunaCam)
                    .addComponent(NaveCam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PersCam))
        );

        jPanel2.add(camaraPanel);

        getContentPane().add(jPanel2);

        commandsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        exitApp.setText("Salir");
        exitApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitAppActionPerformed(evt);
            }
        });
        commandsPanel.add(exitApp);

        getContentPane().add(commandsPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

  private void exitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitAppActionPerformed
    // TODO add your handling code here:
    closeApp(0);
  }//GEN-LAST:event_exitAppActionPerformed

    private void PersCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PersCamActionPerformed
        // TODO add your handling code here:
        universe.desactivarCamLuna();
        universe.desactivarCamNave();
        universe.activarCamPers();
    }//GEN-LAST:event_PersCamActionPerformed

    private void NaveCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NaveCamActionPerformed
        // TODO add your handling code here:
        universe.desactivarCamPers();
        universe.desactivarCamLuna();
        universe.activarCamNave();
    }//GEN-LAST:event_NaveCamActionPerformed

    private void LunaCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LunaCamActionPerformed
        // TODO add your handling code here:
        universe.desactivarCamPers();
        universe.desactivarCamNave();
        universe.activarCamLuna();
    }//GEN-LAST:event_LunaCamActionPerformed

  public void showWindow () {
    setVisible (true);
  }

  void closeApp (int code) {
    universe.closeApp(code);
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton LunaCam;
    private javax.swing.JRadioButton NaveCam;
    private javax.swing.JRadioButton PersCam;
    private javax.swing.JPanel camaraPanel;
    private javax.swing.JPanel commandsPanel;
    private javax.swing.JButton exitApp;
    private javax.swing.JPanel jPanel2;
    private javax.swing.ButtonGroup primitiveGroup;
    // End of variables declaration//GEN-END:variables
}
