import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class My_Notepad extends JFrame implements ActionListener{

    private JTabbedPane tabbedPane; // Declare JTabbedPane at the class level
    private int tabCount = 1; // Counter for naming new tabs

    My_Notepad(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);//Full Screen
        this.setTitle("My_Notepad");

        //Icon for the window
        ImageIcon notepadIcon = new ImageIcon("C:\\Users\\Asus\\Desktop\\COURSES\\JAVA\\Projects" +
                "\\Swing Projects\\Notepad Clone\\icons\\notepad.png");
        this.setIconImage(notepadIcon.getImage());

        //MenuBar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        //Menu = File
        JMenu file = new JMenu("File");
        file.setFont(new Font("Times new roman", Font.BOLD, 16));

        //Menu items for file
        JMenuItem newFile = new JMenuItem("New tab");
        newFile.addActionListener(this);
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exit.addActionListener(this);

        //Add menu-items to file menu
        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        //Menu = Edit
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("Times new roman", Font.BOLD, 16));

        //Menu items for edit
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        //Add menu-items to edit menu
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);

        //Menu = View
        JMenu view = new JMenu("View");
        view.setFont(new Font("Times new roman", Font.BOLD, 16));

        //Menu items for view
        JMenuItem zoomIn = new JMenuItem("Zoom in");
        zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));

        JMenuItem zoomOut = new JMenuItem("Zoom out");
        zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));

        //Add menu-items to view menu
        view.add(zoomIn);
        view.add(zoomOut);

        //Menu = Help
        JMenu help = new JMenu("Help");
        help.setFont(new Font("Times new roman", Font.BOLD, 16));

        //Menu items for edit
        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        //Add menu-items to help menu
        help.add(helpItem);

        //Add all menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
        menuBar.add(help);

        // Initialize the JTabbedPane
        tabbedPane = new JTabbedPane();

        // Add the initial tab
        addNewTab();
        // Add tabbedPane to the frame
        this.add(tabbedPane);


        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new My_Notepad();  //Anonymous object
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            //File Menu items
            case "New tab":
                addNewTab();
                break;

            case "Open":
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false); // do not accept all files
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only text files",
                        "txt");
                fileChooser.addChoosableFileFilter(restrict);
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        JTextArea textArea = new JTextArea();
                        textArea.setFont(new Font("San_Serif", Font.PLAIN, 18));
                        textArea.setLineWrap(true);
                        String line;
                        while ((line = reader.readLine()) != null) {
                            textArea.append(line + "\n");
                        }
                        reader.close();
                        JScrollPane scrollPane = new JScrollPane(textArea);
                        tabbedPane.addTab(selectedFile.getName(), scrollPane);
                        tabbedPane.setSelectedComponent(scrollPane); // Focus on the new tab
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "File could not be opened",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;

            case "Save":
                JFileChooser saver = new JFileChooser();
                saver.setApproveButtonText("Save"); // Show Save on JFileChooser button
                result = saver.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File saveFile = new File(saver.getSelectedFile().getAbsolutePath() + ".txt");
                    JTextArea textArea = getCurrentTextArea(); // Use helper method
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
                        textArea.write(writer); // Use JTextArea's write method
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "File could not be saved",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;

            case "Print":
                JTextArea textArea = getCurrentTextArea(); // Use helper method
                try {
                    boolean done = textArea.print();
                    if (done) {
                        JOptionPane.showMessageDialog(this, "Printing Complete",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Printing Cancelled",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Printing Failed: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Exit":
                System.exit(0); // Used to exit any running program
                break;

            //Edit Menu items.
            case "Copy":
        }
    }

    //Function to get current text area info
    private JTextArea getCurrentTextArea() {
        JScrollPane scrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
        JViewport viewport = scrollPane.getViewport();
        return (JTextArea) viewport.getView();
    }

    //Function to add new tab
    private void addNewTab() {
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("San_Serif", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        tabbedPane.addTab("Tab " + tabCount++, scrollPane);
    }
}
