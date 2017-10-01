package seitaiv3.main.world;

import seitaiv3.main.Main;
import seitaiv3.main.stuff.Vector;

public class Pos {
	/**
	 * 座標
	 * @deprecated 直接いじらない
	 * */
	@Deprecated private float x, y;

	public Pos(float x, float y){
		set(x,y);
	}
	/**
	 * @param pos
	 */
	public Pos(Pos pos) {
		this(pos.getX(), pos.getY());
	}

	/**
     * 距離を取得
     */
    public static float getDistance(Pos p1,Pos p2) {
        // 精度はあまり高めでないが、高速で近似値を計算できる.
    	float ax = p1.getX();
    	float ay = p1.getY();
    	float bx = p2.getX();
    	float by = p2.getY();

        return (float) Math.sqrt(Math.pow(ax-bx, 2) + Math.pow(ay-by, 2));
    }


    @Override
    public String toString() {
    	return getX() + "," + getY();
    }

    /**
     * 世界の大きさからはみ出していれば調整
     */
    public void adjust(World world){
		float padding = 20;

		float borderX = world.getWidth() - padding;
		float borderY = world.getHeight() - padding;

		if(x < padding)setX(padding);
		else if(x > borderX)setX(borderX);
		if(y < padding)setY(padding);
		else if(y > borderY)setY(borderY);
    }

	//get/set---------------------------------

	/**
	 * ベクトルを減算した結果を返します。
	 *
	 * @param v
	 *            減算するベクトル
	 * @return 減算されたベクトル
	 */
	public Vector getSub(Vector v) {
		return new Vector(x - v.x, y - v.y);
	}


	/**座標をまとめて設定*/
	public void set(float x, float y){
		setX(x);
		setY(y);
	}
	public void set(Pos p){
		setX(p.getX());
		setY(p.getY());
	}
	/**座標をまとめて加算*/
	public void add(float x, float y){
		addX(x);
		addY(y);
	}
	public void add(Pos p){
		addX(p.x);
		addY(p.y);
	}
	public void add(Vector v){
		addX((float) v.x);
		addY((float) v.y);
	}
	/**x座標を設定*/
	public void setX(float x){
		this.x = x;
	}
	/**y座標を設定*/
	public void setY(float y){
		this.y = y;
	}
	/**x座標に加算*/
	public void addX(float addX){
		setX(x + addX);
	}
	/**y座標に加算*/
	public void addY(float addY){
		setY(y + addY);
	}
	/**x座標を取得*/
	public float getX(){
		return x;
	}
	/**y座標を取得*/
	public float getY(){
		return y;
	}
}
