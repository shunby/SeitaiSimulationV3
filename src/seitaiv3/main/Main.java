package seitaiv3.main;

import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seitaiv3.main.window.WindowController;

public class Main extends Application{
	/**インスタンス*/
	private static Main main;

	/**親コンポーネント*/
	private Parent root;
	/**画面*/
	private Stage stage;
	/**メイン処理*/
	private MainThread mainThread;
	/**描画処理*/
	private WindowController drawThread;
	/**描画バッファ*/
	public List<BufferedImage> buffers;

	public boolean isRunning;
	@Override
	public void start(Stage stage) throws Exception {
		//変数初期化-----------
		if(main == null)main = this;
		this.stage = stage;
		buffers = new LinkedList<>();
		mainThread = new MainThread();
		drawThread = new WindowController();

		//画面初期化-----------
		root = load();
		stage.setScene(new Scene(root));
		stage.show();
		stage.setOnCloseRequest((ev)->isRunning = false);

		//実行開始-------------
		isRunning = true;
		mainThread.start();
		drawThread.start();

	}

	private Parent load() throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/fxml/main_window.fxml"));
		loader.setController(drawThread);
		return (Parent)loader.load();
	}



	/**エントリポイント*/
	public static void main(String[] args){
		launch();
	}

	//ここからget/set----------------------------------------------------------------------

	public  boolean popFirstBuffer(BufferedImage[] img) {
		synchronized(buffers){
			if(buffers.isEmpty())return false;
			img[0] = buffers.get(0);
			buffers.remove(0);
			return true;
		}
	}

	public BufferedImage glanceFirstBuffer(){
		synchronized(buffers){
			return buffers.get(0);
		}
	}

	public int getBufferLength(){
		synchronized(buffers){
			return buffers.size();
		}
	}

	public void addBuffer(BufferedImage img) {
		synchronized(buffers){
			buffers.add(img);
		}
	}

	/**Mainのインスタンスを取得*/
	public static Main get(){
		return main;
	}


}
