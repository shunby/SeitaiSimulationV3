package seitaiv3.main.window;

import java.net.URL;
import java.util.ResourceBundle;

import seitaiv3.main.Main;
import seitaiv3.main.MainThread;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.ai.AI;
import seitaiv3.main.stuff.living.ai.Behave;
import seitaiv3.main.stuff.living.ai.Condition;
import seitaiv3.main.stuff.living.animal.Animal;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;
import seitaiv3.main.world.chunk.Chunk;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class WindowController implements Initializable {

	/**Mainのインスタンス*/
	private Main main;

	@FXML public Canvas canvas;
	@FXML public VBox info;
	@FXML public Label simulateState;
	@FXML public ListView<String> aiList;

	public boolean up, down, left, right;

	private World world;


	/**
	 *
	 */
	public WindowController() {
		main = Main.get();
		world = main.getMainThread().getWorld();

	}

	@FXML
	public void onKeyPressed(KeyEvent ev){
		KeyCode code = ev.getCode();
		if(code == KeyCode.UP)up = true;
		if(code == KeyCode.DOWN)down = true;
		if(code == KeyCode.RIGHT)right = true;
		if(code == KeyCode.LEFT)left = true;
		ev.consume();
	}

	@FXML
	public void onKeyReleased(KeyEvent ev){
		KeyCode code = ev.getCode();
		if(code == KeyCode.UP)up = false;
		if(code == KeyCode.DOWN)down = false;
		if(code == KeyCode.RIGHT)right = false;
		if(code == KeyCode.LEFT)left = false;
		if(code == KeyCode.T)main.getMainThread().timepass = !main.getMainThread().timepass;
		ev.consume();
	}

	@FXML
	public void mouseClicked(MouseEvent ev){
		Pos p = new Pos((float)ev.getX() + world.getCamera().getX(), (float)ev.getY() + world.getCamera().getY());
		Chunk c = world.getChunk(p);
		for(Stuff s:c.getStuffs()){
			if(!(s instanceof Animal))continue;
			Animal l = (Animal)s;
			if(Pos.getDistance(l.getPos(), p) < 10){
				aiList.getItems().clear();
				System.out.println(l.getAitable().getAi().size());
				for(AI ai:l.getAitable().getAi()){
					String str = "";
					for(Condition con:ai.getConditions()){
						str += Condition.getConditionName(con) + "&";
					}
					str += Behave.getBehaveName(ai.getBehave());
					aiList.getItems().add(str);

				}

			}
		}
	}


	/**
	 * 画面初期化
	 */
	public void initWindow() {
		aiList.setOnKeyPressed((e)->onKeyPressed(e));
		aiList.setOnKeyReleased((e)->onKeyReleased(e));
	}

	/**
	 * UI更新
	 * @param mainThread
	 */
	public void updateUI(MainThread mainThread){
		int time = mainThread.getTime();
		int sec = time / 60;

		simulateState.setText(String.format("time: %d (%d 分 %d 秒), \n fe: %d, ae:%d, ge: %d, g: %d", time, sec / 60, sec % 60, world.getFlesheater(), world.getAnyeater(), world.getPlanteater(), world.getPlant()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	//get/set-------------------------------

	public Canvas getCanvas(){
		return canvas;
	}




}
