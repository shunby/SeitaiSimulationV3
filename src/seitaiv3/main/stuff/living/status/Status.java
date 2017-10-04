package seitaiv3.main.stuff.living.status;

/**
 * @author 春太朗
 *
 */
public class Status implements Cloneable {
	/**ステータス*/
	private float energy, energy_max, size, speed,
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

		return res;
	}


	//get/set-------------------------------------------


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




}
