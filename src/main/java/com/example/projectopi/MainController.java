package com.example.projectopi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    int exp = 0;
    int level = 1;
    int difficult = 1;
    int[] arraylevels =  {0,2,2,2,2,1,0,0,0,0};

    int needexp = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Синий фон", "Красный фон", "Желтый фон");
        anotherfon.setItems(list);
    }

    @FXML
    private ComboBox<String> anotherfon;

    @FXML
    private AnchorPane backfon;

    @FXML
    private AnchorPane forwardfon;

    @FXML
    public void newfon(ActionEvent event){
        switch (anotherfon.getValue()) {
            case ("Синий фон"):
                anotherfon.setStyle("-fx-background-color: LightBlue;");
                forwardfon.setStyle("-fx-background-color: SteelBlue;");
                backfon.setStyle("-fx-background-color: LightBlue;");
                break;
            case ("Красный фон"):
                anotherfon.setStyle("-fx-background-color: Brown;");
                forwardfon.setStyle("-fx-background-color: Red;");
                backfon.setStyle("-fx-background-color: Brown;");
                break;
            case ("Желтый фон"):
                anotherfon.setStyle("-fx-background-color: Goldenrod;");
                forwardfon.setStyle("-fx-background-color: Yellow;");
                backfon.setStyle("-fx-background-color: Goldenrod;");
                break;
            default:
                anotherfon.setValue("Error");
        }
    }

    @FXML
    private Button hardbutton;

    @FXML
    private Button startbutton;

    @FXML
    private Label diffenough;

    @FXML
    private void click2(ActionEvent event) {
        if (!levelenough.isVisible()) {
            diffenough.setVisible(false);
            if (difficult == 1) {
                hardbutton.setText("Сложность: Жесть");
                difficult = 2;
                needexp = 200 * (level - 1) + 100;

                if (arraylevels[level] != 2  && level != 0) {
                    diffenough.setLayoutX(202);
                    diffenough.setText("Пройди хотя бы 2 раза на легком");
                    diffenough.setVisible(true);
                }
                else{
                    if(needexp > exp && level != 0){
                        diffenough.setText("Недостаточно опыта:" +needexp);
                        diffenough.setVisible(true);
                        diffenough.setLayoutX(220);
                    }
                }
            } else {
                hardbutton.setText("Сложность: Легкий");
                difficult = 1;
            }
        }
    }

    @FXML
    private Button levelbutton;

    @FXML
    private Label levelenough;

    @FXML
    private void click(ActionEvent event) {
        if(level < 9) {
            level = level + 1;
        }
        else{
            level = 0;
        }

        levelenough.setVisible(false);

        diffenough.setVisible(false);
        hardbutton.setText("Сложность: Легкий");
        difficult = 1;

        levelbutton.setText("Уровень: " + level);
        needexp = 200 * (level - 1);
        if(level == 0){
            needexp = 0;
        }

        if(level != 0) {
            if (arraylevels[level] < 1) {
                levelenough.setText("Предыдущий уровень");
                levelenough.setVisible(true);
                levelenough.setLayoutX(50);
            } else {
                if (exp < needexp) {
                    levelenough.setLayoutX(79);
                    levelenough.setText("Опыт: " + needexp);
                    levelenough.setVisible(true);
                }
            }
        }
    }

    @FXML
    private Label expmenu;

    @FXML
    private void click1(ActionEvent event) {
        if(!levelenough.isVisible() && !diffenough.isVisible()) {
            exp = exp + 10;
            expmenu.setText("Ваш опыт: " + exp);
            expmenu.setText("Ваш опыт: " + exp);
            if (levelenough.isVisible() && exp >= needexp && level != 0 && arraylevels[level] >= 1) {    // Изменения при достаточном опыте для уровня
                levelenough.setVisible(false);
            }
            if (diffenough.isVisible() && exp >= needexp && level != 0 && arraylevels[level] > 1) {  // Измения при достаточном опыте для уровня сложности
                diffenough.setVisible(false);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Some troubles");
            alert.setHeaderText(null);
            alert.setContentText("Проверьте уровень и сложность");

            alert.showAndWait();
        }
    }
}