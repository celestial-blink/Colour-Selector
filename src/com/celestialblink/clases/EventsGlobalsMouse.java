/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.celestialblink.clases;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

/**
 *
 * @author FKADAGIO
 */
public class EventsGlobalsMouse implements NativeMouseInputListener{
    
    public static Point point=new Point(100, 100);
    
    public EventsGlobalsMouse(){
        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }
    
    @Override
    public void nativeMouseClicked(NativeMouseEvent nme) {
        
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nme) {
        
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nme) {
        
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nme) {
        point=nme.getPoint();
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nme) {
        
    }
    
}
