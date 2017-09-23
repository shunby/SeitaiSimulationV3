/**
 *
 */
package seitaiv3.main.stuff.living;

import java.awt.Color;
import java.awt.Graphics2D;

import seitaiv3.main.stuff.DeathCause;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.World;

/**
 *
 */
public class Sensor extends Stuff {
	/**このセンサーを所有するLiving*/
	private Living living;

	/**
	 */
	public Sensor(Living l, int range, World world) {
		super(l.getPos(), range, range, world);
		living = l;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(!collidedList.isEmpty() ? Color.RED :  Color.green);
		g.drawRect(pos.getX() - width / 2, pos.getY() - height / 2, width, height);
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
	protected void onDie(DeathCause cause) {
		// TODO 自動生成されたメソッド・スタブ

	}



}
