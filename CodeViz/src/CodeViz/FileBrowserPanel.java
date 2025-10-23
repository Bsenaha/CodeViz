package CodeViz;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileBrowserPanel extends JPanel {

    private final DefaultListModel<String> fileListModel;
    private File firstFile = null;
    private FolderOpenListener folderOpenListener;

    public FileBrowserPanel(JMenuItem openItem) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 0));
        fileListModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(fileListModel);

        JScrollPane scrollPane = new JScrollPane(fileList);
        add(scrollPane);

        openItem.addActionListener(e -> {

            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            folderChooser.setCurrentDirectory(new File("user.home"));
            folderChooser.setDialogTitle("Select a Folder");

            if (folderChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                fileListModel.clear();
                File[] files = folderChooser.getSelectedFile().listFiles(file ->
                        !file.isHidden() && !file.getName().equals(".DS_Store"));

                if (files != null) {
                    for (File f : files) {
                        fileListModel.addElement(f.isDirectory() ? "[DIR]" + f.getName() : f.getName());
                    }
                }

                if (files != null && files.length > 0) firstFile = files[0];

                if (folderOpenListener != null && firstFile != null && firstFile.isFile()) {
                    folderOpenListener.folderOpened(firstFile);
                }
            }
        });
    }

    public File getFile () {
        return firstFile;
    }

    public interface FolderOpenListener {
        void folderOpened(File firstFile);
    }

    public void setFolderOpenListener(FolderOpenListener listener) {
        this.folderOpenListener = listener;
    }
}
