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

		switch(rand.nextInt(10)){
		case 1://空腹
			return animal -> !animal.isFull();
		case 2://満腹
			return animal -> animal.isFull();
		case 3://近くに草がある
			return animal ->animal.search((liv)->liv.getType() == LivingType.Plant) != null;
		case 4://近くに草食がいる
			return animal ->animal.search((liv)->liv.getType() == LivingType.PlantEater) != null;
		case 5://近くに雑食がいる
			return animal ->animal.search((liv)->liv.getType() == LivingType.AnyEater) != null;
		case 6://近くに肉食がいる
			return animal ->animal.search((liv)->liv.getType() == LivingType.FleshEater) != null;
		case 7://近くに草がない
			return animal ->animal.search((liv)->liv.getType() == LivingType.Plant) == null;
		case 8://近くに草食がいない
			return animal ->animal.search((liv)->liv.getType() == LivingType.PlantEater) == null;
		case 9://近くに雑食がいない
			return animal ->animal.search((liv)->liv.getType() == LivingType.AnyEater) == null;
		case 10://近くに肉食がいない
			return animal ->animal.search((liv)->liv.getType() == LivingType.FleshEater) == null;
		case 11://平原にいる
			return animal->animal.getChunk() instanceof Plain;
		case 12://隠れ家にいる
			return animal->animal.getChunk() instanceof Shelter;
		case 13://近くに平原がある

		};
		return liv -> true;
	}

}
