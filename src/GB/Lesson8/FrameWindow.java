package GB.Lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FrameWindow extends JFrame {
    public static void main(String[] args) {
        new InitWnd();
    }

    public FrameWindow() {
        setTitle("Game Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(new GridLayout(Settings.SizeMap, Settings.SizeMap));

        setBounds(10, 10, 50 * Settings.SizeMap, 50 * Settings.SizeMap);


        addButtons();

    }
    static void removeListener(JButton button){
        for (ActionListener actionListener : button.getActionListeners()) {
            button.removeActionListener(actionListener);
        }
    }
    static Map<String,JButton> btnMap = new HashMap<String,JButton>();
    void addButtons() {
        for (int i = 0; i < Settings.SizeMap; i++) {
            for (int j = 0; j < Settings.SizeMap; j++) {
                JButton button = new JButton("");
                button.setFont(new Font("Default", Font.BOLD | Font.ITALIC, 16));
                button.setName(i + "_" + j);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeListener(button);
                        Executes.turnUser(button.getName());
                        button.setText(Settings.userSign.toString());
                    }
                });
                add(button);
                btnMap.put(button.getName(),button);

            }
        }
        Executes.fillMap();

    }
}
