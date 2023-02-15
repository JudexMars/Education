import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class TextEditor {
  private JFrame frame;
  private JTextArea textArea;
  private JFileChooser fileChooser;

  public TextEditor() {
    frame = new JFrame("Text Editor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    frame.add(scrollPane, BorderLayout.CENTER);

    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    JMenu fileMenu = new JMenu("File");
    menuBar.add(fileMenu);

    JMenuItem openItem = new JMenuItem("Open");
    fileMenu.add(openItem);
    openItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        openFile();
      }
    });

    JMenuItem saveItem = new JMenuItem("Save");
    fileMenu.add(saveItem);
    saveItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveFile();
      }
    });

    JMenuItem exitItem = new JMenuItem("Exit");
    fileMenu.add(exitItem);
    exitItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
  }

  private void openFile() {
    int result = fileChooser.showOpenDialog(frame);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        textArea.read(new FileReader(file), null);
      } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private void saveFile() {
    int result = fileChooser.showSaveDialog(frame);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        textArea.write(new FileWriter(file));
      } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public void show() {
    frame.setSize(600, 400);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    editor.show();
  }
}
