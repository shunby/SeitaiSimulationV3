package seitaiv3.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.ai.AI;
import seitaiv3.main.stuff.living.ai.AITable;
import seitaiv3.main.stuff.living.ai.Behave;
import seitaiv3.main.stuff.living.ai.Condition;
import seitaiv3.main.stuff.living.animal.Animal;
import seitaiv3.main.stuff.living.plant.Plant;
import seitaiv3.main.stuff.living.status.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

/**
 *メインの処理を実行するスレッド
 */
public class MainThread implements Runnable {
	/**Main*/
	private Main main;
	/**世界*/
	private World world;
	/**ループ回数*/
	private int time;
	/**時間が動くか*/
	public boolean timepass = true;

	public MainThread(){
		main = Main.get();
		world = new World(2000, 2000);
	}

	@Override
	public void run(){
		try{

		Random r = new Random();


		for(int i = 0; i < 700; i++){
			Status s = new Status();
			s.setEnergy(600);
			s.setEnergy_max(1200);
			s.setSize(30);

			Living l1 = new Plant(new Pos(r.nextInt(1800) + 100, r.nextInt(1800) + 100), world, s);
			world.registerStuff(l1);
		}
		for(int i = 0;i < 200; i++){
			Status s1 = new Status();
			s1.setEnergy(600);
			s1.setEnergy_max(1200);
			s1.setSize(30);
			s1.setSpeed(5);
			s1.setFeed(0f);
			s1.setRace(0xffffff);
			s1.setAttack(2f);
			Animal l2 = new Animal(new Pos(r.nextInt(1800) + 100, r.nextInt(1800) + 100), world, s1);
//			AI ai = new AI(Behave.go_plant);
//			ai.getConditions().add(Condition.no_condition);
//			l2.getAitable().getAi().add(ai);
			l2.setAitable(AITable.getRandomTable(l2, r));
			world.registerStuff(l2);
		}
		for(int i = 0;i < 25; i++){
			Status s1 = new Status();
			s1.setEnergy(600);
			s1.setEnergy_max(1200);
			s1.setSize(30);
			s1.setSpeed(5);
			s1.setFeed(1f);
			s1.setRace(0xff0000);
			s1.setAttack(5f);
			Animal l2 = new Animal(new Pos(r.nextInt(1800) + 100, r.nextInt(1800) + 100), world, s1);
//			AI ai = new AI(Behave.go_planteater);
//			ai.getConditions().add(Condition.no_condition);
//			l2.getAitable().getAi().add(ai);
			l2.setAitable(AITable.getRandomTable(l2, r));
			world.registerStuff(l2);
		}
		for(int i = 0;i < 0; i++){
			Status s1 = new Status();
			s1.setEnergy(600);
			s1.setEnergy_max(1200);
			s1.setSize(30);
			s1.setSpeed(5);
			s1.setFeed(0.5f);
			s1.setRace(0xffffff);
			s1.setAttack(3f);
			Animal l2 = new Animal(new Pos(r.nextInt(1800) + 100, r.nextInt(1800) + 100), world, s1);
//			AI ai = new AI(Behave.go_feed);
//			ai.getConditions().add(Condition.no_condition);
//			l2.getAitable().getAi().add(ai);
			l2.setAitable(AITable.getRandomTable(l2, r));
			world.registerStuff(l2);
		}



		BufferedImage img = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
		WritableImage wimg = new WritableImage(700, 700);
		Graphics2D g = (Graphics2D)img.getGraphics();
		GraphicsContext g2 = main.getWindowController().getCanvas().getGraphicsContext2D();
		while (main.isRunning) {
			long ms = System.currentTimeMillis();
			update(wimg, img, g, g2);
			long timeout = 64 - (System.currentTimeMillis() - ms);
			Thread.sleep(timeout < 1 ? -timeout:timeout);
		}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}


	private void update(WritableImage wimg, BufferedImage img, Graphics2D g, GraphicsContext g2){
		try{
			if(timepass)
				time++;



			world.update(g, timepass);
			SwingFXUtils.toFXImage(img, wimg);

			Platform.runLater(()->{
				g2.drawImage(wimg, 0, 0);
				main.getWindowController().updateUI(this);

			});
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}

	//get/set-----------------------------------------------------------------------
	public World getWorld(){
		return world;
	}

	public int getTime(){
		return this.time;
	}






}













