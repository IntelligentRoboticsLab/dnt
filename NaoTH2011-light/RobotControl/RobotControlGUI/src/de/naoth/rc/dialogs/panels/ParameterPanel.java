/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.naoth.rc.dialogs.panels;

/**
 *
 * @author auke
 */

import de.naoth.rc.server.Command;
import de.naoth.rc.server.CommandSender;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.Init;
import net.xeoh.plugins.base.annotations.injections.InjectPlugin;

/**
 *
 * @author  Auke Wiggers
 */
@PluginImplementation
public class ParameterPanel extends javax.swing.JPanel
{

  @InjectPlugin
  // TODO quick hack, should check if parent is really a commandsender
  public CommandSender parent = (CommandSender)this.getParent();
  private Command commandToExecute;
  //private String defaultConfigureFilePath;

  /** Creates new form ParameterPanel */
  public ParameterPanel()
  {
    initComponents();

    jTextArea.addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed(KeyEvent e)
      {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER)
        {
          sendParameters();

          int k = jTextArea.getCaretPosition();
          if(k > 0)
            jTextArea.setCaretPosition(k-1);
        }
      }
    });
  }//end constructor

  @Init
  public void init()
  {
//    defaultConfigureFilePath = parent.getMainDirectory().getParentFile().getAbsolutePath() +
//      "/NaoController/";
  }                                       
                         
private void sendParameters() {
    Command cmd = new Command("ParameterList:"+jComboBox.getSelectedItem().toString() + ":set");

    String text = this.jTextArea.getText();

    text = text.replaceAll("( |\t)+", "");
    String[] lines = text.split("(\n)+");
    for (String l : lines)
    {
      String[] splitted = l.split("=");
      if (splitted.length == 2)
      {
        String key = splitted[0].trim();
        String value = splitted[1].trim();
        // remove the last ;
        if (value.charAt(value.length() - 1) == ';')
        {
          value = value.substring(0, value.length() - 1);
        }

        cmd.addArg(key, value);
      }
    }//end for
    parent.sendCommand(cmd);

    // update everything
    //listParameters();

    // this is better, but less robust
    getParameterList();
}   

private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt)                                              
{                                                  
  getParameterList();
}                                                 

private void getParameterList() 
{
  if (jComboBox.getSelectedItem() != null)
  {
    Command cmd = new Command("ParameterList:" + jComboBox.getSelectedItem().toString() + ":get");
    parent.sendCommand(cmd);
  }
}//end refresh

public Command getCurrentCommand()
{
  return commandToExecute;
}
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jToolBar = new javax.swing.JToolBar();
        jLabel = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox();
        jButtonGet = new javax.swing.JButton();
        jButtonSet = new javax.swing.JButton();

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jToolBar.setRollover(true);

        jLabel.setText("list");
        jToolBar.add(jLabel);

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jToolBar.add(jComboBox);

        jButtonGet.setText("Get");
        jButtonGet.setFocusable(false);
        jButtonGet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonGetMouseClicked(evt);
            }
        });
        jToolBar.add(jButtonGet);

        jButtonSet.setText("Set");
        jButtonSet.setFocusable(false);
        jButtonSet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSetMouseClicked(evt);
            }
        });
        jToolBar.add(jButtonSet);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGetMouseClicked
        sendParameters();
    }//GEN-LAST:event_jButtonGetMouseClicked

    private void jButtonSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSetMouseClicked
        getParameterList();
    }//GEN-LAST:event_jButtonSetMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGet;
    private javax.swing.JButton jButtonSet;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JLabel jLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JToolBar jToolBar;
    // End of variables declaration//GEN-END:variables

}
