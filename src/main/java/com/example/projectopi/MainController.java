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

    int Exp = 0;
    int level = 1;
    int Difficult = 1;
    int[] ArrayLevels =  {0,2,2,2,2,1,0,0,0,0};

    int NeedExp = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> list = FXCollections.observableArrayList("Синий фон", "Красный фон", "Желтый фон");
        AnotherFon.setItems(list);
    }

    @FXML
    private ComboBox<String> AnotherFon;

    @FXML
    private AnchorPane BackFon;

    @FXML
    private AnchorPane ForwardFon;

    @FXML
    public void NewFon(ActionEvent event){
        switch (AnotherFon.getValue()) {
            case ("Синий фон"):
                AnotherFon.setStyle("-fx-background-color: LightBlue;");
                ForwardFon.setStyle("-fx-background-color: SteelBlue;");
                BackFon.setStyle("-fx-background-color: LightBlue;");
                break;
            case ("Красный фон"):
                AnotherFon.setStyle("-fx-background-color: Brown;");
                ForwardFon.setStyle("-fx-background-color: Red;");
                BackFon.setStyle("-fx-background-color: Brown;");
                break;
            case ("Желтый фон"):
                AnotherFon.setStyle("-fx-background-color: Goldenrod;");
                ForwardFon.setStyle("-fx-background-color: Yellow;");
                BackFon.setStyle("-fx-background-color: Goldenrod;");
                break;
        }
    }

    @FXML
    private Button HardButton;

    @FXML
    private Button StartButton;

    @FXML
    private Label DiffEnough;

    @FXML
    private void click2(ActionEvent event) {
        if (!LevelEnough.isVisible()) {
            DiffEnough.setVisible(false);
            if (Difficult == 1) {
                HardButton.setText("Сложность: Жесть");
                Difficult = 2;
                NeedExp = 200 * (level - 1) + 100;

                if (ArrayLevels[level] != 2) {
                    DiffEnough.setLayoutX(202);
                    DiffEnough.setText("Пройди хотя бы 2 раза на легком");
                    DiffEnough.setVisible(true);
                }
                else{
                    if(NeedExp > Exp){
                        DiffEnough.setText("Недостаточно опыта:" +NeedExp);
                        DiffEnough.setVisible(true);
                        DiffEnough.setLayoutX(220);
                    }
                }
            } else {
                HardButton.setText("Сложность: Легкий");
                Difficult = 1;
            }
        }
    }

    @FXML
    private Button LevelButton;

    @FXML
    private Label LevelEnough;

    @FXML
    private void click(ActionEvent event) {
        if(level < 9) {
            level = level + 1;
        }
        else{
            level = 0;
        }

        LevelEnough.setVisible(false);

        DiffEnough.setVisible(false);
        HardButton.setText("Сложность: Легкий");
        Difficult = 1;

        LevelButton.setText("Уровень: " + level);
        NeedExp = 200 * (level - 1);
        if(level == 0){
            NeedExp = 0;
        }

        if(level != 0) {
            if (ArrayLevels[level] < 1) {
                LevelEnough.setText("Предыдущий уровень");
                LevelEnough.setVisible(true);
                LevelEnough.setLayoutX(50);
            } else {
                if (Exp < NeedExp) {
                    LevelEnough.setLayoutX(79);
                    LevelEnough.setText("Опыт: " + NeedExp);
                    LevelEnough.setVisible(true);
                }
            }
        }
    }

    @FXML
    private Label ExpMenu;

    @FXML
    private void click1(ActionEvent event) {
        Exp = Exp + 10;
        ExpMenu.setText("Ваш опыт: " + Exp);
        ExpMenu.setText("Ваш опыт: " + Exp);
        if(LevelEnough.isVisible()) {
            if (Exp >= NeedExp) {
                if (level != 0) {
                    if (ArrayLevels[level] >= 1) {
                        LevelEnough.setVisible(false);
                    }
                }
            }
        }
        if(DiffEnough.isVisible()){
            if (Exp >= NeedExp) {
                if (level != 0) {
                    if (ArrayLevels[level] > 1) {
                        DiffEnough.setVisible(false);
                    }
                }
            }
        }
    }
}