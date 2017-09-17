package seitaiv3.main.world;

import java.awt.Graphics;
import java.util.Vector;

import javafx.scene.canvas.GraphicsContext;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.chunk.Liner4Tree;

public class World {
	/**幅と奥行き*/
	private int width, height;
	/**四分探索木*/
	private Liner4Tree tree;
	/**衝突リスト*/
	private Vector<Stuff> collisionList;

	public World(int width, int height){
		this.width = width;
		this.height = height;
		tree = new Liner4Tree(7, this);
		collisionList = new Vector<>();
	}

	/**
	 *
	 */
	public void update(Graphics g) {
		collisionCheck();
		tree.updateStuff(g);
	}




	/**衝突判定*/
	private void collisionCheck(){
		tree.collisionCheck(collisionList);
		Stuff collider1 = null;
		Stuff collider2 = null;
		for(int i = 0; i+1 < collisionList.size(); i+=2){//0と1, 2と3, 2nと(2n+1)が衝突する
			collider1 = collisionList.get(i);
			collider2 = collisionList.get(i + 1);
			collider1.setCollided(collider2);
			collider2.setCollided(collider1);
		}
	}


	//ここからget/set-------------------------------
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Liner4Tree getTree() {
		return tree;
	}




}
