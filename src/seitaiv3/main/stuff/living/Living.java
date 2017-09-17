package seitaiv3.main.stuff.living;

import java.awt.Color;
import java.awt.Graphics;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

public class Living extends Stuff {


	public Living(Pos p, int width, int height, World world) {
		super(p, width, height, world);
	}

	@Override
	protected void onUpdate() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(pos.getX() - width / 2, pos.getY() - height / 2, width, height);

	}



}
