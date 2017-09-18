package seitaiv3.main.stuff.living;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

public class Living extends Stuff {
	int ax, ay;


	public Living(Pos p, int width, int height, World world) {
		super(p, width, height, world);
		ax = world.rand.nextInt(5) - 2;
		ay = world.rand.nextInt(5) - 2;
	}

	@Override
	protected void onUpdate() {
		pos.add(ax, ay);
	}

	@Override
	protected void draw(GraphicsContext g) {
		g.setFill(Color.RED);
		if(!collidedList.isEmpty())g.setFill(Color.CYAN);
		g.fillOval(pos.getX() - width / 2, pos.getY() - height / 2, width, height);

	}



}
