/**
 *
 */
package seitaiv3.main.world.chunk;

import java.awt.Color;
import java.awt.Graphics2D;

import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

/**
 * @author 春太朗
 *
 */
public class Shelter extends Chunk {

	/**
	 * @param x
	 * @param y
	 * @param world
	 */
	public Shelter(int x, int y, World world) {
		super(x, y, world);
	}

	protected void draw(Graphics2D g){
		super.draw(g);
		int l = world.getChunkLength();
		Pos p =  world.getWindowPos(new Pos(x * l, y * l));
		g.setColor(Color.blue);
		g.fillOval((int)p.getX() + l/2, (int)p.getY() + l/2, 20, 20);
	}

}
