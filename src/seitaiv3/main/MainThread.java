package seitaiv3.main;

import java.util.Vector;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.World;
import seitaiv3.main.world.chunk.Liner4Tree;

/**
 *メインの処理を実行するスレッド
 */
public class MainThread extends Thread {
	/**Main*/
	private Main main;
	/**世界*/
	private World world;

	/**衝突リスト*/
	private Vector<Stuff> collisionList;

	public MainThread(){
		main = Main.get();
		world = new World(2000, 2000);

		Liner4Tree.Init(7, world);
	}

	@Override
	public void run(){
		while(main.isRunning){
			collisionCheck();
		}
	}

	/**衝突判定*/
	private void collisionCheck(){
		Liner4Tree.collisionCheck(collisionList);
		Stuff collider1 = null;
		Stuff collider2 = null;
		for(int i = 0; i+1 < collisionList.size(); i+=2){//0と1, 2と3, 2nと(2n+1)が衝突する
			collider1 = collisionList.get(i);
			collider2 = collisionList.get(i + 1);
			collider1.setCollided(collider2);
			collider2.setCollided(collider1);
		}
	}
}













