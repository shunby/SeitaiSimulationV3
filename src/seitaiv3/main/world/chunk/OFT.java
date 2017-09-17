package seitaiv3.main.world.chunk;

import seitaiv3.main.stuff.Stuff;

public class OFT {

	/**登録されている小空間*/
	private Cell cell;
	/**登録するオブジェクト*/
	private Stuff object;
	/**同じ空間に登録されている次のオブジェクト*/
	private OFT next;
	/**同じ空間に登録されている前のオブジェクト*/
	private OFT prev;

	public OFT(Stuff obj) {
		this.object = obj;
	}

	/**自ら空間を離れる*/
	public boolean remove(){
		//既に離れている
		if(cell == null)return false;
		//空間に離脱を通知
		if(!cell.onRemove(this))return false;

		if(prev != null)prev.next = next;
		if(next != null)next.prev = prev;

		cell = null;
		return true;
	}






	//ここからgetter/setter---------------------------
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	public Stuff getObject() {
		return object;
	}
	public void setObject(Stuff object) {
		this.object = object;
	}
	public OFT getNext() {
		return next;
	}
	public void setNext(OFT next) {
		this.next = next;
	}
	public OFT getPrev() {
		return prev;
	}
	public void setPrev(OFT prev) {
		this.prev = prev;
	}



}
