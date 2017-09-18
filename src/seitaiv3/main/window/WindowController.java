package seitaiv3.main.window;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import seitaiv3.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;

public class WindowController implements Initializable {

	/**Mainのインスタンス*/
	private Main main;

	@FXML public Canvas canvas;
	@FXML public VBox info;


	/**
	 *
	 */
	public WindowController() {
		main = Main.get();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	//get/set-------------------------------

	public Canvas getCanvas(){
		return canvas;
	}



}
