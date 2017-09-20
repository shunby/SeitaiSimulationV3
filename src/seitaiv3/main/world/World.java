package seitaiv3.main.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.chunk.Liner4Tree;

public class World {
	/**幅と奥行き*/
	private int width, height;
	/**四分探索木*/
	private Liner4Tree tree;
	/**衝突リスト*/
	private List<Stuff> collisionList;
	/**オブジェクト*/
	private List<Stuff> stuffs;
	/**random*/
	public Random rand;
	/**カメラ*/
	private Pos camera;

	public World(int width, int height){
		this.width = width;
		this.height = height;
		tree = new Liner4Tree(3, this);
		collisionList = new ArrayList<>();
		rand = new Random();
		stuffs = new ArrayList<>();
		camera = new Pos(this,0, 0);
	}

	/**
	 *
	 */
	public void update(Graphics2D g) {
		stuffs.forEach((stuff)->stuff.preUpdate());
		collisionCheck();
		stuffs.forEach((stuff)->stuff.update());
		stuffs.forEach((stuff)->{
			//描画
			if(isInCamera(stuff.getPos()))stuff.draw(g);
		});
		stuffs.forEach((stuff)->stuff.postUpdate());

	}




	/**衝突判定*/
	private void collisionCheck(){
		tree.collisionCheck(collisionList);




		Stuff c1 = null;
		Stuff c2 = null;
		if(collisionList.size() > 0){
			int i = 0;
			while(i < collisionList.size()){
				c1 = collisionList.get(i++);
				c2 = collisionList.get(i++);
				if(c1 == null)continue;
				Pos p1 = c1.getPos();
				Pos p2 = c2.getPos();
				int w1 = c1.getWidth();
				int h1 = c1.getHeight();
				int w2 = c2.getWidth();
				int h2 = c2.getHeight();

				//矩形の衝突判定

				if((Math.abs(p1.getX() - p2.getX()) < (w1+ w2) / 2)
					&&
					(Math.abs(p1.getY() - p2.getY()) < (h1 + h2) / 2)
				){

					c1.setCollided(c2);
					c2.setCollided(c1);
				}


			}
		}

	}

	public void registerStuff(Stuff s){
		tree.register(s.getOFT());
		stuffs.add(s);
	}


	//ここからget/set-------------------------------
	public Pos getCamera(){
		return camera;
	}

	public boolean isInCamera(Pos p){
		int x = p.getX();
		int y = p.getY();
		int cx = camera.getX();
		int cy = camera.getY();
		return cx <= x && x <= cx + 700 && cy <= y && y <= cy + 700;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Liner4Tree getTree() {
		return tree;
	}

	public List<Stuff> getStuffs(){
		return stuffs;
	}




}
