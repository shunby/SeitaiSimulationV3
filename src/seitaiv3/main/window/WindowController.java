package seitaiv3.main.window;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;

import seitaiv3.main.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;

public class WindowController extends Thread implements Initializable {

	/**Mainのインスタンス*/
	private Main main;
	/**canvasのGraphicsContext*/
	private GraphicsContext graphic;

	@FXML public Canvas canvas;
	@FXML public static VBox info;


	/**
	 *
	 */
	public WindowController() {
		main = Main.get();
	}

	@Override
	public void run() {
		graphic = canvas.getGraphicsContext2D();
		while(main.isRunning){
			WritableImage wimg = new WritableImage(700, 700);
			BufferedImage[] img = new BufferedImage[1];

			boolean flag = false;
			while(!flag){
				flag = main.popFirstBuffer(img);
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			SwingFXUtils.toFXImage(img[0], wimg) ;
			graphic.drawImage(wimg, 0, 0);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}



}
