package domain.utilities;

import java.util.ArrayList;

import domain.Inventory;

public class Blender {
	// These will contain all the recipes.
	private static Blender blender;
	
	public ArrayList<Recipe> blendRecipeBook = new ArrayList<Recipe>();
	public ArrayList<Recipe> breakRecipeBook = new ArrayList<Recipe>();
	public static int atomNumber = 0;
	// naming scheme for the recipes: first word is atom used, second one is the
	// atom received.
	// I defined them separately for convenience, just in case if we need to debug
	// etc. It would be
	// more straight forward to add them to the ArrayList directly.
	Recipe alphaBeta = new Recipe(0, 1, 2);
	Recipe alphaGama = new Recipe(0, 2, 3);
	Recipe alphaSigma = new Recipe(0, 3, 4);
	Recipe betaGama = new Recipe(1, 2, 2);
	Recipe betaSigma = new Recipe(1, 3, 3);
	Recipe gamaSigma = new Recipe(2, 3, 2);
	// Break recipes are defined separately, this can be improved
	// in the future, i.e. a single recipe can be used to define both the break and
	// blend recipes.
	// Defining them separately makes the code more flexible, for example we can
	// change the ratios between
	// breaking and blending the atoms to change the gameplay mechanics.
	Recipe betaAlpha = new Recipe(1, 0, 2);
	Recipe gamaAlpha = new Recipe(2, 0, 3);
	Recipe sigmaAlpha = new Recipe(3, 0, 4);
	Recipe gamaBeta = new Recipe(2, 1, 2);
	Recipe sigmaBeta = new Recipe(3, 1, 3);
	Recipe sigmaGama = new Recipe(3, 2, 2);

	public Blender() {
		blendRecipeBook.add(alphaBeta);
		blendRecipeBook.add(alphaGama);
		blendRecipeBook.add(alphaSigma);
		blendRecipeBook.add(betaGama);
		blendRecipeBook.add(betaSigma);
		blendRecipeBook.add(gamaSigma);

		breakRecipeBook.add(betaAlpha);
		breakRecipeBook.add(gamaAlpha);
		breakRecipeBook.add(sigmaAlpha);
		breakRecipeBook.add(gamaBeta);
		breakRecipeBook.add(sigmaBeta);
		breakRecipeBook.add(sigmaGama);
	}
	
	public synchronized static Blender getBlender(){
		if(blender == null) {
			blender = new Blender();
		}
		return blender;
	}

	// This returns an int for debbuging purposes, might remove it in the final
	// version
	// REQUIRES: Integers 0,1,2 or 3 as both arguments for consumed and goat atom
	// IDs, there is at least one atom if we are breaking and atleast 2 atoms of
	// consumed
	// atoms exist in the inventory
	// MODIFIES: Inventory's atom counts are changed, if we are blending, goalAtom
	// is increased and consumedAtom is decreased and if we are breaking, vice versa
	// EFFECTS: Returns 1 if the recipe entered by user is existing in the recipe
	// book and there are enough atoms in the inventory to
	// blend or break the atoms according to user input. If there is no such recipe
	// or there are not sufficient amount of atoms to blend or break this method
	// returns 0 instead
	public int blend(int consumedAtomID, int goalAtomID) {
		if (consumedAtomID > goalAtomID) {
			Recipe givenRecipe = new Recipe(consumedAtomID, goalAtomID);
			if (recipeCheck(breakRecipeBook, givenRecipe) && removeFromInventory(consumedAtomID, 1)) {
				int createdAtomAmount = atomNumber;
				addToInventory(goalAtomID, createdAtomAmount);
				return 1;
			} else {
				return 0;
			}
		} else {
			Recipe givenRecipe = new Recipe(consumedAtomID, goalAtomID);
			if (recipeCheck(blendRecipeBook, givenRecipe) && removeFromInventory(consumedAtomID, atomNumber)) {
				addToInventory(goalAtomID, 1);
				return 1;
			} else {
				return 0;
			}
		}
	}

	private boolean removeFromInventory(int atomID, int amount) {
		// removes the consumed atoms
		return Inventory.removeAtom(atomID, amount);
	}

	private void addToInventory(int atomID, int amount) {
		// adds the newly created atoms
		Inventory.addAtom(atomID, amount);
	}

	private boolean recipeCheck(ArrayList<Recipe> recipees, Recipe recipe) {
		boolean inList = false;
		for (int i = 0; i < recipees.size(); i++) {
			if (recipees.get(i).goalAtom == recipe.goalAtom
					&& recipees.get(i).ingredientAtomID == recipe.ingredientAtomID) {
				inList = true;
				atomNumber = recipees.get(i).atomAmount;
			}
		}
		return inList;
	}
}
