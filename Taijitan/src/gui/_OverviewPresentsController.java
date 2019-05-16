package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import domain.Activity;
import domain.Domaincontroller;
import domain.Session;
import domain.User;
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
import java.util.Date;
import java.util.List;


public class _OverviewPresentsController extends AnchorPane {

    @FXML
    private JFXListView lstSessions;

    @FXML
    private Label lblTeacher;

    @FXML
    private Label lblDate;

    @FXML
    private JFXListView lstPressents;
    @FXML
    private JFXButton btnPrintPdf;

    @FXML
    private JFXButton btnPrintExcell;


    private Domaincontroller dc;
    private FrameController fc;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");



    private void buildGui() {
        lstSessions.setItems(FXCollections.observableArrayList(dc.getAllSessions()));

        lstSessions.getSelectionModel().selectedItemProperty().addListener((ObservableValue, oldValue, newValue) ->
        {
            if (newValue != null) {
                Session sellectedSession = (Session) newValue;


                System.out.println("tekst voor in lbl: " + sellectedSession.toString());
                System.out.println("tekst voor in lbl2: " + sellectedSession.getTeacherUserId().getFirstName());


                lblDate.setText(String.format("Datum: %s", sellectedSession.toString()));
                lblTeacher.setText(String.format("Trainer: %s %s", sellectedSession.getTeacherUserId().getFirstName(), sellectedSession.getTeacherUserId().getName()));
                lblTeacher = new Label(sellectedSession.getTeacherUserId().getFirstName());

                lstPressents.setItems(FXCollections.observableArrayList(dc.getUsersFromSession(sellectedSession)));
            }
        });


    }

    public _OverviewPresentsController(Domaincontroller dc, FrameController fc) {
        this.dc = dc;
        this.fc = fc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("_OverviewPresents.fxml"));
        loader.setRoot(this);
        loader.setController(this);


        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();


    }
    @FXML
    private void selectMemberFromList(MouseEvent event)
    {
        if(event.getClickCount() == 2){
            User selectedUser = (User) lstPressents.getSelectionModel().getSelectedItem();
            List<User> users = dc.getAllUsers();
            if(users.contains(selectedUser)){
                System.out.println(selectedUser);
                fc.changeToMembersWithSelectedUser(selectedUser, "presentOverview");
            }
            else {
                AlertBoxController.BasicAlert("Error", selectedUser.getFirstName() + " " + selectedUser.getName() + " is geen bestaand lid meer.");
            }
        }
    }

    @FXML
    void printOverviewExcell(ActionEvent event) {
        try {
            String[] columns = {"Datum", "Lesgever", "Aanwezige leden"};
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
            for(Session s : dc.getAllSessions()){
                rownum++;
                Row row = sheet.createRow(rownum);


                row.createCell(0).setCellValue(s.getDate().toString());
                row.createCell(1).setCellValue(s.getTeacherUserId().getFirstName() + " " +s.getTeacherUserId().getName());
               for (User u : dc.getUsersFromSession(s)){
                   rownum++;
                   Row subrow = sheet.createRow((rownum));
                   subrow.createCell(2).setCellValue(u.getFirstName());
                   subrow.createCell(3).setCellValue(u.getName());
                   subrow.createCell(4).setCellValue(u.getDiscriminator());
                   subrow.createCell(4).setCellValue(u.getEmail());
                   subrow.createCell(5).setCellValue(u.getPhoneNumber());
               }

            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
//            FileOutputStream fileOut = new FileOutputStream(askPath(".xlsx"));
            FileOutputStream fileOut = new FileOutputStream(AskPath.execute("Aanwezigheden", "xlsx"));
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
            text = "Aanwezigheden";
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

            for (Session s: dc.getAllSessions()){
                counter++;
                contentStream.beginText();
                contentStream.setFont( PDType1Font.COURIER, 9 );
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 600-(counter*10));

                String type;



                text = String.format("%-12s %s %-12s %s %d",s.getDate().toString(), "|", s.getTeacherUserId().getFirstName() + " " + s.getTeacherUserId().getName() ,"|", dc.getUsersFromSession(s).size());


                contentStream.showText(text);
                contentStream.endText();
            }
            System.out.println("content added");
            contentStream.close();
            document.addPage(pageOne);
            String path = AskPath.execute("Aanwezigheden", "pdf");
            document.save(path);
            document.close();
    }catch (IOException e) {
            System.out.println("locatie niet gevonden");
            e.printStackTrace();
        }
}
}
