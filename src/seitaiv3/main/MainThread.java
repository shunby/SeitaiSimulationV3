package seitaiv3.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

/**
 *メインの処理を実行するスレッド
 */
public class MainThread extends Thread {
	/**Main*/
	private Main main;
	/**世界*/
	private World world;



	public MainThread(){
		main = Main.get();
		world = new World(2000, 2000);

		Living l = new Living(new Pos(100, 100), 10, 10, world);
		world.getTree().register(l.getOFT());

		Living l1 = new Living(new Pos(200, 100), 20, 20, world);
		world.getTree().register(l1.getOFT());

		Living l2 = new Living(new Pos(100, 200), 50, 50, world);
		world.getTree().register(l2.getOFT());
	}

	@Override
	public void run(){
		while(main.isRunning){
			if(main.getBufferLength() < 100){
				BufferedImage img = new BufferedImage(700, 700, BufferedImage.TYPE_INT_BGR);
				Graphics g = img.getGraphics();
				g.setColor(Color.black);
				world.update(g);
				main.addBuffer(img);
			}

			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}


}













