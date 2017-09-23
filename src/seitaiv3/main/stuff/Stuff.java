package seitaiv3.main.stuff;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import seitaiv3.main.stuff.living.Sensor;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;
import seitaiv3.main.world.chunk.OFT;

public abstract class Stuff {
	/**座標*/
	protected Pos pos;
	/**速度*/
	protected Vector speed;
	/**加速度*/
	protected Vector accel;

	protected int width;
	protected int height;
	/**このオブジェクトのいる世界*/
	protected World world;
	/**衝突リスト*/
	protected List<Stuff> collidedList;
	/**空間登録用のOFTオブジェクト*/
	private OFT oft;
	/**死亡フラグ*/
	private boolean isdead;
	/**消滅までの時間*/
	private int removeTime = -1;

	/**
	 *
	 */
	public Stuff(Pos p, int width, int height, World world) {
		this.pos = new Pos(p);
		this.width = width;
		this.height = height;
		this.world = world;
		this.speed = new Vector();
		this.accel = new Vector();
		collidedList = new ArrayList<>();
		oft = new OFT(this);

	}

	/**最初の更新処理
	 * 他のオブジェクトに伝えるべきこととかあればここに
	 * 順番的には
	 * preUpdate->setCollided->update->draw->postUpdate
	 * */
	public void preUpdate(){
		//速度による位置の変更
		updateVector();
		pos.adjust(world);
	}

	/**二巡目の更新処理
	 * 基本的にここに処理を書く
	 * */
	public void update(){

	}

	/**三巡目の更新処理
	 * 他の状態を見て判断することとか描画の後にやりたいこととかがあればここに
	 * */
	public void postUpdate(){
		//衝突関係の更新
		updateCollision();
		if(isdead){
			removeTime--;
			if(removeTime <= 0){
				remove();
			}
		}

	}


	/**速度・加速度の変更*/
	protected void updateVector(){
		speed.add(accel);
		pos.add(speed);
	}
	/**衝突関係の更新*/
	protected void updateCollision(){
		collidedList.clear();
		oft.remove();
		world.getTree().register(oft);
	}

	/**描画処理*/
	public abstract void draw(Graphics2D g);

	/**衝突リストに加える*/
	public void setCollided(Stuff collider) {
		if(!(collider instanceof Sensor))collidedList.add(collider);
	}

	/**存在を抹消する
	 * 世界のオブジェクト一覧・衝突判定空間から自分を消す
	 * */
	public void die(DeathCause cause){
		onDie(cause);
		isdead = true;
	}

	public void remove(){
		collidedList.clear();
		oft.remove();
		world = null;
	}

	protected abstract void onDie(DeathCause cause);



	//ここからgetter/setter----------------------------

	public Vector getAccel(){
		return accel;
	}
	public Vector getSpeed(){
		return speed;
	}
	public Pos getPos() {
		return pos;
	}
	public void setPos(Pos p) {
		this.pos = p;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public OFT getOFT() {
		return oft;
	}




}
