/**
 *
 */
package seitaiv3.main.stuff.living.ai;

import java.util.Random;

import seitaiv3.main.stuff.Vector;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.Living.LivingType;
import seitaiv3.main.stuff.living.animal.Animal;
import seitaiv3.main.world.chunk.Shelter;

/**
 *
 */
@FunctionalInterface
public interface Behave {
	public boolean behave(Animal liv);

	public static Behave getRandomBehave(Random rand){
		switch(rand.nextInt(10)){
		case 0://近くの草に寄る
			return (animal)->{
				Living l = animal.search((liv)->liv.getType() == LivingType.Plant);
				if(l != null){
					Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(v.x, v.y);
					return true;
				}else return false;
			};
		case 1://近くの草食による
			return (animal)->{
				Living l = animal.search((liv)->liv.getType() == LivingType.PlantEater && !(animal.getChunk() instanceof Shelter));
				if(l != null){
					Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(v.x, v.y);
					return true;
				}else return false;
			};
		case 2://近くの雑食による
			return (animal)->{
				Living l = animal.search((liv)->liv.getType() == LivingType.AnyEater && !(animal.getChunk() instanceof Shelter));
				if(l != null){
					Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(v.x, v.y);
					return true;
				}else return false;
			};
		case 3://近くの草食による
			return (animal)->{
				Living l = animal.search((liv)->liv.getType() == LivingType.FleshEater && !(animal.getChunk() instanceof Shelter));
				if(l != null){
					Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(v.x, v.y);
					return true;
				}else return false;
			};
		case 4://近くの草から離れる
			return (animal)->{
				Living l = animal.search((liv)->liv.getType() == LivingType.Plant);
				if(l != null){
					Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(-v.x, -v.y);
					return true;
				}else return false;
			};
		case 5://近くの草食から離れる
			return (animal)->{
				Living l = animal.search((liv)->liv.getType() == LivingType.PlantEater && !(animal.getChunk() instanceof Shelter));
				if(l != null){
					Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(-v.x, -v.y);
					return true;
				}else return false;
			};
		case 6://近くの雑食から離れる
			return (animal)->{
				Living l = animal.search((liv)->liv.getType() == LivingType.AnyEater && !(animal.getChunk() instanceof Shelter));
				if(l != null){
					Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(-v.x, -v.y);
					return true;
				}else return false;
			};
		case 7://近くの肉食から離れる
			return (animal)->{
				Living l = animal.search((liv)->liv.getType() == LivingType.FleshEater && !(animal.getChunk() instanceof Shelter));
				if(l != null){
					Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(-v.x, -v.y);
					return true;
				}else return false;
			};
		case 8://近くの餌による
			return (animal)->{
				Living living = animal.search(
					(liv)->{
						if(liv.getType() != LivingType.Plant)
							return animal.isFeed(liv) && !(liv.getChunk() instanceof Shelter);
						return animal.isFeed(liv);
					}
				);
				if(living != null){
					Vector v = living.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(v.x, v.y);
					return true;
				}else return false;
			};
		case 9://近くの餌による
			return (animal)->{
				Living living = animal.search(
					(liv)->{
						if(liv.getType() != LivingType.Plant)
							return animal.isFeed(liv) && !(liv.getChunk() instanceof Shelter);
						return animal.isFeed(liv);
					}
				);
				if(living != null){
					Vector v = living.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(-v.x, -v.y);
					return true;
				}else return false;
			};
//
//				if(!isFull())chase((l)->{
//					if(l.getType() != LivingType.Plant)
//						return isFeed(l) && !(l.getChunk() instanceof Shelter);
//					return isFeed(l);
//				});
//				else chase((l)->!l.isDead() && isLove(l));
//				if(target != null){
//					moving = target.getPos().getSub(new Vector(pos.getX(), pos.getY()));
//				}else if(world.rand.nextInt(50)==0)moving.set(world.rand.nextInt(5) - 2, world.rand.nextInt(5) - 2);
			//};
		}
		return null;
	}

}
