package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import domain.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class _OverviewActivitiesController extends AnchorPane {
    @FXML
    private JFXListView<Activity> lstActivities;
    @FXML
    private Label lblName;
    @FXML
    private Label lblStart;
    @FXML
    private JFXListView<User> lstPressents;
    @FXML
    private Label lblEnd;
    @FXML
    private Label lblType;
    @FXML
    private JFXButton btnPrintPdf;
    @FXML
    private JFXButton btnPrintExcell;
    @FXML
    private JFXButton btnPrintSellected;

    private Domaincontroller dc;
    private  FrameController fc;
    private  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public _OverviewActivitiesController(Domaincontroller dc, FrameController fc) {
        this.dc = dc;
        this.fc = fc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("_OverviewActivities.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();
    }

    private void buildGui() {
        lstActivities.setItems(FXCollections.observableArrayList((dc.getAllActivities())));

        lstActivities.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) ->
        {
            Activity act = (Activity) newValue;
//            Date dateStart = new Date();
//            Date dateEnd = new Date();


            lblName.setText("Naam: " + act.getName());
            lblStart.setText("Start: " + formatter.format( act.getStartDate()));
            lblEnd.setText("Einde: " +  formatter.format(act.getEndDate()));
            if(act.getType() == 0){
                lblType.setText("Type: Excursie");
            }
            else{
                lblType.setText("Type: Stage");
            }

            lstPressents.setItems(FXCollections.observableArrayList(dc.getUsersFromActivity(act)));
        });
    }

    @FXML
    void selectUserFromList(MouseEvent event) {
        if(event.getClickCount() == 2){
            User selectedUser = (User) lstPressents.getSelectionModel().getSelectedItem();
            List<User> users = dc.getAllUsers();
            if(users.contains(selectedUser)){
                fc.changeToMembersWithSelectedUser(selectedUser, "activitiesOverview");
            }
            else {
                AlertBoxController.BasicAlert("Error", selectedUser.getFirstName() + " " + selectedUser.getName() + " is geen bestaand lid meer.");
            }
        }
    }

    @FXML
    void selectActivityFromList(MouseEvent event) {
        if(event.getClickCount() == 2){
            Activity selectedActivity = (Activity) lstActivities.getSelectionModel().getSelectedItem();
            List<Activity> activities = dc.getAllActivities();

            if(activities.contains(selectedActivity)){
                fc.changeToActivityWithSelectedUser(selectedActivity, "activitiesOverview");
            }
            else{
                AlertBoxController.BasicAlert("Error", selectedActivity.getName()+ " " + " is geen bestaande activiteit meer");

            }
        }
    }

    @FXML
    void printOverviewExcell(ActionEvent event) {
        try {
            String[] columns = {"Naam", "Start", "Einde", "Type", "Deelnemers"};
            //https://www.callicoder.com/java-write-excel-file-apache-poi/
            Workbook workbook = new XSSFWorkbook();

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("OvezichtActiviteiten");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.RED.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));


            int rownum = 1;
            for(Activity act : dc.getAllActivities()){
                rownum++;
                Row row = sheet.createRow(rownum);

                row.createCell(0).setCellValue(act.getName());
                row.createCell(1).setCellValue(formatter.format(act.getStartDate()));
                row.createCell(2).setCellValue(formatter.format(act.getEndDate()));
                if(act.getType() == 0){
                    row.createCell(3).setCellValue("excursie");
                }
                else{
                    row.createCell(3).setCellValue("stage");
                }

                for(User u : act.getUsers()){
                    rownum++;
                    Row subrow = sheet.createRow(rownum);
                    subrow.createCell(4).setCellValue(u.getFirstName() +" "+ u.getName());
                }
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
//            FileOutputStream fileOut = new FileOutputStream(askPath(".xlsx"));
            FileOutputStream fileOut = new FileOutputStream(AskPath.execute("Activiteiten", "xlsx"));
            workbook.write(fileOut);
            fileOut.close();

            workbook.close();

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }

    @FXML
    void printOverviewPdf(ActionEvent event) {
        try {
            PDDocument document = new PDDocument();
            PDPage pageOne = new PDPage();
            PDPageContentStream contentStream = new PDPageContentStream(document, pageOne);
            contentStream.beginText();
            contentStream.setFont( PDType1Font.COURIER, 20 );
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 750);
            String text;
            text = "Activiteiten";
            contentStream.showText(text);
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont( PDType1Font.COURIER, 12 );
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            text = formatter.format(date);
            contentStream.showText(text);
            contentStream.endText();


            int counter = 0;
            List<Activity> activities = dc.getAllActivities();

            for (Activity act : activities){
                counter++;
                contentStream.beginText();
                contentStream.setFont( PDType1Font.COURIER, 9 );
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 600-(counter*10));

                String type;

                if(act.getType() == 0){
                   type = "Excursie";
                }
                else{
                   type = "Stage";
                }

                text = String.format("%-12s %s %-12s %s %-12s %s %-34s", act.getName(), "|", formatter.format(act.getStartDate()), "|", formatter.format(act.getEndDate()), "|", type);


                contentStream.showText(text);
                contentStream.endText();
            }
            contentStream.close();
            document.addPage(pageOne);
            String path = AskPath.execute("ActiviteitenTest", "pdf");
            document.save(path);
            document.close();
        } catch (IOException e) {
            System.out.println("locatie niet gevonden");
            e.printStackTrace();
        }
    }


    @FXML
    void printSellected(ActionEvent event) {

        Activity sellectedActivity = lstActivities.getSelectionModel().getSelectedItem();

        if(sellectedActivity == null){
            AlertBoxController.BasicAlert("Geen activiteit geselecteerd", "Sellecteer een activiteit uit de lijst, klik vervolgens op deze knop om deze activiteit te exporteren");
        }
        else{
            try {
                String[] columns = {"Naam", "Start", "Einde", "Type", "Deelnemers"};
                //https://www.callicoder.com/java-write-excel-file-apache-poi/
                Workbook workbook = new XSSFWorkbook();

                CreationHelper createHelper = workbook.getCreationHelper();

                Sheet sheet = workbook.createSheet("OvezichtActiviteiten");

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.RED.getIndex());

                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);

                Row headerRow = sheet.createRow(0);

                for (int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                    cell.setCellStyle(headerCellStyle);
                }

                CellStyle dateCellStyle = workbook.createCellStyle();
                dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));


                int rownum = 1;

                    rownum++;
                    Row row = sheet.createRow(rownum);

                    row.createCell(0).setCellValue(sellectedActivity.getName());
                    row.createCell(1).setCellValue(formatter.format(sellectedActivity.getStartDate()));
                    row.createCell(2).setCellValue(formatter.format(sellectedActivity.getEndDate()));
                    if(sellectedActivity.getType() == 0){
                        row.createCell(3).setCellValue("excursie");
                    }
                    else{
                        row.createCell(3).setCellValue("stage");
                    }

                    for(User u : sellectedActivity.getUsers()){
                        rownum++;
                        Row subrow = sheet.createRow(rownum);
                        subrow.createCell(4).setCellValue(u.getFirstName() +" "+ u.getName());
                    }


                for (int i = 0; i < columns.length; i++) {
                    sheet.autoSizeColumn(i);
                }
//            FileOutputStream fileOut = new FileOutputStream(askPath(".xlsx"));
                FileOutputStream fileOut = new FileOutputStream(AskPath.execute("geselecteerdeActiviteit", "xlsx"));
                workbook.write(fileOut);
                fileOut.close();


                workbook.close();

            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                e.printStackTrace();
            }
            catch (IOException e){
                System.out.println("IO Exception");
                e.printStackTrace();
            }
        }
    }

    public void refresh(){
        this.lstPressents.refresh();
    }
}
