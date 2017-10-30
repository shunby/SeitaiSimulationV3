/**
 *
 */
package seitaiv3.main.stuff.living.ai;

import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.animal.Animal;

/**
 * @author 春太朗
 *
 */
public class AI {
	private Behave behave;
	private Condition condition;
	private Animal animal;
	/**
	 *
	 */
	public AI(Animal animal, Condition condition, Behave behave) {
		this.animal = animal;
		this.condition = condition;
		this.behave = behave;
	}

	public boolean update(){
		if(condition.eval(animal)){
			behave.behave(animal);
			return true;
		}else{
			return false;
		}
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
	public Condition getCondition() {
		return condition;
	}

	/**
	 * @return living
	 */
	public Living getLiving() {
		return animal;
	}
}
