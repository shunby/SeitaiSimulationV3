package seitaiv3.main.stuff.living;

import java.util.ArrayList;
import java.util.List;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.Vector;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

public abstract class Living extends Stuff {
	/**死亡とみなすHPの割合*/
	public static float DEATH_RATE = 0.3f;
	/**触覚*/
	protected Sensor sensor;
	/**触覚に触れたもの(フレームごとに更新)*/
	protected List<Stuff> sensoredList;
	/**ステータス*/
	protected Status status;
	/**進みたい向き*/
	protected Vector moving;

	public Living(Pos p, World world, Status status) {
		super(p, world);

		sensor = new Sensor(this, 100, world);
		sensoredList = new ArrayList<>();
		this.status = status;
		moving = new Vector();
		world.registerStuff(sensor);
	}

	@Override
	protected void updateVector() {
		super.updateVector();
		pos.add(moving.getNormalize().mul(status.getValue(Status.SPEED)));

	}


	@Override
	public void update() {
		super.update();

		if(status.getValue(Status.FOOD) > 0){
			status.setValue(Status.FOOD, status.getValue(Status.FOOD) - 1);
		}else{
			status.setValue(Status.HP, status.getValue(Status.HP) - 1);
		}

		if(!isdead && status.getValue(Status.HP) <= status.getValue(Status.HP_MAX) * DEATH_RATE){//死亡判定
			die();
		}else if(status.getValue(Status.HP) <= 0){//消滅判定
			isremovable = true;
		}
	}

	@Override
	public void postUpdate() {
		super.postUpdate();
		sensoredList.clear();
	}

	/**
	 * センサーに触れた
	 */
	public void sensored(Stuff collider) {
		sensoredList.add(collider);
	}

	@Override
	protected boolean onDie() {
		return false;
	}


	@Override
	protected void onRemoved() {
		sensor.die();
	}

	//get/set-----------------------------------

	public Vector getMoving(){
		return moving;
	}

	public Status getStatus(){
		return status;
	}

	@Override
	public int getWidth() {
		return (int)status.getValue(Status.SIZE);
	}

	@Override
	public int getHeight() {
		return (int)status.getValue(Status.SIZE);
	}




}
