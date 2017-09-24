package seitaiv3.main.stuff.living;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 春太朗
 *
 */
public class Status {
	/**ステータス名*/
	public static final
		String HP 	=	"hp",
		HP_MAX 		=	"hp_max",
		FOOD 		=	"food",
		FOOD_MAX 	=	"food_max",
		SIZE		=	"size",
		SPEED		=	"speed",
		PGROWTH_SPEED	=	"plant_growth_speed";
	/**ステータスの名前と中身*/
	private Map<String, Float> status;
	/**ステータスの初期値*/
	private Map<String, Float> origin;

	public Status(){
		status = new HashMap<>();
		origin = new HashMap<>();
	}

	public void setValue(String name, float value){
		if(name == null || name == "")throw new IllegalArgumentException();
		status.put(name, value);
		if(!origin.containsKey(name))origin.put(name, value);
	}

	public float getValue(String name){
		if(!status.containsKey(name))status.put(name, 0f);
		return status.get(name);
	}

	public float getOrigin(String name){
		return origin.get(name);
	}

}
