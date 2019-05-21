package gui;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;

public class AskPath {

    public static String execute(String docName, String extension){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String path = f.getAbsolutePath();

        LocalDate now = LocalDate.now();
        int Year = now.getYear();
        int Month = now.getMonthValue();
        int day = now.getDayOfMonth();

//        String fileName = String.format("%s%s%d%d%d%s", path, "/OverzichtGeregistreerdeLeden", Year, Month, day, extension);
        String fileName = String.format("%s/%s_%d-%d-%d.%s", path, docName, Year, Month, day, extension);

        return fileName;
    }

}
