package seitaiv3.main.stuff.living.status;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 春太朗
 *
 */
public class Status implements Cloneable {
	/**ステータス*/
	private float hp, hp_default, hp_max, food, food_max, size, speed,
		pgrowth_speed, feed;


	public Status(){

	}

	@Override
	protected Status clone(){
		Status res = new Status();

		res.hp = hp;
		res.hp_default = hp_default;
		res.hp_max = hp_max;
		res.food = food;
		res.food_max = food_max;
		res.size = size;
		res.speed = speed;
		res.pgrowth_speed = pgrowth_speed;
		res.feed = feed;

		return res;
	}


	//get/set-------------------------------------------

	public void setHp(float hp) {
		this.hp = hp;
	}

	public float getHp() {
		return hp;
	}

	public float getHp_max() {
		return hp_max;
	}

	public float getFood() {
		return food;
	}

	public float getFood_max() {
		return food_max;
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

	public void setHp_max(float hp_max) {
		this.hp_max = hp_max;
	}

	public void setFood(float food) {
		this.food = food;
	}

	public void setFood_max(float food_max) {
		this.food_max = food_max;
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

	public float getHp_default() {
		return hp_default;
	}

	public void setHp_default(float hp_default) {
		this.hp_default = hp_default;
	}



}
