package seitaiv3.main.stuff.living.status;

import java.util.Random;
/**
 * @author 春太朗
 *
 */
public class Status implements Cloneable {
	/**ステータス*/
	private float energy, energy_max, size, speed, attack,
		pgrowth_speed, feed;
	private int race;


	public Status(){

	}

	@Override
	protected Status clone(){
		Status res = new Status();

		res.energy = energy;
		res.energy_max = energy_max;
		res.size = size;
		res.speed = speed;
		res.pgrowth_speed = pgrowth_speed;
		res.feed = feed;
		res.race = race;
		res.attack = attack;

		return res;
	}


	public static Status makeChildStatus(Status status, Status status2) {
		Status res = new Status();
		Random r = new Random();

		double sudden = 0.0015;

		res.size =
		r.nextFloat() < sudden ?
				(status.size + r.nextFloat() * 1 - 0.5f) :
					(r.nextBoolean() ? status.size : status2.size);

		res.speed =
		r.nextFloat() < sudden ?
				(status.size + r.nextFloat() * 1 - 0.5f) :
					(r.nextBoolean() ? status.speed : status2.speed);

		res.pgrowth_speed =
		r.nextFloat() < sudden ?
				(status.pgrowth_speed + r.nextFloat() * 1 - 0.5f) :
					(r.nextBoolean() ? status.pgrowth_speed : status2.pgrowth_speed);

		res.feed =
		r.nextFloat() < sudden ?
				(status.feed + r.nextFloat()/10f - 0.05f) :
					(r.nextBoolean() ? status.feed : status2.feed);

		res.race =
		r.nextFloat() < sudden ?
				(status.race + r.nextInt(3) - 1) :
					(r.nextBoolean() ? status.race : status2.race);

		res.attack =
		r.nextFloat() < sudden ?
				(status.attack + r.nextFloat() * 1 - 0.5f) :
					(r.nextBoolean() ? status.attack : status2.attack);

		res.energy_max = res.size * 40f;
		res.energy = res.energy_max / 2f;
		status.energy -= res.energy_max / 4f;
		status2.energy -= res.energy_max / 4f;

		return res;
	}



	//get/set-------------------------------------------


	public float getAttack(){
		return attack;
	}

	public void setAttack(float attack){
		this.attack = attack;
	}

	public float getSize() {
		return size;
	}

	public float getSpeed() {
		return speed;
	}

	public float getPgrowth_speed() {
		return pgrowth_speed;
	}

	public float getFeed() {
		return feed;
	}

	public int getRace(){
		return race;
	}

	public void setRace(int race){
		this.race = race;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setPgrowth_speed(float pgrowth_speed) {
		this.pgrowth_speed = pgrowth_speed;
	}

	public void setFeed(float feed) {
		this.feed = feed;
	}

	public float getEnergy() {
		return energy;
	}

	public void setEnergy(float energy) {
		this.energy = energy;
	}

	public float getEnergy_max() {
		return energy_max;
	}

	public void setEnergy_max(float energy_max) {
		this.energy_max = energy_max;
	}

	public float gainEnergy(float gain) {
		if(energy > gain){
			energy -= gain;
			return gain;
		}else{
			float tmp = energy;
			energy = 0;
			return tmp;
		}
	}




}
