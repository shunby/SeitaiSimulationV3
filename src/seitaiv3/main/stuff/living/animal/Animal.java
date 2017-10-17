/**
 *
 */
package seitaiv3.main.stuff.living.animal;

import java.util.function.Predicate;

import seitaiv3.main.Resources;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.Vector;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.plant.Plant;
import seitaiv3.main.stuff.living.status.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;
import seitaiv3.main.world.chunk.Chunk;

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

		int race = status.getRace();
		//肉食度によって画像を変える
		switch(getType()){
		case PlantEater:
			img = Resources.getChangedImage(Resources.planteater, race>>>16 & 0xff, race>>>8 & 0xff, race & 0xff);
			break;
		case FleshEater:
			img = Resources.getChangedImage(Resources.flesheater, race>>>16 & 0xff, race>>>8 & 0xff, race & 0xff);
			break;
		case AnyEater:
			img = Resources.getChangedImage(Resources.anyeater, race>>>16 & 0xff, race>>>8 & 0xff, race & 0xff);
			break;
		default:
			break;
		}
	}


	@Override
	public void updateAliving() {
		if(!isFull())chase((l)->isFeed(l));
		else chase((l)->!l.isDead() && isLove(l));
		if(target != null){
			moving = target.getPos().getSub(new Vector(pos.getX(), pos.getY()));
		}else if(world.rand.nextInt(50)==0)moving.set(world.rand.nextInt(5) - 2, world.rand.nextInt(5) - 2);


		if(isFull()){
			catchLove();
		}else{
			catchFeed();
		}


	}

	private void catchLove() {
		collidedList.forEach((col)->{
			if(col instanceof Living && !((Living)col).isDead() && isLove((Living)col)){
				Status cstat = Status.makeChildStatus(status, ((Living)col).getStatus());

				Animal child = new Animal(new Pos(pos.getX() + 15f, pos.getY() + 15f), world, cstat);
				world.registerStuff(child);

			}
		});
	}


	@Override
	public void postUpdate() {
		super.postUpdate();
	}

	/**funcで渡された条件に合致するもののうち最近のLivingを追う*/
	protected void chase(Predicate<Living> func){
		int eye = 3;


		target = null;
		float nearest = Float.MAX_VALUE;
		int w = world.getChunks().length;
		int h = world.getChunks()[0].length;
		Chunk[][] chunks = world.getChunks();
		for(int x = chunk.x - eye;x <= chunk.x + eye; x++){
			for(int y = chunk.y - eye;y <= chunk.y + eye;y++ ){
				//チャンクごとに判定
				if(0 <= x  && x < w && 0 <= y && y < h){//チャンクが存在するか
					for(Stuff stuff: chunks[x][y].getStuffs()){
						if(stuff != this && stuff instanceof Living){
							if(func.test((Living)stuff)){
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
			if(col instanceof Living && isFeed((Living)col)){
				eat((Living)col);
			}
		});
	}

	/**食べる*/
	protected void eat(Living liv){
		float atk = status.getAttack();
		Status stat = liv.getStatus();


		status.setEnergy(status.getEnergy() + stat.gainEnergy(atk));

	}

	/**満腹判定*/
	public boolean isFull(){
		return status.getEnergy() > (status.getEnergy_max() * 9f) / 10f;
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












