/**
 *
 */
package seitaiv3.main.stuff.living.plant;

import java.awt.Graphics2D;

import seitaiv3.main.Resources;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.status.Status;
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
		if(world.rand.nextInt(700) == 1){
			Status s = new Status();
			s.setHp(120);
			s.setHp_max(1200);
			s.setFood(120);
			s.setFood_max(1200);
			s.setSize(30);
			Living l1 = new Plant(new Pos(pos.getX() +  world.rand.nextInt(21) - 10, pos.getY() +world.rand.nextInt(21) - 10), world, s);
			world.registerStuff(l1);
		}
	}


	@Override
	public LivingType getType() {
		return LivingType.Plant;
	}
}
