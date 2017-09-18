package seitaiv3.main.world.chunk;

import java.util.ArrayList;
import java.util.List;
import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.Pos;
import seitaiv3.main.world.World;


public class Liner4Tree {

	/**最大空間分割レベル*/
	public static int MAX_LEVEL = 8;
	/**この木の空間分割レベル*/
	private int level;
	/**四分木配列*/
	private Cell[] liner4tree;
	/**空間数*/
	private int[] areaNum;
	/**この空間木のある世界*/
	private World world;
	/**最小区分の小空間の大きさ*/
	private int unitW, unitH;
	/**衝突スタック*/
	List<Stuff> collisionStack;

	public Liner4Tree(int level, World world){
		if(level > MAX_LEVEL+1)throw new IllegalArgumentException();
		this.level = level;
		this.world = world;
		areaNum = new int[MAX_LEVEL+1];
		collisionStack = new ArrayList<>();


		//ルート階層の空間数は１
		areaNum[0] = 1;
		for(int i = 1; i < MAX_LEVEL+1; i++){
			areaNum[i] = areaNum[i-1] * 4;
		}
		int cells = (areaNum[level + 1]-1)/3;
		liner4tree = new Cell[cells];

		unitW = (int) (world.getWidth() / (1 << level));
		unitH = (int) (world.getHeight() / (1 << level));

	}

	public boolean register(OFT oft){
		//線形モートン番号
		int elementNumber = getLinerMortonNumber(oft.getObject());
		if(elementNumber < liner4tree.length){
			if(liner4tree[elementNumber] == null)createNewCell(elementNumber);
			return liner4tree[elementNumber].push(oft);
		}
		return false;

	}

	/**新しい小空間を親空間ごと生成*/
	private void createNewCell(int elementNumber) {
		while(liner4tree[elementNumber] == null){
			liner4tree[elementNumber] = new Cell();
			if(elementNumber == 0)break;
			//親空間の空間番号
			elementNumber = (elementNumber - 1) >> 2;
			if(elementNumber >= liner4tree.length)break;
		}

	}


	/**衝突判定リストを作成
	 * @return リストの長さ
	 * */
	public int collisionCheck(List<Stuff> vector){
		vector.clear();
		if(liner4tree[0] == null)return 0;
		collisionStack.clear();
		getCollisionList(0, vector, collisionStack);
		return vector.size();
	}


	/**
	 *
	 */
	private boolean getCollisionList(int elem, List<Stuff> vector,
			List<Stuff> collisionStack) {
		//空間内のオブジェクト同士で判定
		OFT oft1 = liner4tree[elem].getLatest();
		while(oft1 != null){
			OFT oft2 = oft1.getNext();
			while(oft2 != null){
				//衝突リスト作成
				vector.add(oft1.getObject());
				vector.add(oft2.getObject());
				oft2 = oft2.getNext();
			}
			//衝突スタックとの衝突リスト作成
			Stuff object = oft1.getObject();
			collisionStack.forEach((oft)->{
				vector.add(object);
				vector.add(oft);
			});
			oft1 = oft1.getNext();
		}

		boolean childFlag = false;
		//子空間に移動
		int objNum = 0;
		int nextElem;
		for(int i = 0; i < 4; i++){
			nextElem = elem * 4 + 1 + i;
			if(nextElem < liner4tree.length && liner4tree[nextElem] != null){
				if(!childFlag){
					//登録オブジェクトをスタックに追加
					oft1 = liner4tree[elem].getLatest();
					while(oft1 != null){
						collisionStack.add(oft1.getObject());
						objNum++;
						oft1 = oft1.getNext();
					}
				}
				childFlag = true;
				getCollisionList(nextElem, vector, collisionStack);//子空間へ
			}
		}

		//スタックからオブジェクトを外す
		if(childFlag){
			for(int i = 0; i < objNum; i++){
				collisionStack.remove(collisionStack.size() - 1);
			}
		}
		return true;

	}

	/**矩形のモートン番号(線形)*/
	public int getLinerMortonNumber(Stuff s){
		//左上のモートン番号
		int topleft = getPointToElemNum(s.getPos().getX() - s.getWidth() / 2, s.getPos().getY() - s.getHeight() / 2);
		//右下のモートン番号
		int bottomright = getPointToElemNum(s.getPos().getX() + s.getWidth() / 2, s.getPos().getY() + s.getHeight() / 2);

		//右上・左下の排他的論理和
		int xor = topleft ^ bottomright;

		int hi_level = 0;

		for(int i = 0; i < level; i++){
			int check = (xor >> (i*2)) & 0x3;//i番目の区切りが11かどうか
			if(check != 0){
				hi_level = i + 1;
			}
		}
		int area_level = level - hi_level;

		int area_num = bottomright >> (hi_level * 2);
		//線形四分木の番号 = 等比級数の和 + 空間番号
		int liner_area_num = (int) ((areaNum[area_level] - 1)/3) + area_num;
		if(area_num > liner4tree.length)return 0xffffffff;
		return liner_area_num;
	}

	/**ビット分割関数*/
	public static int bitSeparate32(int n){
		n = (n|(n<<8)) & 0x00ff00ff;
		n = (n|(n<<4)) & 0x0f0f0f0f;
		n = (n|(n<<2)) & 0x33333333;
		return (n|(n<<1)) & 0x55555555;
	}

	/**2次元モートン番号*/
	public static int get2DMortonNumber(int x, int y){
		return (bitSeparate32(x) | (bitSeparate32(y)<<1));
	}
	/**2次元モートン番号*/
	public static int get2DMortonNumber(Pos p){
		return get2DMortonNumber(p.getX(), p.getY());
	}

	/**座標から線形四分木要素番号*/
	public int getPointToElemNum(int x, int y){
		return get2DMortonNumber(x/unitW, y/unitH);
	}
	/**座標から線形四分木要素番号*/
	public int getPointToElemNum(Pos p){
		return getPointToElemNum(p.getX(), p.getY());
	}
}









