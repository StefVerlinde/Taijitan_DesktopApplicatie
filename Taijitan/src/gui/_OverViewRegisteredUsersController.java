package gui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import domain.Domaincontroller;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.function.Predicate;

public class _OverViewRegisteredUsersController extends AnchorPane {

    @FXML
    private JFXTreeTableView<User> tblRegistredUsers;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnPrint;

    @FXML
    void printOverview(ActionEvent event) {
            printRegistredUsers();
    }

    JFXTreeTableColumn<User, String> clmUserFirstName = new JFXTreeTableColumn<>("Voornaam");
    JFXTreeTableColumn<User, String> clmUserFamilyName = new JFXTreeTableColumn<>("Familienaam");
    JFXTreeTableColumn<User, String> clmUserDateOfBirth = new JFXTreeTableColumn<>("Geboortedatum");
    JFXTreeTableColumn<User, String> clmUserTelephone = new JFXTreeTableColumn<>("Telefoonnummer");
    JFXTreeTableColumn<User, String> clmUserEmail = new JFXTreeTableColumn<>("Email adres");

    private Domaincontroller dc;

    public _OverViewRegisteredUsersController(Domaincontroller dc) {
        this.dc = dc;

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
        System.out.println(users.size());

//        tblRegistredUsers = new JFXTreeTableView<>();
        tblRegistredUsers.getColumns().setAll(clmUserFamilyName, clmUserFirstName, clmUserDateOfBirth, clmUserEmail, clmUserTelephone);
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
        clmUserFirstName.setPrefWidth(200);
        clmUserFirstName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().firstNameProperty();
            }
        });

        clmUserFamilyName.setPrefWidth(200);
        clmUserFamilyName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().familyNameProperty();
            }
        });

        clmUserDateOfBirth.setPrefWidth(200);
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


    private  void printRegistredUsers(){
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
                    System.out.println("content added");
                    contentStream.close();
            document.addPage(pageOne);
            String path = askPath();
            document.save(path);
//            document.save("D:/my_doc.pdf");
            document.close();
        } catch (IOException e) {
            System.out.println("locatie niet gevonden");
            e.printStackTrace();
        }
    }

    private String askPath(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String path = f.getAbsolutePath();

        LocalDate now = LocalDate.now();
        int Year = now.getYear();
        int Month = now.getMonthValue();
        int day = now.getDayOfMonth();

        String fileName = String.format("%s%s%d%d%d%s", path, "/OverzichtGeregistreerdeLeden", Year, Month, day, ".pdf");
        System.out.println(fileName);

        return fileName;
    }

}


