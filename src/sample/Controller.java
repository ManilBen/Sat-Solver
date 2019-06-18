package sample;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import sample.Model.cnfReader;

import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    @FXML
    private ChoiceBox<String> fileChoice;

    @FXML
    private ChoiceBox<String> instanceChoice ;

    @FXML
    private TextArea instanceArea ;

    @FXML
    private TextArea literalArea ;

    @FXML
    private TextArea clausesArea ;



    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the text area with file instance.

        // init file choice
        File file = new File("src/benchmarks/");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });


        ObservableList<String> availableChoices =
                FXCollections.observableArrayList(directories);

        //System.out.println(availableChoices);
        fileChoice.setItems(availableChoices);
        fileChoice.getSelectionModel().selectFirst();

        // init instance choice

        File f = new File("src/benchmarks/"+fileChoice.getSelectionModel().getSelectedItem()+"/");
        String[] dirs = f.list();
        int totalNumInstances = dirs.length-1;
        ArrayList<String> instlist = new ArrayList<String>();
        for (int j=1; j<=totalNumInstances; j++){
            String inst = "0"+j;
            instlist.add(inst);
        }

        ObservableList<String> avinstanceChoices = FXCollections.observableArrayList(instlist);
        instanceChoice.setItems(avinstanceChoices);

        instanceChoice.getSelectionModel().selectFirst();

        cnfReader c = new cnfReader(0,0);

        File inst = new File(
                "src/benchmarks/"+fileChoice.getSelectionModel().getSelectedItem()
                        +"/"+fileChoice.getSelectionModel().getSelectedItem()+"-"+instanceChoice.getSelectionModel().getSelectedItem()+".cnf");

        int [][] matClauses = c.cnfRead(inst);
        instanceArea.clear();
        for (int i =0; i < matClauses.length; i++) {
            for (int j = 0; j < matClauses[0].length; j++) {
                instanceArea.appendText(" "+String.valueOf(matClauses[i][j])+" ");
            }
            instanceArea.appendText("\n");
        }




    }

    @FXML
    private void handleComboBoxAction() {
        instanceArea.clear();
        cnfReader c = new cnfReader(0,0);

        File f = new File("src/benchmarks/"+fileChoice.getSelectionModel().getSelectedItem()
                +"/"+fileChoice.getSelectionModel().getSelectedItem()+"-"+instanceChoice.getSelectionModel().getSelectedItem()+".cnf");

        System.out.println("src/benchmarks/"+fileChoice.getSelectionModel().getSelectedItem()
                +"/"+fileChoice.getSelectionModel().getSelectedItem()+
                "-"+instanceChoice.getSelectionModel().getSelectedItem()+".cnf");

        int [][] matClauses = c.cnfRead(f);
        System.out.println(matClauses.length);
        for (int i =0; i < matClauses.length; i++) {
            for (int j = 0; j < matClauses[0].length; j++) {
                instanceArea.appendText(" "+String.valueOf(matClauses[i][j])+" ");

            }
            instanceArea.appendText("\n");
        }



    }



}
