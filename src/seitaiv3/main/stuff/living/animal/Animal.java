/**
 *
 */
package seitaiv3.main.stuff.living.animal;

import java.util.function.Predicate;

import seitaiv3.main.Resources;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.Vector;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.ai.AITable;
import seitaiv3.main.stuff.living.plant.Plant;
import seitaiv3.main.stuff.living.status.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;
import seitaiv3.main.world.chunk.Chunk;
import seitaiv3.main.world.chunk.Shelter;

/**
 * 動物
 */
public class Animal extends Living {

	/**AI*/
	private AITable aitable;


	/**
	 * @param p
	 * @param world
	 * @param status
	 */
	public Animal(Pos p, World world, Status status) {
		super(p, world, status);
		aitable = new AITable(this);
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

		aitable.update(this);

		if(isFull()){
			catchLove();
		}else{
			catchFeed();
		}


	}

	private void catchLove() {
		collidedList.forEach((col)->{
			if(col instanceof Animal && !((Animal)col).isDead() && isLove((Animal)col)){
				Status cstat = Status.makeChildStatus(status, ((Animal)col).getStatus());

				Animal child = new Animal(new Pos(pos.getX() + 15f, pos.getY() + 15f), world, cstat);
				AITable ai = AITable.makeChild(world.rand, child, aitable, ((Animal)col).aitable);
				world.registerStuff(child);

			}
		});
	}


	@Override
	public void postUpdate() {
		super.postUpdate();
	}

	/**funcで渡された条件に合致するもののうち最近のLivingを探す*/
	public Living search(Predicate<Living> func){
		int eye = 3;

		Living target = null;
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
		return target;

	}

	/**funcで渡された条件に合致するもののうち最近のChunkを探す*/
	public Chunk searchChunk(Predicate<Chunk> func){
		int eye = 3;

		Chunk target = null;
		float nearest = Float.MAX_VALUE;
		int w = world.getChunks().length;
		int h = world.getChunks()[0].length;
		int l = world.getChunkLength();
		Chunk[][] chunks = world.getChunks();
		for(int x = chunk.x - eye;x <= chunk.x + eye; x++){
			for(int y = chunk.y - eye;y <= chunk.y + eye;y++ ){
				//チャンクごとに判定
				if(0 <= x  && x < w && 0 <= y && y < h){//チャンクが存在するか
					if(func.test(chunks[x][y])){
						float distance = Pos.getDistance(pos, new Pos((x + 0.5f) * l, (y + 0.5f) * l));
						if(distance < nearest){
							nearest = distance;
							target = chunks[x][y];
						}
					}
				}

			}
		}
		return target;

	}

	/**餌をとる*/
	protected void catchFeed(){
		collidedList.forEach((col)->{
			if(col instanceof Living && isFeed((Living)col)){
				if(((Living)col).getType() != LivingType.Plant && !(col.getChunk() instanceof Shelter)){
					eat((Living)col);
				}else if(((Living)col).getType() == LivingType.Plant)eat((Living)col);

			}
		});
	}

	/**食べる*/
	protected void eat(Living liv){
		status.setEnergy(status.getEnergy() +
				liv.getStatus().gainEnergy(status.getAttack()));

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


	public AITable getAitable() {
		return aitable;
	}


	public void setAitable(AITable aitable) {
		this.aitable = aitable;
	}



}












