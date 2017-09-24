/**
 *
 */
package seitaiv3.main.stuff.living;

import java.awt.Color;
import java.awt.Graphics2D;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.World;

/**
 *
 */
public class Sensor extends Stuff {
	/**このセンサーを所有するLiving*/
	private Living living;
	/**このセンサーの届く範囲*/
	private int range;

	/**
	 */
	public Sensor(Living l, int range, World world) {
		super(l.getPos(), world);
		living = l;
		this.range = range;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(!collidedList.isEmpty() ? Color.RED :  Color.green);
		g.drawRect((int)pos.getX() - range / 2, (int)pos.getY() - range / 2, range, range);
	}

	@Override
	public void update() {
		super.update();
		this.pos.set(living.getPos());
	}

	@Override
	public void setCollided(Stuff collider) {
		if(collider != living)super.setCollided(collider);
		if(collider != living && !(collider instanceof Sensor))living.sensored(collider);
	}

	@Override
	protected boolean onDie() {
		return true;
	}

	@Override
	public int getWidth() {
		return range;
	}

	@Override
	public int getHeight() {
		return range;
	}



}
