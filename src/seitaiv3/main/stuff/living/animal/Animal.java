/**
 *
 */
package seitaiv3.main.stuff.living.animal;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import seitaiv3.main.Resources;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.Vector;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.status.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

/**
 * 動物
 */
public class Animal extends Living {

	private Living target;


	/**
	 * @param p
	 * @param world
	 * @param status
	 */
	public Animal(Pos p, World world, Status status) {
		super(p, world, status);

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
	public void updateAliving() {
		chaseFeed();
		if(target != null){
			moving = target.getPos().getSub(new Vector(pos.getX(), pos.getY()));
		}else if(world.rand.nextInt(50)==0)moving.set(world.rand.nextInt(5) - 2, world.rand.nextInt(5) - 2);
		catchFeed();

	}

	@Override
	public void postUpdate() {
		super.postUpdate();
	}

	/**餌を追う*/
	protected void chaseFeed(){
		int eye = 3;


		target = null;
		float nearest = Float.MAX_VALUE;
		int w = world.getChunks().length;
		int h = world.getChunks()[0].length;
		for(int x = chunk.x - eye;x <= chunk.x + eye; x++){
			for(int y = chunk.y - eye;y <= chunk.y + eye;y++ ){
				//チャンクごとに判定
				if(0 <= x  && x < w && 0 <= y && y < h){//チャンクが存在するか
					for(Stuff stuff: world.getChunks()[x][y].getStuffs()){
						if(stuff != this && stuff instanceof Living){
							if(((Living)stuff).isFeedOf(this)){
								float distance = Pos.getDistance(pos, stuff.getPos());
								if(distance < nearest){
									nearest = distance;
									target = ((Living)stuff);
								}
							}
						}
					}
				}

			}
		}

	}

	/**餌をとる*/
	protected void catchFeed(){
		collidedList.forEach((col)->{
			if(col instanceof Living && ((Living)col).isFeedOf(this)){
				eat((Living)col);
			}
		});
	}

	/**食べる*/
	protected void eat(Living liv){
		float atk = status.getAttack();
		Status stat = liv.getStatus();

		stat.setEnergy(stat.getEnergy() - atk);
		status.setEnergy(status.getEnergy() + atk);

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


	@Override
	public void updateDead() {

	}

}












