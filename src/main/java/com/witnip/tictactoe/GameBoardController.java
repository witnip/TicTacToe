package com.witnip.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameBoardController implements Initializable {
    @FXML
    private Label message;
    @FXML
    private Button btnRestartGame;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;

    ArrayList<Button> buttons;
    private int playerTurn = 0;
    private int turnCount = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9));


        buttons.forEach(button -> {
            setUpButton(button);
            button.setFocusTraversable(false);
        });
    }

    private void setUpButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setPlayerSymbol(button);
            button.setDisable(true);
            checkForGameEnd();
        });
    }
    private void setPlayerSymbol(Button button) {
        if(playerTurn == 0){
            button.setText("X");
            message.setText("Player O's turn");
            playerTurn = 1;
            turnCount++;
        }else {
            button.setText("O");
            message.setText("Player X's turn");
            playerTurn = 0;
            turnCount++;
        }
    }
    private void checkForGameEnd() {
        for(int i=0; i<8; i++){
            String line = switch (i){
                case 0 -> btn1.getText()+btn2.getText()+btn3.getText();
                case 1 -> btn4.getText()+btn5.getText()+btn6.getText();
                case 2 -> btn7.getText()+btn8.getText()+btn9.getText();
                case 3 -> btn1.getText()+btn4.getText()+btn7.getText();
                case 4 -> btn2.getText()+btn5.getText()+btn8.getText();
                case 5 -> btn3.getText()+btn6.getText()+btn9.getText();
                case 6 -> btn1.getText()+btn5.getText()+btn9.getText();
                case 7 -> btn3.getText()+btn5.getText()+btn7.getText();
                default -> null;
            };

            if(line.equals("XXX")){
                message.setText("Player X Won!!!");
                btnRestartGame.setVisible(true);

            }
            if(line.equals("OOO")){
                message.setText("Player O Won!!!");
                btnRestartGame.setVisible(true);

            }

        }
        if(turnCount == 9) {
            message.setText("Stalemate..!!");
            btnRestartGame.setVisible(true);
        }
    }

    @FXML
    private void restartGame(ActionEvent event){
        buttons.forEach(this::resetGame);
    }

    private void resetGame(Button button) {
        button.setDisable(false);
        button.setText("");
        playerTurn = 0;
        turnCount = 0;
        btnRestartGame.setVisible(false);
        message.setText("Player X's turn");
    }


}