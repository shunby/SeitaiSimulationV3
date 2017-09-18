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
	/**window*/
	private WindowController wController;

	public boolean isRunning;
	@Override
	public void start(Stage stage) throws Exception {
		//変数初期化-----------
		if(main == null)main = this;
		this.stage = stage;
		mainThread = new MainThread();
		wController = new WindowController();

		//画面初期化-----------
		root = load();
		stage.setScene(new Scene(root));
		stage.show();
		stage.setOnCloseRequest((ev)->isRunning = false);

		//実行開始-------------
		isRunning = true;
		Thread th = new Thread(mainThread);
		th.start();

	}

	private Parent load() throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/fxml/main_window.fxml"));
		loader.setController(wController);
		return (Parent)loader.load();
	}



	/**エントリポイント*/
	public static void main(String[] args){
		launch();
	}

	//ここからget/set----------------------------------------------------------------------
	/**Mainのインスタンスを取得*/
	public static Main get(){
		return main;
	}

	public MainThread getMainThread(){
		return mainThread;
	}

	public WindowController getWindowController(){
		return wController;
	}

}
