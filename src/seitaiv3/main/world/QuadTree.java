package seitaiv3.main.world;

public class QuadTree {

	public static int bitSep32(int n){
		n = (n|(n<<8)) & 0x00ff00ff;
		n = (n|(n<<4)) & 0x0f0f0f0f;
		n = (n|(n<<2)) & 0x33333333;
		return (n|(n<<1)) & 0x55555555;
	}

	public int getMorton(int x, int y){
		return(bitSep32(x) | (bitSep32(y)<<1));
	}
}
