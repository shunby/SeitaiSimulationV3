package seitaiv3.main.window;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import seitaiv3.main.Main;
import seitaiv3.main.MainThread;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WindowController implements Initializable {

	/**Mainのインスタンス*/
	private Main main;

	@FXML public Canvas canvas;
	@FXML public VBox info;
	@FXML public Label simulateState;


	/**
	 *
	 */
	public WindowController() {
		main = Main.get();
	}


	/**
	 * 画面初期化
	 */
	public void initWindow() {

	}

	/**
	 * UI更新
	 * @param mainThread
	 */
	public void updateUI(MainThread mainThread){
		int time = mainThread.getTime();
		int sec = time / 60;

		simulateState.setText(String.format("time: %d (%d 分 %d 秒)", time, sec / 60, sec % 60));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	//get/set-------------------------------

	public Canvas getCanvas(){
		return canvas;
	}




}
