import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Chromebook cb1;
	private Chromebook cb2;
	
	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;
	
	public ResourceCentreTest() {
		super();
	}
	
	@Before
	public void setUp() throws Exception {
		// prepare test data
		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");

		camcorderList= new ArrayList<Camcorder>();
		chromebookList= new ArrayList<Chromebook>();
	}

	
	@Test
	public void testAddCamcorder() {
		// Item list is not null, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//Given an empty list, after adding 1 item, the size of the list is 1
		ResourceCentre.addCamcorder(camcorderList, cc1);		
		assertEquals("Test if that Camcorder arraylist size is 1?", 1, camcorderList.size());
		
		//The item just added is as same as the first item of the list
		assertSame("Test that Camcorder is added same as 1st item of the list?", cc1, camcorderList.get(0));
		
		//Add another item. test The size of the list is 2?
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test that Camcorder arraylist size is 2?", 2, camcorderList.size());
		
		// TC5
		assertSame("Test that second Camcorder item added is the same as the second item in the list", cc2, camcorderList.get(1));
	}
	@Test
	public void testAddChromebook() { // Done by: Ammar
		
		assertNotNull("Test if there is a valid Chromebook arraylist to add to", chromebookList);
		
		ResourceCentre.addChromebook(chromebookList, cb1);
		assertEquals("Given an empty list, after adding 1 chomebook, the size of the list is 1", 1, chromebookList.size());
		
		assertSame("Test that the Chromebook added is the same as 1st item of the list", cb1, chromebookList.get(0));
		
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Given a list with 1 Chromebook, after adding another chromebook, the size of the list is 2", 2, chromebookList.size());
		
		assertSame("Test that the second Chromebook item added is the same as the second item in the list", cb2, chromebookList.get(1));
		
	}
	
	@Test
	public void testRetrieveAllCamcorder() {
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid Camcorder arraylist to add to", camcorderList);
		
		//test if the list of camcorders retrieved from the SourceCentre is empty
				String allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);
				String testOutput = "";
				assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
				
		//Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Test if that Camcorder arraylist size is 2?", 2, camcorderList.size());
		
		//test if the expected output string same as the list of camcorders retrieved from the SourceCentre
		allCamcorder= ResourceCentre.retrieveAllCamcorder(camcorderList);

		testOutput = String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20d\n","CC0012", "Sony DSC-RX100M7", "Yes", "", 20);
	
		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);
		
	}
	@Test
	public void testRetrieveAllChromebook() { // Done by: Ammar
		
		// Test if Item list is not null but empty, so that can add a new item
		assertNotNull("Test if there is valid chromebookList arraylist to add to", chromebookList);
		
		//test if the list of chromebook retrieved from the SourceCentre is empty
		String allChromebook= ResourceCentre.retrieveAllChromebook(chromebookList);
		String testOutput = "";
		assertEquals("Check that ViewAllChromebook", testOutput, allChromebook);
				
		//Given an empty list, after adding 2 items, test if the size of the list is 2
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Test if that Chromebook arraylist size is 2?", 2, chromebookList.size());
		
		//test if the expected output string same as the list of chromebook retrieved from the SourceCentre
		allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		
		testOutput = String.format("%-10s %-30s %-10s %-10s %-20s\n", cb1.getAssetTag(), cb1.getDescription()
																	, ResourceCentre.showAvailability(cb1.getIsAvailable())
																	, cb1.getDueDate(), cb1.getOs());
		testOutput += String.format("%-10s %-30s %-10s %-10s %-20s\n", cb2.getAssetTag(), cb2.getDescription()
																	 , ResourceCentre.showAvailability(cb2.getIsAvailable())
																	 , cb2.getDueDate(), cb2.getOs());
		
		
		assertEquals("Check that ViewAllChromebooklist", testOutput, allChromebook);
		
	}

	@Test
	public void testDoLoanCamcorder() { // Done by: Ammar
		
		assertNotNull("Test if camcorderList exists.", camcorderList);
		
		ResourceCentre.addCamcorder(camcorderList, cc1);
		Boolean loanCamcorder = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "23-7-2021");
		assertTrue("Test that available item can be loaned.", loanCamcorder);
		
		loanCamcorder = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "23-7-2021");
		assertFalse("Test that the same item cannot be loaned again.", loanCamcorder);
		
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);
		loanCamcorder = ResourceCentre.doLoanCamcorder(camcorderList, "CC0012", "23-7-2021");
		assertFalse("Test that the unavailable items cannot be loaned", loanCamcorder);
		
		loanCamcorder = ResourceCentre.doLoanCamcorder(camcorderList, "CC0013", "23-7-2021");
		assertFalse("Test that non-existing items cannot be loaned", loanCamcorder);
		
	}
	
	@Test
	public void testDoLoanChromebook() { // Done by: Ammar
		
		// #1 (Boundary) Test that the list is to loan an item from, is not null.
		
		assertNotNull("Test if chromebookList exists.", chromebookList);
		
		// #2 (Normal)   Test that an available item can be loaned.
		
		ResourceCentre.addChromebook(chromebookList, cb1);
		Boolean loanStatus = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "23-7-2021");
		assertTrue("Test that an available item can be loaned.", loanStatus);
		
		// #3 (Error)    Test that the same item cannot be loaned again.
		
		loanStatus = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "23-7-2021");
		assertFalse("Test that the same item cannot be loaned again.", loanStatus);
		
		// #4 (Error)    Test that unavailable items cannot be loaned.
		
		ResourceCentre.addChromebook(chromebookList, cb2);
		cb2.setIsAvailable(false);
		loanStatus = ResourceCentre.doLoanChromebook(chromebookList, "CB0012", "23-7-2021");
		assertFalse("Test that the same item cannot be loaned again.", loanStatus);
		
		// #5 (Error)    Test that non-existing items cannot be loaned.
		
		loanStatus = ResourceCentre.doLoanChromebook(chromebookList, "CB0100", "23-7-2021");
		assertFalse("Test that non-existing items cannot be loaned.", loanStatus);
	}
	
	@Test
	public void testDoReturnCamcorder() { // Done by: Ammar
		
		ResourceCentre.addCamcorder(camcorderList, cc1);
		cc1.setIsAvailable(false);
		Boolean returnCamcorder = ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertTrue("Test that the item is unavailable before return", returnCamcorder);
		
		cc1.setIsAvailable(true);
		returnCamcorder = ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertFalse("Test that cannot return available items", returnCamcorder);
		
		returnCamcorder = ResourceCentre.doReturnCamcorder(camcorderList, "CC0013");
		assertFalse("Test that non-existing items cannot be returned", returnCamcorder);
		
		assertNotNull("Test that the camcorderList exists", camcorderList);
		
		ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "23-7-2021");
		ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertSame("Test that the due date is deleted after returning","",cc1.getDueDate());
		
	}
	@Test
	public void testDoReturnChromebook() {
		
		ResourceCentre.addChromebook(chromebookList, cb1);
		cb1.setIsAvailable(false);
		Boolean returnChromebook = ResourceCentre.doReturnChromebook(chromebookList, "CB0011");
		assertTrue("Test that the item is unavailable before return", returnChromebook);
		
		cb1.setIsAvailable(true);
		returnChromebook = ResourceCentre.doReturnChromebook(chromebookList, "CB0011");
		assertFalse("Test that cannot return available items", returnChromebook);
		
		returnChromebook = ResourceCentre.doReturnChromebook(chromebookList, "CB0013");
		assertFalse("Test that non-existing items cannot be returned", returnChromebook);
		
		assertNotNull("Test that the chromebookList exists", chromebookList);
		
		ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "23-7-2021");
		ResourceCentre.doReturnChromebook(chromebookList, "CB0011");
		assertSame("Test that the due date is deleted after returning","",cb1.getDueDate());
		
	}
	
	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
