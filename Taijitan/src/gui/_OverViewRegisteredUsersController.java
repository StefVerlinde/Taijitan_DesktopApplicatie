package gui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import domain.Domaincontroller;
import domain.Rank;
import domain.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class _OverViewRegisteredUsersController extends AnchorPane {

    @FXML
    private JFXTreeTableView<User> tblRegistredUsers;

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
    JFXTreeTableColumn<User, String> clmKyu = new JFXTreeTableColumn<>("Kyu");
    JFXTreeTableColumn<User, String> clmUserDateOfBirth = new JFXTreeTableColumn<>("Geboortedatum");
    JFXTreeTableColumn<User, String> clmUserTelephone = new JFXTreeTableColumn<>("Telefoonnummer");
    JFXTreeTableColumn<User, String> clmUserEmail = new JFXTreeTableColumn<>("Email adres");
    JFXTreeTableColumn<User, String> clmDiscriminator = new JFXTreeTableColumn<>(("Rol"));

    private Domaincontroller dc;
    private FrameController fc;

    public _OverViewRegisteredUsersController(Domaincontroller dc,FrameController fc) {
        this.dc = dc;
        this.fc = fc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("_OverviewRegisteredUsers.fxml"));
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
        buildColumns();

        ObservableList<User> users = FXCollections.observableArrayList(dc.getAllUsers());

        //opvullen tabel
        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);


//        tblRegistredUsers = new JFXTreeTableView<>();
        tblRegistredUsers.getColumns().setAll(clmUserFamilyName, clmUserFirstName,clmKyu, clmDiscriminator, clmUserDateOfBirth, clmUserEmail, clmUserTelephone);
        tblRegistredUsers.setRoot(root);
        tblRegistredUsers.setShowRoot(false);

        searchFields();
    }

    private void searchFields() {
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tblRegistredUsers.setPredicate(new Predicate<TreeItem<User>>() {
                    @Override
                    public boolean test(TreeItem<User> user) {
                        boolean flag = user.getValue().familyNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                                ||
                                user.getValue().firstNameProperty().getValue().toLowerCase().contains(newValue.toLowerCase())
                                ||
                                user.getValue().telephoneProperty().getValue().toLowerCase().contains(newValue.toLowerCase());
                        return flag;
                    }
                });
            }
        });
    }


    private void buildColumns() {
        clmUserFirstName.setPrefWidth(100);
        clmUserFirstName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().firstNameProperty();
            }
        });

        clmUserFamilyName.setPrefWidth(100);
        clmUserFamilyName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().familyNameProperty();
            }
        });

        clmKyu.setPrefWidth(10);
        clmKyu.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                    return  param.getValue().getValue().KyuProperty();
            }
        });

        clmDiscriminator.setPrefWidth(15);
        clmDiscriminator.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return  param.getValue().getValue().discriminatorProperty();
            }
        });

        clmUserDateOfBirth.setPrefWidth(50);
        clmUserDateOfBirth.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().dateOfBirthProperty();
            }
        });

        clmUserTelephone.setPrefWidth(240);
        clmUserTelephone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().telephoneProperty();
            }
        });

        clmUserEmail.setPrefWidth(300);
        clmUserEmail.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().emailProperty();
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
                text = "Alle geregistreerde leden";
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
                   for(User u : dc.getAllUsers()){
                        counter+=1;
                        contentStream.beginText();
                        contentStream.setFont( PDType1Font.COURIER, 9 );
                        contentStream.setLeading(14.5f);
                        contentStream.newLineAtOffset(25, 600-(counter*10));

                        text = String.format("%-12s %s %-12s %s %-12s %s %-34s %s %-25s",  u.getFirstName(),"|", u.getName(),"|", u.dateFormatter(u.getDateOfBirth()),"|", u.getEmail(),"|", u.getPhoneNumber());
                        contentStream.showText(text);
                        contentStream.endText();
                    }
                    contentStream.close();
            document.addPage(pageOne);
            String path = AskPath.execute("overzichtLeden", "pdf");
            document.save(path);
//            document.save("D:/my_doc.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void printRegistredUsersExcell() {
        try {
            String[] columns = {"Voornaam", "Familienaam", "Geboortedatum", "Staat", "huisnummer", "Postcode","Gemeente", "telefoonnummer", "email", "Kyu"};
            //https://www.callicoder.com/java-write-excel-file-apache-poi/
            Workbook workbook = new XSSFWorkbook();

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("OverzichtGeregistreerdeLeden");

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
            for (User u : dc.getAllUsers()) {
                rownum++;
                Row row = sheet.createRow(rownum);

                row.createCell(0).setCellValue(u.getFirstName());
                row.createCell(1).setCellValue(u.getName());

                Cell DOBCell = row.createCell(2);
                DOBCell.setCellValue(u.getDateOfBirth());
                DOBCell.setCellStyle(dateCellStyle);

                row.createCell(3).setCellValue(u.getStreet());
                row.createCell(4).setCellValue(u.getHouseNumber());
                row.createCell(5).setCellValue(u.getCityPostalcode().getPostalcode());
                row.createCell(6).setCellValue(u.getCityPostalcode().getName());
                row.createCell(7).setCellValue(u.getPhoneNumber());
                row.createCell(8).setCellValue(u.getEmail());
                if(Rank.getById(u.getRank()) != null)
                    row.createCell(9).setCellValue(String.format("%s",Rank.getById(u.getRank())));
                else row.createCell(9).setCellValue("Niet van toepassing");
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }
//            FileOutputStream fileOut = new FileOutputStream(askPath(".xlsx"));
            FileOutputStream fileOut = new FileOutputStream(AskPath.execute("overzichtLeden", "xlsx"));
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
            TreeItem<User> selectedUser = tblRegistredUsers.getSelectionModel().getSelectedItem();
            List<User> users = dc.getAllUsers();
            if(users.contains(selectedUser.getValue())){
                fc.changeToMembersWithSelectedUser(selectedUser.getValue(), "registeredOverview");
            }
            else {
                AlertBoxController.BasicAlert("Error", selectedUser.getValue().getFirstName() + " " + selectedUser.getValue().getName() + " is geen bestaand lid meer.");
            }
        }
    }

    public void refresh(){
        this.tblRegistredUsers.refresh();
    }
}


