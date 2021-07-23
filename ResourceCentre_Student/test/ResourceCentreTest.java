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
	public void testAddChromebook() {
		//fail("Not yet implemented");
		// write your code here
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
	public void testRetrieveAllChromebook() {
		//fail("Not yet implemented");
		// write your code here
		// Test if Item list is not null but empty, so that can add a new item

	}

	@Test
	public void testDoLoanCamcorder() {
		//fail("Not yet implemented");
		// write your code here

	}

	@Test
	public void testDoLoanChromebook() {
		//fail("Not yet implemented");
		// write your code here

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
	public void testDoReturnCamcorder() {
		//fail("Not yet implemented");
		// write your code here

	}
	@Test
	public void testDoReturnChromebook() {
		//fail("Not yet implemented");
		// write your code here
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
