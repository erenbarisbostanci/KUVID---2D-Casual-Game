package domain.utilities;

public class Recipe {

	/*
	 * A class for recipes, each way to create an atom will be represented by a
	 * unique recipe. eg: a sigma atom can be created by blending 4 alpha atoms or 3
	 * beta atoms. these will be represented uniquely.
	 */

	int goalAtom; // atom to be made
	int ingredientAtomID; // atom that will be used to make the goal atom
	int atomAmount; // amount of ingredient atom required to execute the procces, or the amount of
					// atoms.
	// that will be obtained after the process.

	public Recipe(int ingredient, int goal, int amount) {
		goalAtom = goal;
		ingredientAtomID = ingredient;
		atomAmount = amount;
	}

	public Recipe(int ingredient, int goal) {
		goalAtom = goal;
		ingredientAtomID = ingredient;
	}
}
