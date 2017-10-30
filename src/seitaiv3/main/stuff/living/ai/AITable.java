/**
 *
 */
package seitaiv3.main.stuff.living.ai;

import java.util.ArrayList;
import java.util.List;

import seitaiv3.main.stuff.living.Living;

/**
 *
 */
public class AITable {
	/**このAITableで管理するLiving*/
	private Living living;
	/**AI*/
	private List<AI> ai;

	public AITable(Living l){
		this.living = l;
		this.ai = new ArrayList<>(10);
	}

	public void update(){
		for(AI e: ai){
			if(e.update())break;
		}
	}



}
