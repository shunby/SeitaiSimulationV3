package seitaiv3.main.world.chunk;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.Pos;

public class Chunk {
	/**チャンク位置*/
	public final int x, y;

	/**日照量*/
	private float sun;
	/**内蔵エネルギー量*/
	private float energy;
	/**最大内蔵エネルギー*/
	private float energy_max;

	private Set<Stuff> stuffs;

	public Chunk(int x, int y){
		this.x = x;
		this.y = y;
		this.energy = 1000;
		this.energy_max = 10000;
		this.sun = 0.7f;
		stuffs = new HashSet<>();
	}

	/**フレームごとの更新処理*/
	public void update(){

	}

	public void collisionCheck(){
		for(Iterator<Stuff> iter = stuffs.iterator(); iter.hasNext();){
			Stuff c = iter.next();
			for(Iterator<Stuff> itr = stuffs.iterator(); itr.hasNext();){
				Stuff c1 = itr.next();

				if(c1 == c)continue;
				Pos p1 = c.getPos();
				Pos p2 = c1.getPos();
				int w1 = c.getWidth();
				int h1 = c.getHeight();
				int w2 = c1.getWidth();
				int h2 = c1.getHeight();

				//矩形の衝突判定

				if((Math.abs(p1.getX() - p2.getX()) < (w1+ w2) / 2)
					&&
					(Math.abs(p1.getY() - p2.getY()) < (h1 + h2) / 2)
				){

					c.setCollided(c1);
				}
			}
		}
	}

	public Set<Stuff> getStuffs(){
		return stuffs;
	}

	public float getSun(){
		return sun;
	}


	public float getEnergy(){
		return energy;
	}

	public float gainEnergy(float gain){
		if(gain <= energy){
			energy-=gain;
			return gain;
		}else{
			gain = energy;
			energy = 0;
			return gain;
		}
	}
}
