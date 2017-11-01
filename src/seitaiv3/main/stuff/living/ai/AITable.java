/**
 *
 */
package seitaiv3.main.stuff.living.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import seitaiv3.main.stuff.living.Living;
import seitaiv3.main.stuff.living.animal.Animal;

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

	public void update(Animal animal){
		for(AI e: ai){
			if(e.update(animal))break;
		}
	}

	public static AITable makeChild(Random rand, Animal animal, AITable ai1, AITable ai2){
		AITable result = animal.getAitable();
		List<AI> childAI = result.ai;
		int length = ai1.ai.size() > ai2.ai.size() ? ai1.ai.size() : ai2.ai.size();
		for(int i = 0;i < length;i++){
			AI ac;//子に受け継がれるAI

			if(ai1.ai.size() <= i)ac = ai2.ai.get(i);
			else if(ai2.ai.size() <= i)ac = ai1.ai.get(i);
			else ac = rand.nextBoolean() ? ai1.ai.get(i) : ai2.ai.get(i);
			childAI.add(ac);
		}

		if(childAI.size() != 0 &&rand.nextDouble() < 0.07){//突然変異1
			int selected = rand.nextInt(childAI.size());
			childAI.set(selected, AI.getRandomAI(rand));
		}
		if(childAI.size() != 0 &&rand.nextDouble() < 0.04){//突然変異2
			childAI.add(AI.getRandomAI(rand));
		}


		return result;

	}

	public static AITable getRandomTable(Animal animal, Random rand){
		AITable table = new AITable(animal);
		for(int i = 0;i < rand.nextInt(10);i++){
			table.ai.add(AI.getRandomAI(rand));
		}
		return table;
	}

	/**
	 * @return ai
	 */
	public List<AI> getAi() {
		return ai;
	}



}
