package CodeViz;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        Main main = new Main();
        main.setSize(1200, 700);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
        main.setResizable(false);
        main.setTitle("Assignment 01");
    }

    public Main() {

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem helpItem = new JMenuItem("Help");

        fileMenu.add(openItem);
        FileBrowserPanel filePanel = new FileBrowserPanel(openItem);
        add(filePanel, BorderLayout.WEST);

        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        aboutMenu.add(aboutItem);
        helpMenu.add(helpItem);

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // add status bar
        JTextArea statusBar= new JTextArea();
        statusBar.setEditable(false);
        statusBar.setLineWrap(true);
        statusBar.setBackground(new Color(250, 250, 250));

        JScrollPane statusScrollPane = new JScrollPane(statusBar);
        statusScrollPane.setPreferredSize(new Dimension(0, 100));
        add(statusScrollPane, BorderLayout.SOUTH);

        VisualizerPanel vizPanel = new VisualizerPanel();
        add(vizPanel, BorderLayout.CENTER);

        vizPanel.updateData(0,0,"");

        // get and analyze file
        filePanel.setFolderOpenListener(firstFile -> {
            if (firstFile != null && firstFile.isFile()) {
                FileAnalyzer fileAnalyzer = new FileAnalyzer();
                fileAnalyzer.analyze(firstFile);

                int lines = fileAnalyzer.getLineCount();
                int complexity = fileAnalyzer.getComplexCount();
                String face = fileAnalyzer.getOverallCount();

                vizPanel.updateData(lines, complexity, face);

                // update status bar
                statusBar.append("Opened Folder: " + filePanel.getFile().getParent() + "\n");
                statusBar.append("Analyzing File: " + filePanel.getFile() + " ...\n");
                statusBar.append("Analysis: " + lines + " Lines | " + complexity + " Complexity | Overall - " + face + " face\n\n");
            }
        });
    }
}