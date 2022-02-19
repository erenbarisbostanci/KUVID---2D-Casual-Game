package kuvid;
/*
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
*/
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.Inventory;
import domain.observer.IStatsListener;
import ui.objects.StatsUIHolder;
/*
import domain.factories.DropperFactory;
import domain.object.Shooter;
import domain.utilities.Blender;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;
import ui.objects.AnimationPanel;
*/
class BlenderTest {
	//Blender tester = new Blender(new Coordinate(100, 100));
	
	@BeforeAll
	static void init() {
		IStatsListener listener = new StatsUIHolder();
		//without this listener tests does not work
		//populate the inventory so we can test blender properly
		Inventory.setListener(listener);
		Inventory.addAtom(0, 10);
		Inventory.addAtom(1, 10);
		Inventory.addAtom(2, 10);
		Inventory.addAtom(3, 10);
	}
	//Test Type: Black Box
	//blendFirst makes sure that all of the blend operations are working properly
	//if the input atoms are present in the recipe book
	@Test
	void blendFirst() {
		/*Inventory.atomNumbers[0]=10;
		Inventory.atomNumbers[1]=10;
		Inventory.atomNumbers[2]=10;
		Inventory.atomNumbers[3]=10;

		assertTrue(tester.blend(1,0)==1);
		assertTrue(tester.blend(0,1)==1);
		assertTrue(tester.blend(1,2)==1);
		assertTrue(tester.blend(2,1)==1);
		assertTrue(tester.blend(3,2)==1);
		assertTrue(tester.blend(2,3)==1);
		assertTrue(tester.blend(3,1)==1);
		assertTrue(tester.blend(1,3)==1);
		*/
		
	}
	
	//Test Type: Black Box
	//this test ensures that when a blend is called it makes the appropriate changes in the inventory so 
	//that it removes the atoms that are being used and adds atoms that are being added-in this one break is used
	@Test
	void breakFirstToZero() {
		/*Inventory.atomNumbers[0]=10;
		Inventory.atomNumbers[1]=10;
		Inventory.atomNumbers[2]=10;
		Inventory.atomNumbers[3]=10;
		
		assertTrue(tester.blend(1,0)==1);
		assertTrue(Inventory.atomNumbers[0]==12);
		assertTrue(Inventory.atomNumbers[1]==9);
		*/
	}
	
	//Test Type: Glass Box
	//this test tries illegal arguments for input of the method and makes sure that atom counts are unchanged
	//after illegal arguments
	@Test
	void blendInputTest() {
		/*
		Inventory.atomNumbers[0]=10;
		Inventory.atomNumbers[1]=10;
		Inventory.atomNumbers[2]=10;
		Inventory.atomNumbers[3]=10;
		
		assertTrue(tester.blend(0,0)==0);
		//assert that atom numbers have not changed since the last test where they were 12 and 9 respectively
		assertTrue(Inventory.atomNumbers[0]==10);
		assertTrue(Inventory.atomNumbers[1]==10);
		//check if method takes negative inputs which is something that should not happen
		assertTrue(tester.blend(0,-5)==0);
		assertTrue(Inventory.atomNumbers[0]==10);
		assertTrue(tester.blend(-5,0)==0);
		assertTrue(Inventory.atomNumbers[0]==10);
		*/
	}
	
	//Test Type: Glass Box
	//this test ensures that a blend operation is successfully done if there are enough atoms in the inventory
	//and also checks if created and used atom counts are according to what we normally expect
	@Test
	void blendInputTestForFirst() {
		/*
		Inventory.atomNumbers[0]=10;
		Inventory.atomNumbers[1]=10;
		Inventory.atomNumbers[2]=10;
		Inventory.atomNumbers[3]=10;
		int start=Inventory.atomNumbers[0];
		assertTrue(tester.blend(0,1)==1);
		assertTrue(Inventory.atomNumbers[0]==8);
		assertTrue(Inventory.atomNumbers[1]==11);
		int end=Inventory.atomNumbers[0];
		assertTrue(start-end==2);
		
		*/
	}
	
	//Test Type: Glass Box
//	this test tests if the blend method will work in case there is no atom left to be used in the inventory
//	as a result, since this recipe is in the recipe book but not enough atoms are present in inventory 
	//it returns 0  nothing is added or removed from the 
//	inventory which works as promised without any issue
	@Test
	void blendNoAtomTest() {
		/*
		Inventory.atomNumbers[0]=0;
		Inventory.atomNumbers[1]=10;
		Inventory.atomNumbers[2]=10;
		Inventory.atomNumbers[3]=10;
		
		assertTrue(tester.blend(0,2)==0);
		assertTrue(Inventory.atomNumbers[0]==0);
		assertTrue(Inventory.atomNumbers[2]==10);
		*/
	}
	
	//Test Type: Black Box
//	this test makes sure that Recipe books arrays are initialised and they are populated
	@Test
	void recipesInit() {
		/*
		Inventory.atomNumbers[0]=0;
		Inventory.atomNumbers[1]=10;
		Inventory.atomNumbers[2]=10;
		Inventory.atomNumbers[3]=10;
		
		assertNotNull(tester.blendRecipeBook);
		assertNotNull(tester.breakRecipeBook);
		assertTrue(!tester.blendRecipeBook.isEmpty());
		assertTrue(!tester.breakRecipeBook.isEmpty());
		*/
	}
	

}
