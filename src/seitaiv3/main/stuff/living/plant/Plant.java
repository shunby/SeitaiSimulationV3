/**
 *
 */
package seitaiv3.main.stuff.living.plant;

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
	public void updateAliving() {
		if(world.rand.nextInt(1500) == 1){
			for(int i = 0; i < 2; i++){
				Status s = new Status();
				s.setEnergy(700);
				s.setEnergy_max(1200);
				s.setSize(30);
				Living l1 = new Plant(new Pos(pos.getX() +  world.rand.nextInt(51) - 25, pos.getY() +world.rand.nextInt(51) - 25), world, s);
				world.registerStuff(l1);
			}
		}
	}


	@Override
	public LivingType getType() {
		return LivingType.Plant;
	}


	@Override
	public void updateDead() {

	}
}
