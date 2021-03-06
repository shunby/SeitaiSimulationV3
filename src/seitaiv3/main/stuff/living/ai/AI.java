/**
 *
 */
package seitaiv3.main.stuff.living.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import seitaiv3.main.stuff.living.animal.Animal;

/**
 * @author 春太朗
 *
 */
public class AI {
	private Behave behave;
	private List<Condition> conditions;
	/**
	 *
	 */
	public AI(Behave behave) {
		this.conditions = new ArrayList<>(5);
		this.behave = behave;
	}

	public boolean update(Animal animal){
		boolean eval = true;
		for(Condition c:conditions){
			if(!c.eval(animal)){
				eval = false;
				break;
			}
		}//conditionsの条件がすべて成立すれば真
		if(eval){
			return behave.behave(animal);
		}else{
			return false;
		}
	}

	public static AI getRandomAI(Random rand){
		AI ai = new AI(Behave.getRandomBehave(rand));
		List<Condition> conditions = ai.conditions;
		for(int i = 0; i < rand.nextInt(5); i++){
			conditions.add(Condition.getRandomCondition(rand));
		}
		return ai;
	}


	/**
	 * @return behave
	 */
	public Behave getBehave() {
		return behave;
	}

	/**
	 * @return condition
	 */
	public List<Condition> getConditions() {
		return conditions;
	}

}
