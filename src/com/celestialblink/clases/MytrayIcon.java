/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celestialblink.clases;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author FKADAGIO
 */
public class MytrayIcon {
    //variables
    SystemTray systemTray=null;
    TrayIcon trayIcon=null;
    //true=hex false=rgb
    private boolean RGB_HEX=true;
    
    //true=copy false=no copiar
    private boolean COPY_TO=true;
    
    public MytrayIcon(){
    inicializar();
    }
    
    public void message(String tittle, String data){
        if (trayIcon!=null){
            trayIcon.displayMessage(tittle, data, TrayIcon.MessageType.NONE);
        }
    }

    public boolean isRGB_HEX() {
        return RGB_HEX;
    }

    public void setRGB_HEX(boolean RGB_HEX) {
        this.RGB_HEX = RGB_HEX;
    }

    public boolean isCOPY_TO() {
        return COPY_TO;
    }

    public void setCOPY_TO(boolean COPY_TO) {
        this.COPY_TO = COPY_TO;
    }
    
    
    
    
    
    private void inicializar(){
        if(!SystemTray.isSupported()){
            JOptionPane.showMessageDialog(null, "no soporta trayicon");
            return;
        }
        //obteniedo el systemtray
        systemTray=SystemTray.getSystemTray();
        
        //creando objetos (PopupMenu, MenuItem, Menu) necesarios para el menu del trayicon
        PopupMenu popup=new PopupMenu();
        MenuItem salir=new MenuItem("exit");
        Menu type=new Menu("RGB/HEX");
        MenuItem rgb=new MenuItem("RGB");
        MenuItem hex=new MenuItem("HEX");
        type.add(rgb);
        type.add(hex);
        CheckboxMenuItem copy=new CheckboxMenuItem("Copy to clipboard");
        copy.setState(isCOPY_TO());
        
        //agregando los eventos
        salir.addActionListener((a)->{System.exit(0);});
        rgb.addActionListener((a)->{setRGB_HEX(false);});
        hex.addActionListener((a)->{setRGB_HEX(true);});
        copy.addItemListener((i)->{
            if (isCOPY_TO()) {
                setCOPY_TO(false);
            }else{
                setCOPY_TO(true);
            }
        });
        
        //agregando a popupmenu
        popup.add(copy);
        popup.add(type);
        popup.add(salir);
        
        trayIcon=new TrayIcon(getImage(), "Colour Selection", popup);
        
        try {
            systemTray.add(trayIcon);
            trayIcon.displayMessage("Colour Selection", "press Ctrl + alt to get color", TrayIcon.MessageType.NONE);
        } catch (AWTException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
    }
    private Image getImage(){
       Image image=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/celestialblink/imagenes/Colour selection 16px.png"));
        return image;
    }
}
