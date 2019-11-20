package tpcompiladores;

import javax.swing.*;
import java.io.File;

public class FileChooser {
    private FileChooser() {}

    public static File showFileChooser(String title) {
        return FileChooser.showFileChooser(title, false);
    }

    public static File showFileSave(String title) {
        return FileChooser.showFileChooser(title, true);
    }

    private static File showFileChooser(String title, boolean isSave) {
        final JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(title);

        int returnVal = isSave ? fc.showSaveDialog(null) : fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }

        return null;
    }
}
