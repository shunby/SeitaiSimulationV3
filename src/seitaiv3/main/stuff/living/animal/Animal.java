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
import seitaiv3.main.stuff.living.status.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

/**
 * 動物
 */
public class Animal extends Living {


	/**
	 * @param p
	 * @param world
	 * @param status
	 */
	public Animal(Pos p, World world, Status status) {
		super(p, world, status);


		moving.set(world.rand.nextInt(5)-2, world.rand.nextInt(5)-2);
		//肉食度によって画像を変える
		switch(getType()){
		case PlantEater:
			img = Resources.planteater;
			break;
		case FleshEater:
			img = Resources.flesheater;
			break;
		case AnyEater:
			img = Resources.anyeater;
			break;
		default:
			break;
		}
	}


	@Override
	public void update() {
		super.update();
		if(world.rand.nextInt(50) == 2){
			moving.set(world.rand.nextInt(5)-2, world.rand.nextInt(5)-2);
		}
		catchFeed();

	}

	@Override
	public void postUpdate() {
		super.postUpdate();
	}

	/**餌をとる*/
	protected void catchFeed(){
		LivingType type = getType();
		collidedList.forEach((col)->{
			if(col instanceof Living && ((Living)col).isFeed(this)){
				eat((Living)col);
			}
		});
	}

	/**食べる*/
	protected void eat(Living liv){
		int gain = 2;
		Status stat = liv.getStatus();
		if(stat.getFood() > 0){
			stat.setFeed(stat.getFood() - gain);
			status.setFood(status.getFood() + gain);
		}else{
			stat.setHp(stat.getHp() - gain);
			status.setHp(status.getHp() + gain);
		}

	}


	@Override
	protected void onRemoved() {
	}


	@Override
	public LivingType getType() {
		float feed = status.getFeed();

		if(feed < 0.4){
			return LivingType.PlantEater;
		}else if(feed > 0.6){
			return LivingType.FleshEater;
		}else{
			return LivingType.AnyEater;
		}

	}

}












