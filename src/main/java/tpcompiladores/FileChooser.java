package tpcompiladores;

import javax.swing.*;
import java.io.File;

public class FileChooser {
    private FileChooser() {}

    public static File showFileChooser(String title) {
        final JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(title);

        int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        }

        return null;
    }
}
