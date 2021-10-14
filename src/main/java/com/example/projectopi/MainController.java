package com.example.projectopi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    int[] array_levels =  {0,2,2,2,2,1,0,0,0,0};

    int need_exp = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Синий фон", "Красный фон", "Желтый фон");
        another_fon.setItems(list);
    }

    @FXML
    private ComboBox<String> another_fon;

    @FXML
    private AnchorPane back_fon;

    @FXML
    private AnchorPane forward_fon;

    @FXML
    public void NewFon(ActionEvent event){
        switch (another_fon.getValue()) {
            case ("Синий фон"):
                another_fon.setStyle("-fx-background-color: LightBlue;");
                forward_fon.setStyle("-fx-background-color: SteelBlue;");
                back_fon.setStyle("-fx-background-color: LightBlue;");
                break;
            case ("Красный фон"):
                another_fon.setStyle("-fx-background-color: Brown;");
                forward_fon.setStyle("-fx-background-color: Red;");
                back_fon.setStyle("-fx-background-color: Brown;");
                break;
            case ("Желтый фон"):
                another_fon.setStyle("-fx-background-color: Goldenrod;");
                forward_fon.setStyle("-fx-background-color: Yellow;");
                back_fon.setStyle("-fx-background-color: Goldenrod;");
                break;
            default:
                another_fon.setValue("Error");
        }
    }

    @FXML
    private Button hard_button;

    @FXML
    private Button start_button;

    @FXML
    private Label diff_enough;

    @FXML
    private void click2(ActionEvent event) {
        if (!level_enough.isVisible()) {
            diff_enough.setVisible(false);
            if (difficult == 1) {
                hard_button.setText("Сложность: Жесть");
                difficult = 2;
                need_exp = 200 * (level - 1) + 100;

                if (array_levels[level] != 2) {
                    diff_enough.setLayoutX(202);
                    diff_enough.setText("Пройди хотя бы 2 раза на легком");
                    diff_enough.setVisible(true);
                }
                else{
                    if(need_exp > exp){
                        diff_enough.setText("Недостаточно опыта:" +need_exp);
                        diff_enough.setVisible(true);
                        diff_enough.setLayoutX(220);
                    }
                }
            } else {
                hard_button.setText("Сложность: Легкий");
                difficult = 1;
            }
        }
    }

    @FXML
    private Button level_button;

    @FXML
    private Label level_enough;

    @FXML
    private void click(ActionEvent event) {
        if(level < 9) {
            level = level + 1;
        }
        else{
            level = 0;
        }

        level_enough.setVisible(false);

        diff_enough.setVisible(false);
        hard_button.setText("Сложность: Легкий");
        difficult = 1;

        level_button.setText("Уровень: " + level);
        need_exp = 200 * (level - 1);
        if(level == 0){
            need_exp = 0;
        }

        if(level != 0) {
            if (array_levels[level] < 1) {
                level_enough.setText("Предыдущий уровень");
                level_enough.setVisible(true);
                level_enough.setLayoutX(50);
            } else {
                if (exp < need_exp) {
                    level_enough.setLayoutX(79);
                    level_enough.setText("Опыт: " + need_exp);
                    level_enough.setVisible(true);
                }
            }
        }
    }

    @FXML
    private Label exp_menu;

    @FXML
    private void click1(ActionEvent event) {
        exp = exp + 10;
        exp_menu.setText("Ваш опыт: " + exp);
        exp_menu.setText("Ваш опыт: " + exp);
        if(level_enough.isVisible() && exp >= need_exp && level != 0 && array_levels[level] >= 1){    // Изменения при достаточном опыте для уровня
            level_enough.setVisible(false);
        }
        if(diff_enough.isVisible() && exp >= need_exp && level != 0 && array_levels[level] > 1){  // Измения при достаточном опыте для уровня сложности
            diff_enough.setVisible(false);
        }
    }
}