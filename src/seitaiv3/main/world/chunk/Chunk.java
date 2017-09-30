package seitaiv3.main.world.chunk;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.Pos;

public class Chunk {
	/**チャンク位置*/
	private final int x, y;

	private Set<Stuff> stuffs;

	public Chunk(int x, int y){
		this.x = x;
		this.y = y;
		stuffs = new HashSet<>();
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
}
