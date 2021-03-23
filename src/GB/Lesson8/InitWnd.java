package GB.Lesson8;

import javax.swing.*;
import java.awt.*;

public class InitWnd extends JFrame {
    public InitWnd() {
        setTitle("Выбор");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new FlowLayout());

        setBounds(200, 200, 200, 150);
        JPanel panXO = new JPanel();
        JLabel lblXO = new JLabel("Чьих будешь?");
        JRadioButton radioX = setInitXO("X", true, Settings.SignType.X);
        JRadioButton radioO = setInitXO("O", false, Settings.SignType.O);
        panXO.add(lblXO);
        panXO.add(radioX);
        panXO.add(radioO);

        ButtonGroup group = new ButtonGroup();
        group.add(radioX);
        group.add(radioO);

        add(panXO);

        JPanel panSize = new JPanel();

        JLabel lblSize = new JLabel("Размер");


        JTextField textField = new JTextField("5");
        textField.setPreferredSize(new Dimension(100, 20));

        panSize.add(lblSize);
        panSize.add(textField);
        add(panSize);

        JButton btnApply = new JButton("Apply");
        btnApply.setPreferredSize(new Dimension(100, 20));
        btnApply.addActionListener(e -> {
            var txt = textField.getText();
            if (txt.matches("[0-9]+")){
                Settings.SizeMap = Integer.parseInt(txt);
                new FrameWindow();
                this.dispose();
            }

            else textField.setText("Incorrect!");
        });
        add(btnApply);
        setVisible(true);
    }

    private JRadioButton setInitXO(String title, boolean selected, Settings.SignType signType) {
        JRadioButton rb = new JRadioButton(title, selected);
        rb.addActionListener(e -> Settings.userSign = signType);
        return rb;
    }
}
//fillMap();