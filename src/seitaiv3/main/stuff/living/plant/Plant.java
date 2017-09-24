/**
 *
 */
package seitaiv3.main.stuff.living.plant;

import java.awt.Graphics2D;

import seitaiv3.main.Resources;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

/**
 * @author 春太朗
 *
 */
public class Plant extends Living {

	/**
	 * @param p
	 * @param width
	 * @param height
	 * @param world
	 */
	public Plant(Pos p, World world, Status status) {
		super(p, world, status);
	}

	@Override
	public void update() {
		super.update();

	}

	@Override
	public void draw(Graphics2D g) {
		int siz = (int)status.getValue(Status.SIZE);
		g.drawImage(Resources.plant, (int)pos.getX() - siz/2, (int)pos.getY() - siz/2, siz, siz, null);
	}

	@Override
	protected boolean onDie() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}