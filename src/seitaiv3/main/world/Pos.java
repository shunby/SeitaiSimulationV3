package seitaiv3.main.world;

import seitaiv3.main.Main;

public class Pos {
	/**
	 * 座標
	 * @deprecated 直接いじらない
	 * */
	@Deprecated private int x, y;

	/**
	 * このposがある世界
	 */
	private World world;

	public Pos(World world,int x, int y){
		this.world = world;
		set(x, y);
	}

	public Pos(int x, int y){
		this(Main.get().getMainThread().getWorld(), x, y);
	}
	/**
	 * @param pos
	 */
	public Pos(Pos pos) {
		this(pos.getX(), pos.getY());
	}

	/**
     * 距離を取得
     * http://d.hatena.ne.jp/maachang/20140707/1404713912
     */
    public static int getDistance(Pos p1,Pos p2) {
        // 精度はあまり高めでないが、高速で近似値を計算できる.
    	int ax = p1.getX();
    	int ay = p1.getY();
    	int bx = p2.getX();
    	int by = p2.getY();

        int dx,dy ;
        if ( ( dx = ( ax > bx ) ? ax - bx : bx - ax ) < ( dy = ( ay > by ) ? ay - by : by - ay ) ) {
            return ((( dy << 8 ) + ( dy << 3 ) - ( dy << 4 ) - ( dy << 1 ) +
                    ( dx << 7 ) - ( dx << 5 ) + ( dx << 3 ) - ( dx << 1 )) >> 8 );
        }
        else {
            return ((( dx << 8 ) + ( dx << 3 ) - ( dx << 4 ) - ( dx << 1 ) +
                    ( dy << 7 ) - ( dy << 5 ) + ( dy << 3 ) - ( dy << 1 )) >> 8 );
        }
    }


    @Override
    public String toString() {
    	return getX() + "," + getY();
    }

	//get/set---------------------------------
    /**幅と奥行きを考慮して調整*/
    public void adjust(int width, int height){
    	int a = 10;
    	if(x > world.getWidth() - width / 2 - a)x = 0 + width/2 + a;
		if(x < 0 + width/2 + a)x = world.getWidth() - width / 2 - a;
		if(y > world.getHeight() - height/2 - a)y = 0+height/2 + a;
		if(y < 0+height/2 + a)y = world.getHeight() - height/2 - a;
    }

	/**座標をまとめて設定*/
	public void set(int x, int y){
		setX(x);
		setY(y);
	}
	public void set(Pos p){
		setX(p.getX());
		setY(p.getY());
	}
	/**座標をまとめて加算*/
	public void add(int x, int y){
		addX(x);
		addY(y);
	}
	public void add(Pos p){
		addX(p.x);
		addY(p.y);
	}
	/**x座標を設定*/
	public void setX(int x){
		if(x > world.getWidth())x = 0;
		if(x < 0)x = world.getWidth();
		this.x = x;
	}
	/**y座標を設定*/
	public void setY(int y){
		if(y > world.getHeight())y = 0;
		if(y < 0)y = world.getHeight();
		this.y = y;
	}
	/**x座標に加算*/
	public void addX(int addX){
		setX(x + addX);
	}
	/**y座標に加算*/
	public void addY(int addY){
		setY(y + addY);
	}
	/**x座標を取得*/
	public int getX(){
		return x;
	}
	/**y座標を取得*/
	public int getY(){
		return y;
	}
}
