package agendaescolar.controller;

import agendaescolar.domain.Group;
import agendaescolar.domain.SchoolSubject;
import agendaescolar.domain.Task;
import agendaescolar.repository.GroupRepository;
import agendaescolar.repository.SchoolSubjectRepository;
import agendaescolar.repository.TaskRepository;
import agendaescolar.service.MySQLConnection;
import static agendaescolar.service.SQLCauses.NOT_FOUND;
import agendaescolar.view.Main;
import agendaescolar.view.TaskView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class HomeController implements Initializable {
    
    private static Group groupSelected;
    private static SchoolSubject schoolSubjectSelected;
    private static Task taskSelected;
    
    @FXML private AnchorPane anchorPane;
    @FXML private TableView<Group> groupsTable;
    @FXML private TableView<SchoolSubject> schoolSubjectsTable;
    @FXML private TableView<Task> tasksTable;
    @FXML private TableColumn<Group, String> groupsColumn;
    @FXML private TableColumn<SchoolSubject, String> schoolSubjectsColumn;
    @FXML private TableColumn<Task, String> tasksColumn;

    private List<Group> groups;
    private List<SchoolSubject> schoolSubjects;
    private List<Task> tasks;
    private ObservableList<Group> observableListGroups;
    private ObservableList<SchoolSubject> observableListSchoolSubjects;
    private ObservableList<Task> observableListTasks;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.setTitle(Main.SceneTypes.HOME);

        groupsColumn.setCellValueFactory(new PropertyValueFactory<>("classNumber"));
        schoolSubjectsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tasksColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        try {
            groups = GroupRepository.getGroupsByUserLogged();
            observableListGroups = FXCollections.observableArrayList(groups);
            groupsTable.setItems(observableListGroups);
            
            groupsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> getItemTableGroups(newValue)
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Um erro interno afetou a execução do programa!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
            ((Stage) anchorPane.getScene().getWindow()).close();
        }
    }
    
    @FXML
    private void handleNewGroup() throws IOException {
        VBox groupCreationAcAnchorPane = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/GroupCreation.fxml"));
        anchorPane.getChildren().setAll(groupCreationAcAnchorPane);
    }
    
    @FXML
    private void handleNewSchoolSubject() throws IOException {
        VBox schoolSubjectCreationAcAnchorPane = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/SchoolSubjectCreation.fxml"));
        anchorPane.getChildren().setAll(schoolSubjectCreationAcAnchorPane);
    }
    
    @FXML
    private void handleNewTask() throws IOException {
        VBox taskCreationAcAnchorPane = FXMLLoader.load(getClass().getResource("/agendaescolar/view/styles/TaskCreation.fxml"));
        anchorPane.getChildren().setAll(taskCreationAcAnchorPane);
    }
    
    private void getItemTableGroups(Group group) {
        groupSelected = group;
        tasksTable.getItems().clear();
        
        try {
            schoolSubjects = SchoolSubjectRepository.getSchoolSubjectsByGroupSelected();
            observableListSchoolSubjects = FXCollections.observableArrayList(schoolSubjects);
            schoolSubjectsTable.setItems(observableListSchoolSubjects);
            
            schoolSubjectsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> getItemTableSchoolSubjects(newValue)
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Um erro interno afetou a execução do programa!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, e);
            ((Stage) anchorPane.getScene().getWindow()).close();
        }
    }
    
    private void getItemTableSchoolSubjects(SchoolSubject schoolSubject) {
        schoolSubjectSelected = schoolSubject;
        
        try {
            tasks = TaskRepository.getTasksBySchoolSubject();
            observableListTasks = FXCollections.observableArrayList(tasks);
            tasksTable.setItems(observableListTasks);
            
            tasksTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                try {
                    new TaskView(newValue);
                } catch (IOException ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );
        } catch (SQLException e) {
        }
    }

    public static Group getGroupSelected() {
        return groupSelected;
    }

    public static SchoolSubject getSchoolSubjectSelected() {
        return schoolSubjectSelected;
    }

    public static Task getTaskSelected() {
        return taskSelected;
    }

}
