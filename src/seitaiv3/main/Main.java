package seitaiv3.main;

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
	private DrawThread drawThread;

	public boolean isRunning;
	@Override
	public void start(Stage stage) throws Exception {
		//変数初期化-----------
		if(main == null)main = this;
		this.stage = stage;
		root = load();
		//画面初期化-----------
		stage.setScene(new Scene(root));
		stage.show();
		//処理部分初期化-------
		mainThread = new MainThread();
		drawThread = new DrawThread();
		//実行開始-------------
		mainThread.run();
		drawThread.run();

	}

	private Parent load() throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/fxml/main_window.fxml"));
		loader.setController(new WindowController());
		return (Parent)loader.load();
	}

	/**Mainのインスタンスを取得*/
	public static Main get(){
		return main;
	}

	/**エントリポイント*/
	public static void main(String[] args){
		launch();
	}
}
