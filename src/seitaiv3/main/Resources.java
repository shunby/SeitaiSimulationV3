/**
 *
 */
package seitaiv3.main;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author 春太朗
 *
 */
public class Resources {
	public static Image plant;

	static{
		ClassLoader cl = Resources.class.getClassLoader();
		try {
			plant = ImageIO.read(cl.getResource("res/image/plant.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
