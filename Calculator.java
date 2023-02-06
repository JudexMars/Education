import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator {
  private JFrame frame;
  private JTextField display;

  public Calculator() {
    frame = new JFrame("Calculator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    display = new JTextField();
    display.setEditable(false);
    frame.add(display, BorderLayout.NORTH);

    JPanel buttons = new JPanel();
    buttons.setLayout(new GridLayout(4, 4));
    frame.add(buttons, BorderLayout.CENTER);

    String[] labels = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+" };
    for (String label : labels) {
      JButton button = new JButton(label);
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          display.setText(display.getText() + label);
        }
      });
      buttons.add(button);
    }
  }

  public void show() {
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    calculator.show();
  }
}