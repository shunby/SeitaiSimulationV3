package seitaiv3.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import seitaiv3.main.stuff.living.Living;
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

	public MainThread(){
		main = Main.get();
		world = new World(3000, 3000);
	}

	@Override
	public void run(){
		try{

		Random r = new Random();
		for(int i = 0; i < 1000; i++){
			Status s = new Status();
			s.setHp(120);
			s.setHp_max(1200);
			s.setFood(120);
			s.setFood_max(1200);
			s.setSize(30);
			Living l1 = new Plant(new Pos(r.nextInt(2500) + 100, r.nextInt(2500) + 100), world, s);
			world.registerStuff(l1);

			Status s1 = new Status();
			s1.setHp(120);
			s1.setHp_max(1200);
			s1.setFood(120);
			s1.setFood_max(1200);
			s1.setSize(30);
			s1.setSpeed(5);
			s1.setFeed(r.nextFloat());
			Living l2 = new Animal(new Pos(r.nextInt(2500) + 100, r.nextInt(2500) + 100), world, s1);
			world.registerStuff(l2);
		}

		//Graphics2D g = main.getWindowController().getCanvas().getGraphicsContext2D();
		BufferedImage img = new BufferedImage(700, 700, BufferedImage.TYPE_INT_ARGB);
		WritableImage wimg = new WritableImage(700, 700);
		Graphics2D g = (Graphics2D)img.getGraphics();
		Color bgColor = new Color(160, 82, 45);
		GraphicsContext g2 = main.getWindowController().getCanvas().getGraphicsContext2D();
		while (main.isRunning) {
			long ms = System.currentTimeMillis();
			update(wimg, img, g, g2, bgColor);
			long timeout = 64 - (System.currentTimeMillis() - ms);
			System.out.println(timeout);
			Thread.sleep(timeout < 1 ? -timeout:timeout);
		}
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}


	private void update(WritableImage wimg, BufferedImage img, Graphics2D g, GraphicsContext g2, Color bgColor){
		try{
			time++;

			if(time % 2000 == 0){
				System.out.println("Running Garbage Collection");
				System.gc();
			}

			g.setColor(bgColor);
			g.fillRect(0, 0, 700, 700);

			if(world.rand.nextInt(10) == 1){
				Status s = new Status();
				s.setHp(120);
				s.setHp_max(1200);
				s.setFood(120);
				s.setFood_max(1200);
				s.setSize(30);
				Living l1 = new Plant(new Pos(world.rand.nextInt(2500) + 100, world.rand.nextInt(2500) + 100), world, s);
				world.registerStuff(l1);
			}

			world.update(g);
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













