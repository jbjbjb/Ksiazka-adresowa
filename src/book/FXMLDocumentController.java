/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML public TableView<Person> table;
    @FXML public TableColumn<Person, String> surnameCol;
    @FXML public TableColumn<Person, String> firstnameCol;
    @FXML public TableColumn<Person, String> phoneCol;
    @FXML public TableColumn<Person, String> emailCol;
    @FXML public TextField surnameF;
    @FXML public TextField firstnameF;
    @FXML public TextField phoneF;
    @FXML public TextField emailF;
    @FXML public Button addButton, updateButton, deleteButton;
    
    
    
    @FXML
    private void handleAdd(ActionEvent event) {
        ObservableList<Person> people=table.getItems();
     
        Person p=new Person(surnameF.getText(),firstnameF.getText(),phoneF.getText(),emailF.getText());
        people.add(p);
        
       
    }
    @FXML
    private void handleUpdate(ActionEvent event) {
        ObservableList<Person> people=table.getItems();
     try{
            int index = table.getSelectionModel().getSelectedIndex();
            Person p=new Person(surnameF.getText(),firstnameF.getText(),phoneF.getText(),emailF.getText());
        people.set(index,p);
        }catch(ArrayIndexOutOfBoundsException e){
            label.setText("Nie wybrano wiersza do usunięcia.");
        }
        
        
       
    }
    @FXML
    private void handleDelete(ActionEvent event) {
    ObservableList<Person> people = table.getItems();
        try{
            int index = table.getSelectionModel().getSelectedIndex();
            people.remove(index);
        }catch(ArrayIndexOutOfBoundsException e){
            label.setText("Nie wybrano wiersza do usunięcia.");
        }
    }
    @FXML
    private void handleSelected(MouseEvent event) {
    ObservableList<Person> people = table.getItems();
        try{
            int index = table.getSelectionModel().getSelectedIndex();
            Person p =people.get(index);
            surnameF.setText(p.getSurname());
            firstnameF.setText(p.getFirstname());
            phoneF.setText(p.getPhone());
            emailF.setText(p.getEmail());
        }catch(ArrayIndexOutOfBoundsException e){
            label.setText("Nie wybrano wiersza do usunięcia.");
        }
    }
    @FXML
    private void handleSave(ActionEvent event){
        ObservableList<Person> people = table.getItems();
        String line="";
        String s=";";
        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter("db.txt"));
            for(int i=0;i<people.size();i++){
                Person p=people.get(i);
                line=p.getSurname()+s+p.getFirstname()+s+p.getPhone()+s+p.getEmail();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Cannot save file");
        }
       finally {
		if (bw != null) {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
     @FXML
    private void handleCancel(ActionEvent event){   
        ObservableList<Person> people = table.getItems();
        people.clear();
        people.addAll(readDB());
        table.setItems(people);
    }
    public ObservableList<Person> readDB() {
        String line;
        String sep=";";
        ObservableList<Person> people = FXCollections.observableArrayList();
        try {   
            BufferedReader bf = new BufferedReader(new FileReader("db.txt"));
            while (((line = bf.readLine()) != null)){
                String[] d=line.split(sep);
                Person person=new Person(d[0], d[1], d[2], d[3]);
                people.add(person);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Cannot read file");
        }
        return people;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          table.getItems().addAll(readDB());
          
          
// TODO
    }    
    
}
