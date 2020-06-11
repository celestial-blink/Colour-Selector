/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celestialblink.clases;

import com.celestialblink.ejecutables.start_CS;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author FKADAGIO
 */
public class EventsGlobalsKey implements  NativeKeyListener{
    
    private short[] keys={0,0,0};
    private Robot robot=null;
    private Color color=null;
    private String dataInfo="";
    private Clipboard cb=null;

    public EventsGlobalsKey(){
        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }
    
    private String toHex(int n){
    String number=Integer.toHexString(n);
    return (n<10)?"0"+n:number.toUpperCase();
    }
    
    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        if((int)keys[2]<2){
        keys[keys[2]]=(short)nke.getKeyCode();
        keys[2]+=1;
        }        
        if (keys[0]==29&&keys[1]==56 || keys[1]==29&&keys[0]==56){
            dataInfo="";
            try {
                robot=new Robot();
                color=robot.getPixelColor((int)EventsGlobalsMouse.point.getX(),(int)EventsGlobalsMouse.point.getY());
                if(start_CS.Mytray.isRGB_HEX()){
                dataInfo+="#";
                dataInfo+=toHex(color.getRed());
                dataInfo+=toHex(color.getGreen());
                dataInfo+=toHex(color.getBlue());
                }else{
                dataInfo+=color.getRed()+",";
                dataInfo+=color.getGreen()+",";
                dataInfo+=color.getBlue();
                }
                if (start_CS.Mytray.isCOPY_TO()) {
                    cb=Toolkit.getDefaultToolkit().getSystemClipboard();
                    cb.setContents(new StringSelection(dataInfo), null);
                    dataInfo+="   COPIED";
                }
                start_CS.Mytray.message("COLOR", dataInfo);
            } catch (AWTException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }finally{
                if (robot!=null){
                    robot=null;
                }
                if (color!=null) {
                    color=null;
                }
                if (cb!=null) {
                    cb=null;
                }
            }
            
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        keys[0]=0;
        keys[1]=0;
        keys[2]=0;       
    }
    
}
