package edu.towson.cis.cosc603.project4.vendingmachine;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class VendingMachineTest {

	/**  Declaring necessary test objects for {@link VendingMachine}. */
	static VendingMachine vendingMachine1;
	static VendingMachineItem item1;
	static VendingMachineItem item2;

	/**
	 * Initializes the necessary test objects for the test cases to use.
	 * 
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
//        System.out.println("setUp");
        vendingMachine1 = new VendingMachine();
		item1 = new VendingMachineItem("Snickers",1.50);
		item2 = new VendingMachineItem("Baby Ruth",1.50);

	}
	
	@Before
	public void setUp1() throws Exception {
		vendingMachine1.addItem(item1, "A");
		vendingMachine1.addItem(item2, "C");
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Test constructor postcondition: all entries in itemArray are null, balance set to be 0
	 */
	@Test
	public void testVendingMachine() {
//        System.out.println("testVendingMachine");
//		assertNotNull(vendingMachine1.getItem("A"));
//		assertNull(vendingMachine1.getItem("B"));
//		assertNotNull(vendingMachine1.getItem("C"));
//		assertNull(vendingMachine1.getItem("D"));
//		assertEquals(0,vendingMachine1.balance,0.001);
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Add items with normal input.
	 */
	@Test
	public void testAddItemA() {
//        System.out.println("testAddItemA");
		vendingMachine1.addItem(item1, "B");
		assertSame(item1,vendingMachine1.getItem("B"));
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Add an item to a slot that is already occupied. 
	 */
	@Test
	public void testAddItemB() {
//        System.out.println("testAddItemB");
        try { // try inserting in slot A
			vendingMachine1.addItem(new VendingMachineItem("Fith Avenue",1.00), "A");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot A already occupied", e.getMessage());
		}
		assertSame(item1,vendingMachine1.getItem("A"));
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Add an item with an invalid code.
	 */
	@Test
	public void testAddItemC() {
//        System.out.println("testAddItemC");
        try { // try inserting in slot E
			vendingMachine1.addItem(new VendingMachineItem("Fith Avenue",1.00), "E");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}
	}
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * Remove item from empty slot with invalid code.
	 */
	@Test
	public void testRemoveItemA() {
//        System.out.println("testRemoveItemA");
        try { // try removing item from slot E
	        vendingMachine1.removeItem("E");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}
		assertSame(item1,vendingMachine1.getItem("A"));
		assertNull(vendingMachine1.getItem("B"));
		assertSame(item2,vendingMachine1.getItem("C"));
		assertNull(vendingMachine1.getItem("D"));
	}
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * Remove item from occupied slot with invalid code.
	 */
	@Test
	public void testRemoveItemB() {
//        System.out.println("testRemoveItemB");
        try { // try removing item from slot E
	        vendingMachine1.removeItem("E");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}
	}
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * Remove item from occupied slot with valid code.
	 */
	@Test
	public void testRemoveItemC() {
//        System.out.println("testRemoveItemC");
        VendingMachineItem itemA = vendingMachine1.getItem("A");
        assertSame(itemA, vendingMachine1.removeItem("A"));
        assertNull(vendingMachine1.getItem("A"));
	}

	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * Remove item from empty slot with valid code.
	 */
	@Test
	public void testRemoveItemD() {
//        System.out.println("testRemoveItemD");
		try { // try removing item from slot B
	        vendingMachine1.removeItem("B");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot B is empty -- cannot remove item", e.getMessage());
		}
		assertNull(vendingMachine1.getItem("B"));
	}
	
	/**
	 * Test for the insertMoney() method of the {@link VendingMachine} class.
	 * Test balance with normal input values.
	 */
	@Test
	public void testInsertMoneyA() {
//        System.out.println("testInsertMoneyA");
        Double currentBalance = vendingMachine1.balance;
        vendingMachine1.insertMoney(1.00);
        currentBalance += 1.00;
		assertEquals(currentBalance,vendingMachine1.balance,0.001);
		vendingMachine1.insertMoney(0.00);
		currentBalance += 0.00;
		assertEquals(currentBalance,vendingMachine1.balance,0.001);
	}
	
	
	/**
	 * Test for the insertMoney() method of the {@link VendingMachine} class.
	 * Test balance with negative input values.
	 */
	@Test
	public void testInsertMoneyB() {
//        System.out.println("testInsertMoneyB");
        Double currentBalance = vendingMachine1.balance;
        try {
        	vendingMachine1.insertMoney(-1.00);
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid amount.  Amount must be >= 0", e.getMessage());
        }
		assertEquals(currentBalance,vendingMachine1.balance,0.001);

		vendingMachine1.insertMoney(1.00);
        currentBalance += 1.00;
		assertEquals(currentBalance,vendingMachine1.balance,0.001);
	}
	
	/**
	 * Test for the getBalance() method of the {@link VendingMachine} class.
	 * Test getBalance with normal input values.
	 */
	@Test
	public void testGetBalanceA() {
//        System.out.println("testGetBalanceA");
        Double currentBalance = vendingMachine1.getBalance();

        vendingMachine1.insertMoney(0.00);
		assertEquals(currentBalance,vendingMachine1.getBalance(),0.001);

		vendingMachine1.insertMoney(1.00);
        currentBalance += 1.00;
		assertEquals(currentBalance,vendingMachine1.getBalance(),0.001);
	}

	/**
	 * Test for the getBalance() method of the {@link VendingMachine} class.
	 * Test getBalance with negative input values.
	 */
	@Test
	public void testGetBalanceB() {
//        System.out.println("testGetBalanceB");
        Double currentBalance = vendingMachine1.getBalance();

        try {
        	vendingMachine1.insertMoney(-0.75);
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid amount.  Amount must be >= 0", e.getMessage());
        }
		assertEquals(currentBalance,vendingMachine1.getBalance(),0.001);
	}

	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * Test making a purchase from an empty slot.
	 */
	@Test
	public void testMakePurchaseA() {
//        System.out.println("testMakePurchaseA");
		assertFalse(vendingMachine1.makePurchase("D"));
	}
	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * Test making a purchase for an item with sufficient balance.
	 */
	@Test
	public void testMakePurchaseB() {
//        System.out.println("testMakePurchaseB");
		Double currentBalance;
		vendingMachine1.insertMoney(1.50);
		currentBalance = vendingMachine1.getBalance();
		assertTrue(vendingMachine1.makePurchase("C"));
		assertNull(vendingMachine1.getItem("C"));
		}
	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * Test making a purchase for an item with insufficient balance.
	 */
	@Test
	public void testMakePurchaseC() {
//        System.out.println("testMakePurchaseC");
		assertFalse(vendingMachine1.makePurchase("A"));
        assertSame(item1, vendingMachine1.removeItem("A"));
		vendingMachine1.insertMoney(0.25);
		assertFalse(vendingMachine1.makePurchase("C"));
        assertSame(item2, vendingMachine1.removeItem("C"));
	}
	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * Test making a purchase for an item with invalid slot code.
	 */
	@Test
	public void testMakePurchaseD() {
//        System.out.println("testMakePurchaseD");
        try {
        	vendingMachine1.makePurchase("E");
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
        }
	}

	
	/**
	 * Test for the returnChange() method of the {@link VendingMachine} class.
	 * Test return change without making purchases.
	 */
	@Test
	public void testReturnChangeA() {
//        System.out.println("testReturnChangeA");
        Double currentBalance = 0.00;
		vendingMachine1.insertMoney(0.50);
        currentBalance += 0.50;
		vendingMachine1.insertMoney(0.25);
        currentBalance += 0.25;
		assertEquals(currentBalance,vendingMachine1.returnChange(),0.001);	
		assertEquals(0,vendingMachine1.getBalance(),0.001);	
	}
	
	/**
	 * Test for the returnChange() method of the {@link VendingMachine} class.
	 * Test return change with making purchases.
	 */
	@Test
	public void testReturnChangeB() {
//        System.out.println("testReturnChangeB");
        vendingMachine1.insertMoney(5.75);
        Double currentBalance = vendingMachine1.getBalance();
        vendingMachine1.makePurchase("A");
        currentBalance -= 1.50;
		assertEquals(currentBalance,vendingMachine1.returnChange(),0.001);	
		assertEquals(0,vendingMachine1.getBalance(),0.001);
	}
	
	@After
	public void tearDown1() throws Exception {
		double change = vendingMachine1.returnChange();
		if (vendingMachine1.getItem("A") != null) {
			vendingMachine1.removeItem("A");
		}
		if (vendingMachine1.getItem("B") != null) {
			vendingMachine1.removeItem("B");
		}
		if (vendingMachine1.getItem("C") != null) {
			vendingMachine1.removeItem("C");
		}
		if (vendingMachine1.getItem("D") != null) {
			vendingMachine1.removeItem("D");
		}
	}

	/**
	 * Cleans up test objects after a test case is executed.
	 */
	@AfterClass
	public static void tearDown(){
//        System.out.println("tearDown");
        vendingMachine1 = null;
        item1 = null;
        item2 = null;
        
	}
}
