package seitaiv3.main.stuff;

import java.util.LinkedList;
import java.util.List;

import seitaiv3.main.world.World;
import javafx.scene.shape.Rectangle;

public class Stuff {
	/**座標*/
	private int x, y, width, height;
	/**このオブジェクトのいる世界*/
	private World world;
	/**衝突リスト*/
	private List<Stuff> collidedList;


	/**
	 *
	 */
	public Stuff() {
		collidedList = new LinkedList<>();
	}

	public void update(){

	}




	/**衝突リストに加える*/
	public void setCollided(Stuff collider) {
		collidedList.add(collider);
	}



	//ここからgetter/setter----------------------------
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
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




}
