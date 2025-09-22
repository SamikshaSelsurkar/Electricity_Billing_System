package com.project.electricity;

import java.awt.BorderLayout;
import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class payment_bill extends JFrame {

    String meter;

    public payment_bill(String meter) {
        this.meter = meter;

        JFXPanel jfxPanel = new JFXPanel();

        Platform.runLater(() -> {
            WebView webView = new WebView();
            WebEngine engine = webView.getEngine();
            engine.load("https://paytm.com/online-payments"); 

            Scene scene = new Scene(webView);
            jfxPanel.setScene(scene);
        });

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            setVisible(false);
            new pay_bill(meter); 
        });

        JPanel topPanel = new JPanel();
        topPanel.add(back);

        // Frame layout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(jfxPanel, BorderLayout.CENTER);

        // Frame settings
        setSize(1000, 700);
        setLocation(200, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new payment_bill(""); // test meter
        });
    }
}
