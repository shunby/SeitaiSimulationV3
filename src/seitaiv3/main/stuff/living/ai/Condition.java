/**
 *
 */
package seitaiv3.main.stuff.living.ai;

import java.util.Random;

import seitaiv3.main.stuff.living.Living.LivingType;
import seitaiv3.main.stuff.living.animal.Animal;
import seitaiv3.main.world.chunk.Plain;
import seitaiv3.main.world.chunk.Shelter;

/**
 *
 */
@FunctionalInterface
public interface Condition {
	public boolean eval(Animal liv);

	public static Condition getRandomCondition(Random rand){

		switch(rand.nextInt(21)){
		case 1://空腹
			return animal -> !animal.isFull();
		case 2://満腹
			return animal -> animal.isFull();
		case 3://近くに草がある
			return animal ->animal.search((liv)->liv.getType() == LivingType.Plant) != null;
		case 4://近くに草食がいる
			return animal ->animal.search((liv)->liv.getType() == LivingType.PlantEater && !(liv.getChunk() instanceof Shelter)) != null;
		case 5://近くに雑食がいる
			return animal ->animal.search((liv)->liv.getType() == LivingType.AnyEater && !(liv.getChunk() instanceof Shelter)) != null;
		case 6://近くに肉食がいる
			return animal ->animal.search((liv)->liv.getType() == LivingType.FleshEater && !(liv.getChunk() instanceof Shelter)) != null;
		case 7://近くに草がない
			return animal ->animal.search((liv)->liv.getType() == LivingType.Plant) == null;
		case 8://近くに草食がいない
			return animal ->animal.search((liv)->liv.getType() == LivingType.PlantEater && !(liv.getChunk() instanceof Shelter)) == null;
		case 9://近くに雑食がいない
			return animal ->animal.search((liv)->liv.getType() == LivingType.AnyEater && !(liv.getChunk() instanceof Shelter)) == null;
		case 10://近くに肉食がいない
			return animal ->animal.search((liv)->liv.getType() == LivingType.FleshEater && !(liv.getChunk() instanceof Shelter)) == null;
		case 17://近くに餌がある
			return (animal)->{
				return animal.search((l)->{
					if(l.getType() != LivingType.Plant)
						return animal.isFeed(l) && !(l.getChunk() instanceof Shelter);
					return animal.isFeed(l);
				}) != null;
			};
		case 18://近くに餌がない
			return (animal)->{
				return animal.search((l)->{
					if(l.getType() != LivingType.Plant)
						return animal.isFeed(l) && !(l.getChunk() instanceof Shelter);
					return animal.isFeed(l);
				}) == null;
			};
		case 19://近くに繁殖可能な相手がいる
			return (animal)->{
				return animal.search((liv)->{
					return !liv.isDead() && animal.isLove(liv);
				}) != null;
			};
		case 20://近くに繁殖可能な相手がいない
			return (animal)->{
				return animal.search((liv)->{
					return !liv.isDead() && animal.isLove(liv);
				}) == null;
			};
		case 11://平原にいる
			return animal->animal.getChunk() instanceof Plain;
		case 12://隠れ家にいる
			return animal->animal.getChunk() instanceof Shelter;
		case 13://近くに平原がある
			return animal->animal.searchChunk((chunk)->chunk instanceof Plain) != null;
		case 14://近くに隠れ家がある
			return animal->animal.searchChunk((chunk)->chunk instanceof Shelter) != null;
		case 15://近くに平原がない
			return animal->animal.searchChunk((chunk)->chunk instanceof Plain) == null;
		case 16://近くに隠れ家がない
			return animal->animal.searchChunk((chunk)->chunk instanceof Shelter) == null;
		case 0://無条件で
			return animal->true;

		};
		return null;
	}

}
