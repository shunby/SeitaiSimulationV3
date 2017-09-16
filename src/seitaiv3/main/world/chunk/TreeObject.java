package seitaiv3.main.world.chunk;

import seitaiv3.main.stuff.Stuff;
import seitaiv3.main.world.Chunk;

/**
 * http://marupeke296.com/COL_2D_No8_QuadTree.html
 * 四分木探索で登録するオブジェクト
 */
public class TreeObject {
	/**このインスタンスの保持するオブジェクトが所属する空間*/
	private Chunk chunk;
	/**このインスタンスが保持しているオブジェクト*/
	private Stuff object;
}
