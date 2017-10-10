package seitaiv3.main.stuff.living;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.stuff.Vector;
import seitaiv3.main.stuff.living.status.Status;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;

public abstract class Living extends Stuff {
	/**死亡とみなすHPの割合*/
	public static float DEATH_RATE = 0.1f;

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
		}else if(isdead){//消滅判定
			chunk.gainEnergy(-1);
			if(status.getEnergy() <= 0)isremovable = true;
		}
		if(isdead) {
			updateDead();
		}else {
			updateAliving();
		}
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

		if(isdead){
			AffineTransform beforeAffin = g.getTransform();
			AffineTransform affin = new AffineTransform();
			affin.translate(p.getX() - siz/2, p.getY() - siz/2 );
			affin.scale( 1.0, 1.0 );
			affin.rotate( Math.toRadians(180), siz / 2, siz / 2 );
			g.transform( affin );

			g.drawImage( img, 0, 0, siz, siz, null );
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(0, 0, siz, siz);
			g.setTransform( beforeAffin );
		}else{
			g.drawImage(img, (int)p.getX() - siz/2, (int)p.getY() - siz/2, siz, siz, null);

		}
		g.setColor(Color.yellow);
		g.drawString(String.format("%.1f", status.getEnergy()), (int)p.getX() - siz/2, (int)p.getY() - siz/2);
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
		LivingType t1 = animal.getType();
		if(getRacialDistance((Living)animal) < 10000)return false;
		switch(t){
		case PlantEater:
			if(t1 == LivingType.Plant)return true;
			break;
		case FleshEater:
			if(t1 != LivingType.Plant)return true;
			break;
		case AnyEater:
			return true;
		}
		return false;
	}

	/**血の隔たり*/
	public int getRacialDistance(Living l){
		return Math.abs(l.getStatus().getRace() - getStatus().getRace());
	}

	public boolean isLove(Living animal) {
		if(animal.getType() == getType() && getRacialDistance(animal) > 10000)return true;
		return false;
	}

}
