/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celestialblink.ejecutables;

import com.celestialblink.clases.EventsGlobalsKey;
import com.celestialblink.clases.EventsGlobalsMouse;
import com.celestialblink.clases.MytrayIcon;
import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 *
 * @author FKADAGIO
 */
public class start_CS {
    public static MytrayIcon Mytray=null;
    public static void main(String[] args) {
        Mytray=new MytrayIcon();
        EventsGlobalsKey key=new EventsGlobalsKey();
        EventsGlobalsMouse mouse=new EventsGlobalsMouse();
        try {
            GlobalScreen.registerNativeHook();
            System.out.println("started...");
        } catch (NativeHookException nhe) {
            JOptionPane.showMessageDialog(null, nhe.getMessage());
        }
        GlobalScreen.addNativeKeyListener(key);
        GlobalScreen.addNativeMouseMotionListener(mouse);
    }
}
