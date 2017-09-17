package seitaiv3.main.stuff;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;
import seitaiv3.main.world.chunk.Liner4Tree;
import seitaiv3.main.world.chunk.OFT;

public abstract class Stuff {
	/**座標*/
	protected Pos pos;
	protected int width;
	protected int height;
	/**このオブジェクトのいる世界*/
	private World world;
	/**衝突リスト*/
	private List<Stuff> collidedList;
	/**空間登録用のOFTオブジェクト*/
	private OFT oft;

	/**
	 *
	 */
	public Stuff(Pos p, int width, int height, World world) {
		this.pos = p;
		this.width = width;
		this.height = height;
		this.world = world;
		collidedList = new LinkedList<>();
		oft = new OFT(this);
	}

	/**更新処理*/
	public void update(Graphics g){
		//衝突判定の更新
		oft.remove();
		world.getTree().register(oft);

		//状態の更新
		onUpdate();

		//バッファに描画
		draw(g);

		//衝突リストを空に
		collidedList.clear();
	}
	/**描画処理*/
	protected abstract void draw(Graphics g);
	/**状態の更新処理*/
	protected abstract void onUpdate();


	/**衝突リストに加える*/
	public void setCollided(Stuff collider) {
		collidedList.add(collider);
	}



	//ここからgetter/setter----------------------------
	public Pos getPos() {
		return pos;
	}
	public void setPos(Pos p) {
		this.pos = p;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public OFT getOFT() {
		return oft;
	}




}
