package sn.l2gl.suunu.daara.view;

import sn.l2gl.suunu.daara.controller.TalibeController;

import javax.swing.*;

public class AppDaara extends JFrame {
    //Parce que appDaara est  une fenêtre, pas conteneur conteneur de composants.
    private TalibeView talibeView;
    public AppDaara(){
        setTitle("Gestion Daara");
        setSize(800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//sert à centrer la fenêtre à l’écran

        talibeView =new TalibeView();
        new TalibeController(talibeView);

        setContentPane(talibeView);
        setVisible(true);
    }
    public static void main(String[] args){
        new AppDaara();
    }
}
