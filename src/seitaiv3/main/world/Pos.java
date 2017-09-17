package seitaiv3.main.world;

public class Pos {
	/**
	 * 座標
	 * @deprecated 直接いじらない
	 * */
	@Deprecated private int x, y;

	public Pos(int x, int y){
		set(x, y);
	}
	/**
	 * @param pos
	 */
	public Pos(Pos pos) {
		this(pos.getX(), pos.getY());
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
		this.x = x;
	}
	/**y座標を設定*/
	public void setY(int y){
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
