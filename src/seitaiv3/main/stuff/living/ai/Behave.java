/**
 *
 */
package seitaiv3.main.stuff.living.ai;

import java.util.Random;

import seitaiv3.main.stuff.Vector;
import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.Living.LivingType;
import seitaiv3.main.stuff.living.animal.Animal;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;
import seitaiv3.main.world.chunk.Chunk;
import seitaiv3.main.world.chunk.Plain;
import seitaiv3.main.world.chunk.Shelter;

/**
 *
 */
@FunctionalInterface
public interface Behave {
	public boolean behave(Animal liv);

	public static final Behave go_plant =(animal)->{
		Living l = animal.search((liv)->liv.getType() == LivingType.Plant);
		if(l != null){
			Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(v.x, v.y);
			return true;
		}else return false;
	};

	public static final Behave go_planteater = (animal)->{
		Living l = animal.search((liv)->liv.getType() == LivingType.PlantEater && !(animal.getChunk() instanceof Shelter));
		if(l != null){
			Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(v.x, v.y);
			return true;
		}else return false;
	};

	public static final Behave go_anyeater = (animal)->{
		Living l = animal.search((liv)->liv.getType() == LivingType.AnyEater && !(animal.getChunk() instanceof Shelter));
		if(l != null){
			Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(v.x, v.y);
			return true;
		}else return false;
	};

	public static final Behave go_flesheater = (animal)->{
		Living l = animal.search((liv)->liv.getType() == LivingType.FleshEater && !(animal.getChunk() instanceof Shelter));
		if(l != null){
			Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(v.x, v.y);
			return true;
		}else return false;
	};

	public static final Behave avoid_plant = (animal)->{
		Living l = animal.search((liv)->liv.getType() == LivingType.Plant);
		if(l != null){
			Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(-v.x, -v.y);
			return true;
		}else return false;
	};

	public static final Behave avoid_planteater = (animal)->{
		Living l = animal.search((liv)->liv.getType() == LivingType.PlantEater && !(animal.getChunk() instanceof Shelter));
		if(l != null){
			Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(-v.x, -v.y);
			return true;
		}else return false;
	};

	public static Behave avoid_anyeater = (animal)->{
		Living l = animal.search((liv)->liv.getType() == LivingType.AnyEater && !(animal.getChunk() instanceof Shelter));
		if(l != null){
			Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(-v.x, -v.y);
			return true;
		}else return false;
	};

	public static Behave avoid_flesheater = (animal)->{
		Living l = animal.search((liv)->liv.getType() == LivingType.FleshEater && !(animal.getChunk() instanceof Shelter));
		if(l != null){
			Vector v = l.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(-v.x, -v.y);
			return true;
		}else return false;
	};

	public static Behave go_feed = (animal)->{
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

	public static final Behave avoid_feed =
	(animal)->{
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
	public static Behave go_love =
	(animal)->{
		Living living =animal.search((liv)-> !liv.isDead() && animal.isLove(liv));
		if(living != null){
			Vector v = living.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(v.x, v.y);
			return true;
		}else return false;
	};

	public static Behave avoid_love =
			(animal)->{
				Living living =animal.search((liv)-> !liv.isDead() && animal.isLove(liv));
				if(living != null){
					Vector v = living.getPos().getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
					animal.getMoving().set(-v.x, -v.y);
					return true;
				}else return false;
			};

	public static final Behave go_plain = (animal)->{
		Chunk chunk = animal.searchChunk((c)->c instanceof Plain);
		int l = animal.getWorld().getChunkLength();
		if(chunk != null){
			Pos p = new Pos((chunk.x + 0.5f)*l, (chunk.y + 0.5f) * l);
			Vector v = p.getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(v.x, v.y);
			return true;
		}else return false;
	};

	public static Behave avoid_plain = (animal)->{
		Chunk chunk = animal.searchChunk((c)->c instanceof Plain);
		int l = animal.getWorld().getChunkLength();
		if(chunk != null){
			Pos p = new Pos((chunk.x + 0.5f)*l, (chunk.y + 0.5f) * l);
			Vector v = p.getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(-v.x, -v.y);
			return true;
		}else return false;
	};

	public static final Behave go_shelter = (animal)->{
		Chunk chunk = animal.searchChunk((c)->c instanceof Shelter);
		int l = animal.getWorld().getChunkLength();
		if(chunk != null){
			Pos p = new Pos((chunk.x + 0.5f)*l, (chunk.y + 0.5f) * l);
			Vector v = p.getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(v.x, v.y);
			return true;
		}else return false;
	};

	public static final Behave avoid_shelter = (animal)->{
		Chunk chunk = animal.searchChunk((c)->c instanceof Shelter);
		int l = animal.getWorld().getChunkLength();
		if(chunk != null){
			Pos p = new Pos((chunk.x + 0.5f)*l, (chunk.y + 0.5f) * l);
			Vector v = p.getSub(new Vector(animal.getPos().getX(), animal.getPos().getY()));
			animal.getMoving().set(-v.x, -v.y);
			return true;
		}else return false;
	};

	public static final Behave move_random = (animal)->{
		Random rand = animal.getWorld().rand;
		if(rand.nextInt(50)==0)animal.getMoving().set(rand.nextInt(5) - 2, rand.nextInt(5) - 2);
		return true;
	};

	public static final Behave do_nothing = (animal)->{
		return true;
	};


	public static Behave getRandomBehave(Random rand){
		switch(rand.nextInt(18)){
		case 0://近くの草に寄る
			return go_plant;
		case 1://近くの草食による
			return go_planteater;
		case 2://近くの雑食による
			return go_anyeater;
		case 3://近くの肉食による
			return go_flesheater;
		case 4://近くの草から離れる
			return avoid_plant;
		case 5://近くの草食から離れる
			return avoid_planteater;
		case 6://近くの雑食から離れる
			return avoid_anyeater;
		case 7://近くの肉食から離れる
			return avoid_flesheater;
		case 8://近くの餌による
			return go_feed;
		case 9://近くの餌から離れる
			return avoid_feed;
		case 10://近くの平原による
			return go_plain;
		case 11://近くの平原から離れる
			return avoid_plain;
		case 12://近くの隠れ家による
			return go_shelter;
		case 13://近くの隠れ家から離れる
			return avoid_shelter;
		case 14://適当に動く
			return move_random;
		case 15://何もしない
			return do_nothing;
		case 16://近くの繁殖相手に近づく
			return go_love;
		case 17://近くの繁殖相手から離れる
			return avoid_love;
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

	public static String getBehaveName(Behave b){
		if(b == avoid_anyeater)return "avoid_anyeater";
		else if(b == avoid_feed)return "avoid_feed";
		else if(b == avoid_flesheater)return "avoid_flesheater";
		else if(b == avoid_plain)return "avoid_plain";
		else if(b == avoid_plant)return "avoid_plant";
		else if(b == avoid_planteater)return "avoid_planteater";
		else if(b == avoid_shelter)return "avoid_shelter";
		else if(b == do_nothing)return "do_nothing";
		else if(b == go_anyeater)return "go_anyeater";
		else if(b == go_feed)return "go_feed";
		else if(b == go_flesheater)return "go_flesheater";
		else if(b == go_plain)return "go_plain";
		else if(b == go_plant)return "go_plant";
		else if(b == go_planteater)return "go_planteater";
		else if(b == go_shelter)return "go_shelter";
		else if(b == move_random)return "move_random";
		else if(b == go_love)return "go_love";
		else if(b == avoid_love)return "avoid_love";
		return null;
	}

}
