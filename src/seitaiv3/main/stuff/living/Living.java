package seitaiv3.main.stuff.living;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import seitaiv3.main.stuff.DeathCause;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

public class Living extends Stuff {
	Sensor sensor;
	List<Stuff> sensoredList;

	public Living(Pos p, int width, int height, World world) {
		super(p, width, height, world);
		speed.set(world.rand.nextInt(5) - 2, world.rand.nextInt(5) - 2);
		sensor = new Sensor(this, 100, world);
		sensoredList = new ArrayList<>();
		world.registerStuff(sensor);
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void postUpdate() {
		super.postUpdate();
		sensoredList.clear();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(!sensoredList.isEmpty() ?  Color.CYAN:Color.RED);
		g.fillRect(pos.getX() - width / 2, pos.getY() - height / 2, width, height);
	}

	/**
	 * センサーに触れた
	 */
	public void sensored(Stuff collider) {
		sensoredList.add(collider);
	}


	@Override
	protected void onDie(DeathCause cause) {
		// TODO 自動生成されたメソッド・スタブ

	}




}
