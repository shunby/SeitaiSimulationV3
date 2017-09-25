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
		img = Resources.getChangedImage(Resources.plant, 0, 255, 0);
	}

	@Override
	public void update() {
		super.update();

	}
}
