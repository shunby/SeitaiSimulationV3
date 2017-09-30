package seitaiv3.main.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.living.Sensor;
import seitaiv3.main.world.chunk.Chunk;
import seitaiv3.main.world.chunk.Liner4Tree;

public class World {
	/**幅と奥行き*/
	private int width, height;
	/**オブジェクト*/
	private Set<Stuff> stuffs;
	/**バッファ*/
	private Set<Stuff> stuffsBuffer;
	/**更新処理中か*/
	private boolean isUpdating;
	/**random*/
	public Random rand;
	/**カメラ*/
	private Pos camera;
	/**チャンク*/
	private Chunk[][] chunks;
	/**チャンクの一辺の大きさ*/
	private final int chunkLength = 50;

	public World(int width, int height){
		this.width = width;
		this.height = height;
		rand = new Random();
		chunks = new Chunk[width/chunkLength][height/chunkLength];
		stuffs = new HashSet<>();
		stuffsBuffer = new HashSet<>();
		camera = new Pos(0, 0);
	}

	/**
	 *
	 */
	public void update(Graphics2D g)throws Exception {
		//バッファの中身を全てリストに追加
		stuffsBuffer.forEach((stuff)->registerStuff(stuff));
		stuffsBuffer.clear();

		isUpdating = true;

		stuffs.forEach((stuff)->stuff.preUpdate());
		collisionCheck();

		for(Iterator<Stuff> iter = stuffs.iterator(); iter.hasNext();){
			iter.next().update();
		}

		stuffs.forEach((stuff)->{
			//描画
			if(isInCamera(stuff.getPos()))stuff.draw(g);
		});
		stuffs.forEach((stuff)->stuff.postUpdate());

		for(Iterator<Stuff> iter = stuffs.iterator(); iter.hasNext();){
			if(iter.next().isRemovable())iter.remove();
		}

		isUpdating = false;
	}




	/**衝突判定*/
	private void collisionCheck(){

		for(Chunk[] c:chunks){
			for(Chunk chunk:c){
				chunk.collisionCheck();
			}
		}

		Stuff c1 = null;
		Stuff c2 = null;
		if(collisionList.size() > 0){
			int i = 0;
			while(i < collisionList.size()){
				c1 = collisionList.get(i++);
				c2 = collisionList.get(i++);

				if(c1 instanceof Sensor && c2 instanceof Sensor)continue;



			}
		}

	}

	public void registerStuff(Stuff s){
		if(isUpdating){
			stuffsBuffer.add(s);
			return;
		}

		stuffs.add(s);
	}


	//ここからget/set-------------------------------
	public Pos getCamera(){
		return camera;
	}

	public boolean isInCamera(Pos p){
		float x = p.getX();
		float y = p.getY();
		float cx = camera.getX();
		float cy = camera.getY();
		return cx <= x && x <= cx + 700 && cy <= y && y <= cy + 700;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Set<Stuff> getStuffs(){
		return stuffs;
	}




}
