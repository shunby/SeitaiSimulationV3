/**
 *
 */
package seitaiv3.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author 春太朗
 *
 */
public class Resources {
	public static BufferedImage plant, planteater, flesheater, anyeater;

	static{
		ClassLoader cl = Resources.class.getClassLoader();
		try {
			plant = ImageIO.read(cl.getResource("res/image/plant.png"));
			planteater = ImageIO.read(cl.getResource("res/image/rabbit.png"));
			flesheater = ImageIO.read(cl.getResource("res/image/wolf.png"));
			anyeater = ImageIO.read(cl.getResource("res/image/raccoon.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 引数の画像の白色部分を指定の色に変換した画像を返す
	 * (元画像は変換しない)
	 */
	public static BufferedImage getChangedImage(BufferedImage img, int r, int g, int b){
		int h = img.getHeight();
		int w = img.getWidth();

		BufferedImage write = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		int newColor = 0xff000000|r<<16|g<<8|b;//引数から色を作成
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				int color = img.getRGB(x, y);//ピクセル読み込み
				if(color == 0xffffffff){//白ならば
					write.setRGB(x, y, newColor);
				}else{
					write.setRGB(x, y, color);
				}
			}
		}
		return write;
	}

}
