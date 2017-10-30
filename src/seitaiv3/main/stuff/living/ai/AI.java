/**
 *
 */
package seitaiv3.main.stuff.living.ai;

import java.util.ArrayList;
import java.util.List;

import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.animal.Animal;

/**
 * @author 春太朗
 *
 */
public class AI {
	private Behave behave;
	private List<Condition> conditions;
	private Animal animal;
	/**
	 *
	 */
	public AI(Animal animal, Behave behave) {
		this.animal = animal;
		this.conditions = new ArrayList<>(5);
		this.behave = behave;
	}

	public boolean update(){
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

	/**
	 * @return living
	 */
	public Living getLiving() {
		return animal;
	}
}
