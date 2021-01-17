import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.Frame;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hilfsklassen.gui.MainFrame;

public class RezeptLoader {

    public RezeptLoader() {
        ladeNeueRezeptDatei();
    }

    public  void ladeNeueRezeptDatei() {
        JFrame frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // warnings and file chooser
        boolean sure = wannaContinue(frame,
                "Die aktuellen Rezepte werden ueberschrieben. Dies kann zu massiven Problem im Projekt fuehren.\n Wollen Sie fortfahren?");
        System.out.println(sure);

        if (!sure) {
            msg(frame, "Der Vorgang wird abgebrochen.");
            return;
        }
        File f = getInputFileTxt();
        File other = new File(MainFrame.pathToAlgoKueche + "/res/rezept.txt");

        // warnings and file chooser
        boolean reallySure = wannaContinue(frame,
                "Das Ueberschrieben kann zu massiven Problem im Projekt fuehren.\n Bestaetigen Sie, dass sie trotzdem fortfahren wollen?");
        System.out.println(reallySure);
        if (!sure) {
            msg(frame, "Der Vorgang wird abgebrochen.");
            return;
        }

        // copy source to target using Files Class
        try {
            copyFiles(f, other);
        } catch (Exception e) {
        }
    }

    private void copyFiles(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private File getInputFileTxt() {
        FileDialog dialog = new FileDialog((Frame) null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setDirectory(".");
        dialog.setMultipleMode(false);
        dialog.setVisible(true);
        dialog.setFile("*.txt");

        File files[] = dialog.getFiles();
        if (files.length > 0) {
            return files[0];
        }
        return null;
    }

    private boolean wannaContinue(JFrame frame, String msg) {
        Object[] options = { "Fortfahren", "Abbrechen" };
        return JOptionPane.showOptionDialog(frame, msg, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]) == 0;
    }

    private void msg(JFrame frame, String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }
}