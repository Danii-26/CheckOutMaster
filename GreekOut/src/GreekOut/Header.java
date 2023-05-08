package GreekOut;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Header extends JLabel {
    public Header(String title, Color colorBackground) {
        this.setText(title);
        this.setBackground(colorBackground);
        this.setForeground(new Color(255, 255, 255));
        this.setFont(new Font("Dialog", 1, 20));
        this.setHorizontalAlignment(0);
        this.setVerticalAlignment(0);
        this.setOpaque(true);
    }
}