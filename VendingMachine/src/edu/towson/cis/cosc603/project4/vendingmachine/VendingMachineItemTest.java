package edu.towson.cis.cosc603.project4.vendingmachine;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;




public class VendingMachineItemTest {

	/**  Declaring necessary test objects for {@link VendingMachineItem}. */
	static VendingMachineItem item1;
	static VendingMachineItem item2;
	static VendingMachineItem item3;
	static VendingMachineItem item4;

	/**
	 * Initializes the necessary test objects for the test cases to use.
	 * 
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
//        System.out.println("setUp");
		item1 = new VendingMachineItem("Snickers", 1.00);
		item2 = new VendingMachineItem("Baby Ruth", 0.00);
	}

	/**
	 * Test for the VendingMachineItem() constructor of the {@link VendingMachineItem} class.
	 * Test for empty name input.
	 */
	@Test
	public void testVendingMachineItemA() {
//		System.out.println("testVendingMachineItemA");
		try {
	    	item3 = new VendingMachineItem("", 1.00);
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Name cannot be empty", e.getMessage());
		}
		assertNull(item3);
	}

	/**
	 * Test for the VendingMachineItem() constructor of the {@link VendingMachineItem} class.
	 * Test for negative price input.
	 */
	@Test
	public void testVendingMachineItemB() {
//		System.out.println("testVendingMachineItemB");
		try {
	    	item4 = new VendingMachineItem("York Peppermint Patty", -1.00);
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Price cannot be less than zero", e.getMessage());
		}
		assertNull(item4);
	}
	
	/**
	 * Test for the getName() method of the {@link VendingMachineItem} class.
	 * Test normal input.
	 */
	@Test
	public void testGetName() {
//        System.out.println("testGetName");
		assertEquals("Snickers", item1.getName());
		assertEquals("Baby Ruth", item2.getName());
	}
	
	
	/**
	 * Test for the getPrice() method of the {@link VendingMachineItem} class.
	 * Test normal input.
	 */
	@Test
	public void testGetPriceA() {
//        System.out.println("testGetPriceA");
		assertEquals(1.00, item1.getPrice(),0.001);
	}
	
	/**
	 * Test for the getPrice() method of the {@link VendingMachineItem} class.
	 * Test zero (0) price input.
	 */
	@Test
	public void testGetPriceB() {
//        System.out.println("testGetPriceB");
		assertEquals(0.00, item2.getPrice(),0.001);
	}
	
	/**
	 * Cleans up test objects after a test case is executed.
	 */
	@AfterClass
	public static void tearDown(){
//        System.out.println("tearDown");
		item1 = null;
		item2 = null;
		item3 = null;
		item4 = null;
	}
}
