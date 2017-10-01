package seitaiv3.main.stuff.living;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seitaiv3.main.Resources;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.Vector;
import seitaiv3.main.stuff.living.animal.Animal;
import seitaiv3.main.stuff.living.status.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

public abstract class Living extends Stuff {
	/**死亡とみなすHPの割合*/
	public static float DEATH_RATE = 0.3f;

	/**ステータス*/
	protected Status status;
	/**進みたい向き*/
	protected Vector moving;
	/**画像*/
	protected BufferedImage img;

	private int width, height;

	public Living(Pos p, World world, Status status) {
		super(p, world);
		this.status = status;
		moving = new Vector();

		height = (int)status.getSize();
		width = (int)status.getSize();
	}

	@Override
	protected void updateVector() {
		super.updateVector();
		if(!isdead)pos.add(moving.getNormalize().mul(status.getSpeed()));

	}


	@Override
	public final void update() {
		super.update();

		status.setEnergy(status.getEnergy()-1);

		if(!isdead && status.getEnergy() <= status.getEnergy_max() * DEATH_RATE){//死亡判定
			die();
		}else if(status.getEnergy() <= 0){//消滅判定
			isremovable = true;
		}
		if(isdead) updateDead();
			else updateAliving();
	}

	/**生存時の更新処理*/
	public abstract void updateAliving();
	/**死亡時の更新処理*/
	public abstract void updateDead();



	@Override
	protected boolean onDie() {
		return false;
	}



	@Override
	public void draw(Graphics2D g) {
		int siz = (int)status.getSize();
		Pos p = world.getWindowPos(pos);
		g.drawImage(img, (int)p.getX() - siz/2, (int)p.getY() - siz/2, siz, siz, null);
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
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public abstract LivingType getType();


	public static enum LivingType{
		Plant, PlantEater, FleshEater, AnyEater
	}


	public boolean isFeed(Living animal) {
		LivingType t = getType();
		switch(animal.getType()){
		case PlantEater:
			if(t == LivingType.Plant)return true;
			break;
		case FleshEater:
			if(t != LivingType.Plant)return true;
			break;
		case AnyEater:
			return true;
		}
		return false;
	}

}
