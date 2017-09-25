/**
 *
 */
package seitaiv3.main.stuff.living.animal;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import seitaiv3.main.Resources;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.Sensor;
import seitaiv3.main.stuff.living.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

/**
 * 動物
 */
public class Animal extends Living {

	/**触覚*/
	protected Sensor sensor;
	/**触覚に触れたもの(フレームごとに更新)*/
	protected Set<Stuff> sensoredList;



	/**
	 * @param p
	 * @param world
	 * @param status
	 */
	public Animal(Pos p, World world, Status status) {
		super(p, world, status);

		sensor = new Sensor(this, 100, world);
		sensoredList = new HashSet<>();
		world.registerStuff(sensor);

		moving.set(world.rand.nextInt(5)-2, world.rand.nextInt(5)-2);
		//肉食度によって画像を変える
		float feed = status.getFeed();
		if(feed < 0.4){//草食
			img = Resources.planteater;
		}else if(0.6 < feed){//肉食
			img = Resources.flesheater;
		}else{//雑食
			img = Resources.alleater;
		}
	}


	@Override
	public void update() {
		super.update();
		if(world.rand.nextInt(50) == 2){
			moving.set(world.rand.nextInt(5)-2, world.rand.nextInt(5)-2);
		}
	}

	@Override
	public void postUpdate() {
		super.postUpdate();
		sensoredList.clear();
	}

	/**
	 * センサーに触れた
	 */
	public void sensored(Stuff collider) {
		sensoredList.add(collider);
	}


	@Override
	protected void onRemoved() {
		sensor.die();
	}

}












