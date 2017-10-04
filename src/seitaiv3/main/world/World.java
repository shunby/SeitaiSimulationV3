package seitaiv3.main.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowStateListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import seitaiv3.main.Main;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.window.WindowController;
import seitaiv3.main.world.chunk.Chunk;

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
		for(int x = 0; x < width/chunkLength; x++){
			for(int y = 0; y < height/chunkLength; y++){
				chunks[x][y] = new Chunk(x, y, this);
			}
		}
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

		//背景の描画
		g.setColor(new Color(160, 82, 45));
		g.fillRect(0, 0, 700, 700);

		//チャンクの更新
		for(int x = 0; x < chunks.length; x++){
			for(int y = 0; y < chunks[0].length; y++){
				chunks[x][y].update(g);
			}
		}

		//罫線の描画
		g.setColor(Color.black);
		for(int x = 0; x <= chunks.length; x++){
			g.drawLine(x * chunkLength - (int)camera.getX(), 0, x * chunkLength - (int)camera.getX(), height);
		}
		for(int y = 0; y <= chunks[0].length; y++){
			g.drawLine(0, y * chunkLength - (int)camera.getY(), width, y * chunkLength - (int)camera.getY());
		}
		//カメラ位置の更新
		WindowController wc = Main.get().getWindowController();
		int move = 30;
		int addX = (wc.left ? -move : 0) + (wc.right ? move : 0);
		int addY = (wc.up ? -move : 0) + (wc.down ? move : 0);
		camera.add(addX, addY);

		//更新
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

	}

	public void registerStuff(Stuff s){
		if(isUpdating){
			stuffsBuffer.add(s);
			return;
		}

		stuffs.add(s);
	}


	//ここからget/set-------------------------------

	public Chunk getChunk(Pos p){
		return chunks[(int) (p.getX()/chunkLength)][(int) (p.getY()/chunkLength)];
	}

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

	public Pos getWindowPos(Pos p){
		return new Pos(p.getX() - camera.getX(), p.getY() - camera.getY());
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

	public Chunk[][] getChunks(){
		return chunks;
	}

	public int getChunkLength(){
		return chunkLength;
	}




}
