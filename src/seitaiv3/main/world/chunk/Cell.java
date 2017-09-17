package seitaiv3.main.world.chunk;

public class Cell {
	/**最新のOFTオブジェクト*/
	private OFT latest;
	/**オブジェクト保持数*/
	private int length;

	/**空間にOFT追加*/
	public boolean push(OFT oft){
		//チェック
		if(oft == null || oft.getCell() == this)return false;
		if(latest == null){
			latest = oft;//最初のオブジェクト
		}else{
			oft.setNext(latest);
			latest.setPrev(oft);//oft - latest の順にリンク作成
			latest = oft;
		}
		oft.setCell(this);
		length++;
		return true;
	}

	/**OFT離脱時の処理*/
	public boolean onRemove(OFT oft) {
		if(oft.getCell() != this)return false;
		//最新OFTが離脱するか確認
		if(latest == oft)latest = oft.getNext();
		length--;
		return true;
	}


	//ここからgetter/setter-----------------------------------
	public OFT getLatest() {
		return latest;
	}

	public int getLength() {
		return length;
	}




}
