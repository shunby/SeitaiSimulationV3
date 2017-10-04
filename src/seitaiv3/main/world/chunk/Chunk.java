package seitaiv3.main.world.chunk;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

public class Chunk {
	/**チャンク位置*/
	public final int x, y;

	/**日照量*/
	private float sun;
	/**内蔵エネルギー量*/
	private float energy;
	/**最大内蔵エネルギー*/
	private float energy_max;

	private World world;

	private Set<Stuff> stuffs;

	public Chunk(int x, int y, World world){
		this.x = x;
		this.y = y;
		this.world = world;
		this.energy = 10000;
		this.energy_max = 100000;
		this.sun = 0.5f;
		stuffs = new HashSet<>();
	}

	/**フレームごとの更新処理*/
	public void update(Graphics2D g){
		if(energy > energy_max)energy_max = energy;
		int l = world.getChunkLength();
		if(world.isInCamera(new Pos(x * l, y * l))
				|| world.isInCamera(new Pos(x * l + l, y * l))
				|| world.isInCamera(new Pos(x * l, y * l + l))
				|| world.isInCamera(new Pos(x * l + l, y * l + l))){
			Pos p = world.getWindowPos(new Pos(x * l, y * l ));
			g.setColor(Color.BLACK);
			g.drawString(String.format("%.1f", energy), p.getX() + 10, p.getY() + 10);

		}
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
			System.out.println(energy);
			energy-=gain;
			System.out.println(energy);
			System.out.println("====");
			return gain;
		}else{
			gain = energy;
			energy = 0;
			return gain;
		}
	}
}
