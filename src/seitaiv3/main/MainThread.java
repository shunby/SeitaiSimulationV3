package seitaiv3.main;

import java.util.Random;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

/**
 *メインの処理を実行するスレッド
 */
public class MainThread extends Task<Boolean> {
	/**Main*/
	private Main main;
	/**世界*/
	private World world;



	public MainThread(){
		main = Main.get();
		world = new World(700, 700);
	}

	@Override
	protected Boolean call() throws Exception {
		Random r = new Random();
		for(int i = 0; i < 2000; i++){
			Living l1 = new Living(new Pos(r.nextInt(400) + 100, r.nextInt(400) + 100), 10, 10, world);
			world.registerStuff(l1);
		}

		GraphicsContext g = main.getWindowController().getCanvas().getGraphicsContext2D();

		while (main.isRunning) {
			Platform.runLater(()->
			update(g));
		  try {
			Thread.sleep(16);
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		}
		return true;
	}


	private void update(GraphicsContext g){
		g.setFill(new Color(160d/255, 82d/255, 45d/255, 1));
		g.fillRect(0, 0, 700, 700);

		world.update(g);
	}

	//get/set-----------------------------------------------------------------------
	public World getWorld(){
		return world;
	}






}













