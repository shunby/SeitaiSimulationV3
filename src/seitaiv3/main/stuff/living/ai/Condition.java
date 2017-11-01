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

	public static final Condition is_hungry = animal -> !animal.isFull();
	public static final Condition is_full = animal -> animal.isFull();
	public static final Condition exists_plant = animal ->animal.search((liv)->liv.getType() == LivingType.Plant) != null;
	public static final Condition exists_planteater = animal ->animal.search((liv)->liv.getType() == LivingType.PlantEater && !(liv.getChunk() instanceof Shelter)) != null;
	public static final Condition exists_anyeater = animal ->animal.search((liv)->liv.getType() == LivingType.AnyEater && !(liv.getChunk() instanceof Shelter)) != null;
	public static final Condition exists_flesheater = animal ->animal.search((liv)->liv.getType() == LivingType.FleshEater && !(liv.getChunk() instanceof Shelter)) != null;
	public static final Condition no_plant = animal ->animal.search((liv)->liv.getType() == LivingType.Plant) == null;
	public static final Condition no_planteater = animal ->animal.search((liv)->liv.getType() == LivingType.PlantEater && !(liv.getChunk() instanceof Shelter)) == null;
	public static final Condition no_anyeater = animal ->animal.search((liv)->liv.getType() == LivingType.AnyEater && !(liv.getChunk() instanceof Shelter)) == null;
	public static final Condition no_flesheater = animal ->animal.search((liv)->liv.getType() == LivingType.FleshEater && !(liv.getChunk() instanceof Shelter)) == null;
	public static final Condition exists_feed = (animal)->{
		return animal.search((l)->{
			if(l.getType() != LivingType.Plant)
				return animal.isFeed(l) && !(l.getChunk() instanceof Shelter);
			return animal.isFeed(l);
		}) != null;
	};
	public static final Condition no_feed = (animal)->{
		return animal.search((l)->{
			if(l.getType() != LivingType.Plant)
				return animal.isFeed(l) && !(l.getChunk() instanceof Shelter);
			return animal.isFeed(l);
		}) == null;
	};
	public static final Condition exists_love = (animal)->{
		return animal.search((liv)->{
			return !liv.isDead() && animal.isLove(liv);
		}) != null;
	};
	public static final Condition no_love = (animal)->{
		return animal.search((liv)->{
			return !liv.isDead() && animal.isLove(liv);
		}) == null;
	};
	public static final Condition in_plain = animal->animal.getChunk() instanceof Plain;
	public static final Condition in_shelter = animal->animal.getChunk() instanceof Shelter;
	public static final Condition near_plain = animal->animal.searchChunk((chunk)->chunk instanceof Plain) != null;
	public static final Condition near_shelter = animal->animal.searchChunk((chunk)->chunk instanceof Shelter) != null;
	public static final Condition no_plain = animal->animal.searchChunk((chunk)->chunk instanceof Plain) == null;
	public static final Condition no_shelter = animal->animal.searchChunk((chunk)->chunk instanceof Shelter) == null;
	public static final Condition no_condition = animal->true;

	public static Condition getRandomCondition(Random rand){

		switch(rand.nextInt(21)){
		case 1://空腹
			return is_hungry;
		case 2://満腹
			return is_full;
		case 3://近くに草がある
			return exists_plant;
		case 4://近くに草食がいる
			return exists_planteater;
		case 5://近くに雑食がいる
			return exists_anyeater;
		case 6://近くに肉食がいる
			return exists_flesheater;
		case 7://近くに草がない
			return no_plant;
		case 8://近くに草食がいない
			return no_planteater;
		case 9://近くに雑食がいない
			return no_anyeater;
		case 10://近くに肉食がいない
			return no_flesheater;
		case 17://近くに餌がある
			return exists_feed;
		case 18://近くに餌がない
			return no_feed;
		case 19://近くに繁殖可能な相手がいる
			return exists_love;
		case 20://近くに繁殖可能な相手がいない
			return no_love;
		case 11://平原にいる
			return in_plain;
		case 12://隠れ家にいる
			return in_shelter;
		case 13://近くに平原がある
			return near_plain;
		case 14://近くに隠れ家がある
			return near_shelter;
		case 15://近くに平原がない
			return no_plain;
		case 16://近くに隠れ家がない
			return no_shelter;
		case 0://無条件で
			return no_condition;

		};
		return null;
	}

	public static String getConditionName(Condition c){
		if(c == exists_anyeater)return "exists_anyeater";
		else if(c == exists_feed)return "exists_feed";
		else if(c == exists_flesheater)return "exists_flesheater";
		else if(c == exists_love)return "exists_love";
		else if(c == exists_plant)return "exists_plant";
		else if(c == exists_planteater)return "exists_planteater";
		else if(c == in_plain)return "in_plain";
		else if(c == in_shelter)return "in_shelter";
		else if(c == is_full)return "is_full";
		else if(c == is_hungry)return "is_hungry";
		else if(c == near_plain)return "near_plain";
		else if(c == near_shelter)return "near_shelter";
		else if(c == no_anyeater)return "no_anyeater";
		else if(c == no_condition)return "no_condition";
		else if(c == no_feed)return "no_feed";
		else if(c == no_flesheater)return "no_flesheater";
		else if(c == no_love)return "no_love";
		else if(c == no_plain)return "no_plain";
		else if(c == no_plant)return "no_plant";
		else if(c == no_planteater)return "no_planteater";
		else if(c == no_shelter)return "no_shelter";
		return null;
	}


}
