import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help_About extends JDialog implements ActionListener {

    Help_About(JFrame parent) {
        super(parent, "Vinay Bagde's JavaNotePad", true); // Set modal to true
        this.setBounds(400, 100, 650, 570);
        this.setLayout(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.toFront();

        ImageIcon windowIcon = new ImageIcon("C:\\Users\\Asus\\Desktop\\COURSES\\JAVA\\Projects\\Swing Projects" +
                "\\Notepad Clone\\icons\\Help.png");
        this.setIconImage(windowIcon.getImage());

        ImageIcon banner = new ImageIcon("C:\\Users\\Asus\\Desktop\\COURSES\\JAVA\\Projects\\Swing Projects\\" +
                "Notepad Clone\\icons\\banner.jpg");
        Image image = banner.getImage().getScaledInstance(300, 80, Image.SCALE_DEFAULT); // Resizing the image
        ImageIcon finalBanner = new ImageIcon(image); // Store it in another ImageIcon instance and then use.
        JLabel headerIcon = new JLabel(finalBanner);
        headerIcon.setBounds(150, 30, 300, 80); // Set the bounds for the JLabel

        JLabel content = new JLabel("<html><h1>JavaNotePad: Your Ultimate Java Notepad</h1>"
                + "<h2>Build By Vinay Bagde</h2>"
                + "<p>Welcome to JavaNotePad, an intuitive and feature-rich notepad application built with Java Swing " +
                "and AWT. This project aims to recreate the classic notepad experience with modern enhancements for " +
                "smooth and efficient note-taking.</p>"
                + "<h2>Key Features:</h2>"
                + "<ul>"
                + "<li><b>Multi-tabbed Editing:</b> Work on multiple documents within a single window.</li>"
                + "<li><b>File Operations:</b> Open, save, and create new files with ease.</li>"
                + "<li><b>Print Functionality:</b> Print your notes directly from the application.</li>"
                + "<li><b>Text Operations:</b> Cut, copy, paste, and select all with simple shortcuts.</li>"
                + "<li><b>Zoom Controls:</b> Easily zoom in and out to adjust your view.</li>"
                + "<li><b>User-Friendly Interface:</b> Clean, intuitive, and easy to navigate.</li>"
                + "</ul>"
                + "<span>Checkout the GitHub Repository : click 👉</span>"
                + "<a href=\"https://www.w3schools.com\">&nbsp;JavaNotePad GitHub Repo Link</a>"
                + "<p>Dive into JavaNotePad and make your note-taking journey seamless and enjoyable.</p></html>");

        content.setBounds(20, 100, 600, 400); // Set the bounds for the content JLabel
        content.setFont(new Font("Arial", Font.PLAIN, 14));

        // Button to close the window
        JButton button = new JButton("Close");
        button.setBounds(420, 475, 120, 30);
        button.setFocusable(false);
        button.setBackground(new Color(0xE43232));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(this);

        this.add(button);
        this.add(headerIcon);
        this.add(content);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose(); // Close the window
    }
}
