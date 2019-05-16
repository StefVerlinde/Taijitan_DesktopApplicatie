package gui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import domain.Domaincontroller;
import domain.Session;
import domain.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
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
import java.util.function.Predicate;

public class _OverviewScorebord extends AnchorPane {

    @FXML
    private JFXTreeTableView<User> tblScorebord;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnPrintPdf;

    @FXML
    private JFXButton btnPrintExcell;

    @FXML
    void printOverviewExcell(ActionEvent event) {
        printRegistredUsersExcell();
    }

    @FXML
    void printOverviewPdf(ActionEvent event) {
        printRegistredUsersPdf();
    }

    JFXTreeTableColumn<User, String> clmUserFirstName = new JFXTreeTableColumn<>("Voornaam");
    JFXTreeTableColumn<User, String> clmUserFamilyName = new JFXTreeTableColumn<>("Familienaam");
    JFXTreeTableColumn<User, String> clmScore = new JFXTreeTableColumn<>("Score");
    JFXTreeTableColumn<User, String> clmRanking = new JFXTreeTableColumn<>("Ranking");


    private Domaincontroller dc;
    private FrameController fc;

    public _OverviewScorebord(Domaincontroller dc,FrameController fc) {
        this.dc = dc;
        this.fc = fc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("_OverviewScorebord.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildGui();
    }

    private void buildGui(){
        buildColumns();
        ObservableList<User> users = FXCollections.observableArrayList(dc.getAllUsers());
        Collections.sort(users, Comparator.comparing(User::getScore).reversed());
        //opvullen tabel
        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
        System.out.println(users.size());

        tblScorebord.getColumns().setAll( clmRanking, clmUserFamilyName, clmUserFirstName,clmScore);
        tblScorebord.setRoot(root);
        tblScorebord.setShowRoot(false);

        searchFields();
    }

    private void searchFields() {
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tblScorebord.setPredicate(new Predicate<TreeItem<User>>() {
                    @Override
                    public boolean test(TreeItem<User> user) {
                        boolean flag = user.getValue().familyNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                                ||
                                user.getValue().firstNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                                ||
                                user.getValue().scoreProperty().getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
    }

    private void buildColumns() {
        clmUserFirstName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().firstNameProperty();
            }
        });

        clmUserFamilyName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().familyNameProperty();
            }
        });

        clmScore.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call (TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().scoreProperty();
            }
        });

        clmRanking.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            int ranknumber = 1;
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {

                List<User> gesorteerdeUsers = dc.getSortedUsers();
//                Collections.sort(gesorteerdeUsers, Comparator.comparing(User::getScore).reversed());

                User u = param.getValue().getValue();
                int rank = gesorteerdeUsers.indexOf(u)+1;
                return new SimpleStringProperty(String.format("%d", rank));
            }
        });
    }

    private  void printRegistredUsersPdf(){
        try {
            PDDocument document = new PDDocument();
            PDPage pageOne = new PDPage();
            PDPageContentStream contentStream = new PDPageContentStream(document, pageOne);
            contentStream.beginText();
            contentStream.setFont( PDType1Font.COURIER, 20 );
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 750);
            String text;
            text = "Scorebord";
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
            List<User> gesorteerdeUsers = dc.getAllUsers();
            Collections.sort(gesorteerdeUsers, Comparator.comparing(User::getScore).reversed());
            for(User u : gesorteerdeUsers){
                counter+=1;
                contentStream.beginText();
                contentStream.setFont( PDType1Font.COURIER, 9 );
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 600-(counter*10));

                List<User> users = dc.getSortedUsers();
                int rank = users.indexOf(u) + 1;

                text = String.format("%-5s %-15s %-15s %-15s %-15s %-15s %-15s", String.format("%d", rank), "|",   u.getFirstName(),"|", u.getName(),"|", u.getScore());
                contentStream.showText(text);
                contentStream.endText();
            }
            System.out.println("content added");
            contentStream.close();
            document.addPage(pageOne);
//            String path = askPath(".pdf");
            String path = AskPath.execute("Scorebord", "pdf");
            document.save(path);
//            document.save("D:/my_doc.pdf");
            document.close();
        } catch (IOException e) {
            System.out.println("locatie niet gevonden");
            e.printStackTrace();
        }
    }

    private void printRegistredUsersExcell() {
        try {
            String[] columns = {"Ranking", "Voornaam", "Familienaam", "Score"};
            //https://www.callicoder.com/java-write-excel-file-apache-poi/
            Workbook workbook = new XSSFWorkbook();

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Scorebord");

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
            List<User> gesorteerdeUsers = dc.getAllUsers();
            Collections.sort(gesorteerdeUsers, Comparator.comparing(User::getScore).reversed());
            for (User u : gesorteerdeUsers) {
                rownum++;
                Row row = sheet.createRow(rownum);

                List<User> users = dc.getSortedUsers();
                int rank = users.indexOf(u) + 1;


                row.createCell(0).setCellValue(rank);
                row.createCell(1).setCellValue(u.getFirstName());
                row.createCell(2).setCellValue(u.getName());
                row.createCell(3).setCellValue(u.getScore());
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
//            FileOutputStream fileOut = new FileOutputStream(askPath(".xlsx"));
            FileOutputStream fileOut = new FileOutputStream(AskPath.execute("Scorebord", "xlsx"));
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
    private void selectUserInList(MouseEvent event) {
        if(event.getClickCount() == 2)
        {
            TreeItem<User> selectedUser = tblScorebord.getSelectionModel().getSelectedItem();
            List<User> users = dc.getAllUsers();
            if(users.contains(selectedUser.getValue())){
                System.out.println(selectedUser);
                fc.changeToMembersWithSelectedUser(selectedUser.getValue(), "scorebordOverview");
            }
            else {
                AlertBoxController.BasicAlert("Error", selectedUser.getValue().getFirstName() + " " + selectedUser.getValue().getName() + " is geen bestaand lid meer.");
            }
        }
    }
}