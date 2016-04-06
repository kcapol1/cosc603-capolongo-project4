package edu.towson.cis.cosc603.project4.vendingmachine;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class VendingMachineTest {

	/**  Declaring necessary test objects for {@link VendingMachine}. */
	static VendingMachine vendingMachine1; // empty vending machine
	static VendingMachine vendingMachine2; // object to test addItem method
	static VendingMachine vendingMachine3; // object to test removeItem method
	static VendingMachine vendingMachine4; // object to test insertMoney method
	static VendingMachine vendingMachine5; // object to test getBalance method
	static VendingMachine vendingMachine6; // object to test makePurchase method
	static VendingMachine vendingMachine7; // object to test returnChange method

	/**
	 * Initializes the necessary test objects for the test cases to use.
	 * 
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
//        System.out.println("setUp");
        vendingMachine1 = new VendingMachine();
        vendingMachine2 = new VendingMachine();
        vendingMachine3 = new VendingMachine();
        vendingMachine4 = new VendingMachine();
        vendingMachine5 = new VendingMachine();
        vendingMachine6 = new VendingMachine();
        vendingMachine7 = new VendingMachine();
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Test constructor postcondition: all entries in itemArray are null, balance set to be 0
	 */
	@Test
	public void testVendingMachine() {
//        System.out.println("testVendingMachine");
		assertNull(vendingMachine1.getItem("A"));
		assertNull(vendingMachine1.getItem("B"));
		assertNull(vendingMachine1.getItem("C"));
		assertNull(vendingMachine1.getItem("D"));
		assertEquals(0,vendingMachine1.balance,0.001);
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Add items with normal input.
	 */
	@Test
	public void testAddItemA() {
//        System.out.println("testAddItemA");
		VendingMachineItem itemA = new VendingMachineItem("Snickers",1.50);
        VendingMachineItem itemB = new VendingMachineItem("York Peppermint Patty",1.25);
        VendingMachineItem itemC = new VendingMachineItem("Butterfinger",1.00);
        VendingMachineItem itemD = new VendingMachineItem("Baby Ruth",1.25);

        vendingMachine2.addItem(itemA, "A");
		vendingMachine2.addItem(itemB, "B");
		vendingMachine2.addItem(itemC, "C");
		vendingMachine2.addItem(itemD, "D");

		assertSame(itemA,vendingMachine2.getItem("A"));
        assertEquals("Snickers",vendingMachine2.getItem("A").getName());
		assertEquals(1.50,vendingMachine2.getItem("A").getPrice(),0.001);

		assertSame(itemB,vendingMachine2.getItem("B"));
		assertEquals("York Peppermint Patty",vendingMachine2.getItem("B").getName());
		assertEquals(1.25,vendingMachine2.getItem("B").getPrice(),0.001);

		assertSame(itemC,vendingMachine2.getItem("C"));
		assertEquals("Butterfinger",vendingMachine2.getItem("C").getName());
		assertEquals(1.00,vendingMachine2.getItem("C").getPrice(),0.001);

		assertSame(itemD,vendingMachine2.getItem("D"));
		assertEquals("Baby Ruth",vendingMachine2.getItem("D").getName());
		assertEquals(1.25,vendingMachine2.getItem("D").getPrice(),0.001);

		itemA = null;
		itemB = null;
		itemC = null;
		itemD = null;		
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Add an item to a slot that is already occupied. 
	 */
	@Test
	public void testAddItemB() {
//        System.out.println("testAddItemB");
        VendingMachineItem itemA = vendingMachine2.getItem("A");
        VendingMachineItem itemB = vendingMachine2.getItem("B");
        VendingMachineItem itemC = vendingMachine2.getItem("C");
        VendingMachineItem itemD = vendingMachine2.getItem("D");
        
        try { // try inserting in slot A
			vendingMachine2.addItem(new VendingMachineItem("Fith Avenue",1.00), "A");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot A already occupied", e.getMessage());
		}
		assertSame(itemA,vendingMachine2.getItem("A"));
        assertEquals("Snickers",vendingMachine2.getItem("A").getName());
		assertEquals(1.50,vendingMachine2.getItem("A").getPrice(),0.001);

		try { // try inserting in slot B
			vendingMachine2.addItem(new VendingMachineItem("Fith Avenue",1.00), "B");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot B already occupied", e.getMessage());
		}
		assertSame(itemB,vendingMachine2.getItem("B"));
		assertEquals("York Peppermint Patty",vendingMachine2.getItem("B").getName());
		assertEquals(1.25,vendingMachine2.getItem("B").getPrice(),0.001);

		try { // try inserting in slot C
			vendingMachine2.addItem(new VendingMachineItem("Fith Avenue",1.00), "C");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot C already occupied", e.getMessage());
		}
		assertSame(itemC,vendingMachine2.getItem("C"));
		assertEquals("Butterfinger",vendingMachine2.getItem("C").getName());
		assertEquals(1.00,vendingMachine2.getItem("C").getPrice(),0.001);

		try { // try inserting in slot D
			vendingMachine2.addItem(new VendingMachineItem("Fith Avenue",1.00), "D");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot D already occupied", e.getMessage());
		}
		assertSame(itemD,vendingMachine2.getItem("D"));
		assertEquals("Baby Ruth",vendingMachine2.getItem("D").getName());
		assertEquals(1.25,vendingMachine2.getItem("D").getPrice(),0.001);
	}
	
	/**
	 * Test for the addItem() method of the {@link VendingMachine} class.
	 * Add an item with an invalid code.
	 */
	@Test
	public void testAddItemC() {
//        System.out.println("testAddItemC");

        VendingMachineItem itemA = vendingMachine2.getItem("A");
        VendingMachineItem itemB = vendingMachine2.getItem("B");
        VendingMachineItem itemC = vendingMachine2.getItem("C");
        VendingMachineItem itemD = vendingMachine2.getItem("D");

        try { // try inserting in slot E
			vendingMachine2.addItem(new VendingMachineItem("Fith Avenue",1.00), "E");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}
		
		try { // try inserting in slot K
			vendingMachine2.addItem(new VendingMachineItem("Fith Avenue",1.00), "K");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}
		
		try { // try inserting in slot P
			vendingMachine2.addItem(new VendingMachineItem("Fith Avenue",1.00), "P");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}
		
		try { // try inserting in slot Z
			vendingMachine2.addItem(new VendingMachineItem("Fith Avenue",1.00), "Z");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}
		assertSame(itemA,vendingMachine2.getItem("A"));
		assertSame(itemB,vendingMachine2.getItem("B"));
		assertSame(itemC,vendingMachine2.getItem("C"));
		assertSame(itemD,vendingMachine2.getItem("D"));
	}
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * Remove items from empty vending machine with invalid codes.
	 */
	@Test
	public void testRemoveItemA() {
//        System.out.println("testRemoveItemA");

        try { // try removing item from slot E
	        vendingMachine3.removeItem("E");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}
        
		try { // try removing item from slot P
	        vendingMachine3.removeItem("P");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}

		try { // try removing item from slot Z
	        vendingMachine3.removeItem("Z");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}

		assertNull(vendingMachine3.getItem("A"));
		assertNull(vendingMachine3.getItem("B"));
		assertNull(vendingMachine3.getItem("C"));
		assertNull(vendingMachine3.getItem("D"));
	}
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * Remove items from occupied slots with invalid codes.
	 */
	@Test
	public void testRemoveItemB() {
//        System.out.println("testRemoveItemB");
        vendingMachine3.addItem(new VendingMachineItem("Snickers",1.50), "A");
		vendingMachine3.addItem(new VendingMachineItem("York Peppermint Patty",1.25), "B");
		vendingMachine3.addItem(new VendingMachineItem("Butterfinger",1.00), "C");
		vendingMachine3.addItem(new VendingMachineItem("Baby Ruth",1.25), "D");
 
		VendingMachineItem itemA = vendingMachine3.getItem("A");
        VendingMachineItem itemB = vendingMachine3.getItem("B");
        VendingMachineItem itemC = vendingMachine3.getItem("C");
        VendingMachineItem itemD = vendingMachine3.getItem("D");

        try { // try removing item from slot E
	        vendingMachine3.removeItem("E");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}

		try { // try removing item from slot P
	        vendingMachine3.removeItem("P");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}

		try { // try removing item from slot Z
	        vendingMachine3.removeItem("Z");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
		}

		assertSame(itemA, vendingMachine3.getItem("A"));
        assertSame(itemB, vendingMachine3.getItem("B"));
        assertSame(itemC, vendingMachine3.getItem("C"));
        assertSame(itemD, vendingMachine3.getItem("D"));
        
		itemA = null;
		itemB = null;
		itemC = null;
		itemD = null;		        
	}
	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * Remove items from occupied slots with valid codes.
	 */
	@Test
	public void testRemoveItemC() {
//        System.out.println("testRemoveItemC");

        VendingMachineItem itemA = vendingMachine3.getItem("A");
        assertSame(itemA, vendingMachine3.removeItem("A"));
        assertNull(vendingMachine3.getItem("A"));
 
        VendingMachineItem itemB = vendingMachine3.getItem("B");
        assertSame(itemB, vendingMachine3.removeItem("B"));
        assertNull(vendingMachine3.getItem("B"));

        VendingMachineItem itemC = vendingMachine3.getItem("C");
        assertSame(itemC, vendingMachine3.removeItem("C"));
        assertNull(vendingMachine3.getItem("C"));

        VendingMachineItem itemD = vendingMachine3.getItem("D");
        assertSame(itemD, vendingMachine3.removeItem("D"));
        assertNull(vendingMachine3.getItem("D"));
        
		itemA = null;
		itemB = null;
		itemC = null;
		itemD = null;		        
	}

	
	/**
	 * Test for the removeItem() method of the {@link VendingMachine} class.
	 * Remove items from empty vending machine with valid codes.
	 */
	@Test
	public void testRemoveItemD() {
//        System.out.println("testRemoveItemD");
		try { // try removing item from slot A
	        vendingMachine3.removeItem("A");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot A is empty -- cannot remove item", e.getMessage());
		}

		try { // try removing item from slot B
	        vendingMachine3.removeItem("B");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot B is empty -- cannot remove item", e.getMessage());
		}
		
		try { // try removing item from slot C
	        vendingMachine3.removeItem("C");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot C is empty -- cannot remove item", e.getMessage());
		}
		
		try { // try removing item from slot D
	        vendingMachine3.removeItem("D");
		} catch (VendingMachineException e) {
			// test exception error message
			assertEquals("Slot D is empty -- cannot remove item", e.getMessage());
		}
		assertNull(vendingMachine3.getItem("A"));
		assertNull(vendingMachine3.getItem("B"));
		assertNull(vendingMachine3.getItem("C"));
		assertNull(vendingMachine3.getItem("D"));
	}
	
	/**
	 * Test for the insertMoney() method of the {@link VendingMachine} class.
	 * Test balance with normal input values.
	 */
	@Test
	public void testInsertMoneyA() {
//        System.out.println("testInsertMoneyA");
        Double currentBalance = vendingMachine4.balance;
        vendingMachine4.insertMoney(1.00);
        currentBalance += 1.00;
		assertEquals(currentBalance,vendingMachine4.balance,0.001);
		vendingMachine4.insertMoney(0.00);
		currentBalance += 0.00;
		assertEquals(currentBalance,vendingMachine4.balance,0.001);
		vendingMachine4.insertMoney(0.25);
		currentBalance += 0.25;
		assertEquals(currentBalance,vendingMachine4.balance,0.001);
		vendingMachine4.insertMoney(0.75);
		currentBalance += 0.75;
		assertEquals(currentBalance,vendingMachine4.balance,0.001);
	}
	
	
	/**
	 * Test for the insertMoney() method of the {@link VendingMachine} class.
	 * Test balance with negative input values.
	 */
	@Test
	public void testInsertMoneyB() {
//        System.out.println("testInsertMoneyB");
        Double currentBalance = vendingMachine4.balance;
        try {
        	vendingMachine4.insertMoney(-1.00);
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid amount.  Amount must be >= 0", e.getMessage());
        }
		assertEquals(currentBalance,vendingMachine4.balance,0.001);

		vendingMachine4.insertMoney(1.00);
        currentBalance += 1.00;
		assertEquals(currentBalance,vendingMachine4.balance,0.001);

        try {
        	vendingMachine4.insertMoney(-0.25);
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid amount.  Amount must be >= 0", e.getMessage());
        }
		assertEquals(currentBalance,vendingMachine4.balance,0.001);

		vendingMachine4.insertMoney(1.00);
        currentBalance += 1.00;
		assertEquals(currentBalance,vendingMachine4.balance,0.001);

        try {
        	vendingMachine4.insertMoney(-0.75);
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid amount.  Amount must be >= 0", e.getMessage());
        }
		assertEquals(currentBalance,vendingMachine4.balance,0.001);
	}
	
	/**
	 * Test for the getBalance() method of the {@link VendingMachine} class.
	 * Test balance with normal input values.
	 */
	@Test
	public void testGetBalanceA() {
//        System.out.println("testGetBalanceA");
        Double currentBalance = vendingMachine5.getBalance();

        vendingMachine5.insertMoney(0.00);
		assertEquals(currentBalance,vendingMachine5.getBalance(),0.001);

		vendingMachine5.insertMoney(1.00);
        currentBalance += 1.00;
		assertEquals(currentBalance,vendingMachine5.getBalance(),0.001);

		vendingMachine5.insertMoney(0.75);
        currentBalance += 0.75;
		assertEquals(currentBalance,vendingMachine5.getBalance(),0.001);
	}

	/**
	 * Test for the getBalance() method of the {@link VendingMachine} class.
	 * Test balance with negative input values.
	 */
	@Test
	public void testGetBalanceB() {
//        System.out.println("testGetBalanceB");
        Double currentBalance = vendingMachine5.getBalance();

        try {
        	vendingMachine5.insertMoney(-0.75);
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid amount.  Amount must be >= 0", e.getMessage());
        }
		assertEquals(currentBalance,vendingMachine5.getBalance(),0.001);

		vendingMachine5.insertMoney(0.75);
        currentBalance += 0.75;

        try {
        	vendingMachine5.insertMoney(-1.75);
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid amount.  Amount must be >= 0", e.getMessage());
        }
		assertEquals(currentBalance,vendingMachine5.getBalance(),0.001);
	}

	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * Test making a purchase from an empty vending machine.
	 */
	@Test
	public void testMakePurchaseA() {
//        System.out.println("testMakePurchaseA");
		assertFalse(vendingMachine6.makePurchase("A"));
		assertFalse(vendingMachine6.makePurchase("B"));
		assertFalse(vendingMachine6.makePurchase("C"));
		assertFalse(vendingMachine6.makePurchase("D"));
	}
	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * Test making a purchase for an item with sufficient balance.
	 */
	@Test
	public void testMakePurchaseB() {
//        System.out.println("testMakePurchaseB");
		Double currentBalance;
		vendingMachine6.addItem(new VendingMachineItem("Snickers",1.50), "A");
 		vendingMachine6.addItem(new VendingMachineItem("York Peppermint Patty",1.25), "B");
		vendingMachine6.addItem(new VendingMachineItem("Butterfinger",1.00), "C");
		vendingMachine6.addItem(new VendingMachineItem("Baby Ruth",1.25), "D");
		vendingMachine6.insertMoney(5.00);
		currentBalance = vendingMachine6.getBalance();
		assertTrue(vendingMachine6.makePurchase("A"));
        currentBalance -= 1.50;		
		assertNull(vendingMachine6.getItem("A"));
		assertEquals(currentBalance,vendingMachine6.getBalance(),0.001);
		assertTrue(vendingMachine6.makePurchase("C"));
        currentBalance -= 1.00;		
		assertNull(vendingMachine6.getItem("C"));
		assertEquals(currentBalance,vendingMachine6.getBalance(),0.001);
		assertTrue(vendingMachine6.makePurchase("B"));
        currentBalance -= 1.25;		
		assertNull(vendingMachine6.getItem("B"));
		assertEquals(currentBalance,vendingMachine6.getBalance(),0.001);
		assertTrue(vendingMachine6.makePurchase("D"));
        currentBalance -= 1.25;		
		assertNull(vendingMachine6.getItem("D"));
		assertEquals(currentBalance,vendingMachine6.getBalance(),0.001);	
	}
	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * Test making a purchase for an item with insufficient balance.
	 */
	@Test
	public void testMakePurchaseC() {
//        System.out.println("testMakePurchaseC");
        vendingMachine6.addItem(new VendingMachineItem("Snickers",1.50), "A");
 		vendingMachine6.addItem(new VendingMachineItem("York Peppermint Patty",1.25), "B");
		vendingMachine6.addItem(new VendingMachineItem("Butterfinger",1.00), "C");
		vendingMachine6.addItem(new VendingMachineItem("Baby Ruth",1.25), "D");
        VendingMachineItem itemA = vendingMachine6.getItem("A");
        VendingMachineItem itemB = vendingMachine6.getItem("B");
        VendingMachineItem itemC = vendingMachine6.getItem("C");
        VendingMachineItem itemD = vendingMachine6.getItem("D");
		assertFalse(vendingMachine6.makePurchase("A"));
        assertSame(itemA, vendingMachine6.removeItem("A"));
		vendingMachine6.insertMoney(0.25);
		assertFalse(vendingMachine6.makePurchase("B"));
        assertSame(itemB, vendingMachine6.removeItem("B"));
		vendingMachine6.insertMoney(0.5);
		assertFalse(vendingMachine6.makePurchase("C"));
        assertSame(itemC, vendingMachine6.removeItem("C"));
		assertFalse(vendingMachine6.makePurchase("D"));
        assertSame(itemD, vendingMachine6.removeItem("D"));
	}
	
	/**
	 * Test for the makePurchase() method of the {@link VendingMachine} class.
	 * Test making a purchase for an item with invalid slot code.
	 */
	@Test
	public void testMakePurchaseD() {
//        System.out.println("testMakePurchaseD");
        try {
        	vendingMachine6.makePurchase("E");
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
        }
        
        try {
        	vendingMachine6.makePurchase("P");
        } catch  (VendingMachineException e) {
			// test exception error message
			assertEquals("Invalid code for vending machine item", e.getMessage());
        }
        
        try {
        	vendingMachine6.makePurchase("Z");
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
        vendingMachine7.addItem(new VendingMachineItem("Snickers",1.50), "A");
 		vendingMachine7.addItem(new VendingMachineItem("York Peppermint Patty",1.25), "B");
		vendingMachine7.addItem(new VendingMachineItem("Butterfinger",1.00), "C");
		vendingMachine7.addItem(new VendingMachineItem("Baby Ruth",1.25), "D");
        vendingMachine7.insertMoney(0.75);
        currentBalance += 0.75;
		vendingMachine7.insertMoney(1.00);
        currentBalance += 1.00;
		vendingMachine7.insertMoney(0.50);
        currentBalance += 0.50;
		vendingMachine7.insertMoney(0.25);
        currentBalance += 0.25;
		assertEquals(currentBalance,vendingMachine7.returnChange(),0.001);	
		assertEquals(0,vendingMachine7.getBalance(),0.001);	
	}
	
	/**
	 * Test for the returnChange() method of the {@link VendingMachine} class.
	 * Test return change with making purchases.
	 */
	@Test
	public void testReturnChangeB() {
//        System.out.println("testReturnChangeB");
        vendingMachine7.insertMoney(5.75);
        Double currentBalance = vendingMachine7.getBalance();
        vendingMachine7.makePurchase("A");
        currentBalance -= 1.50;
        vendingMachine7.makePurchase("B");
        currentBalance -= 1.25;
        vendingMachine7.makePurchase("C");
        currentBalance -= 1.00;
        vendingMachine7.makePurchase("D");
        currentBalance -= 1.25;
		assertEquals(currentBalance,vendingMachine7.returnChange(),0.001);	
		assertEquals(0,vendingMachine7.getBalance(),0.001);
	}
	
	/**
	 * Cleans up test objects after a test case is executed.
	 */
	@AfterClass
	public static void tearDown(){
//        System.out.println("tearDown");
        vendingMachine1 = null;
        vendingMachine2 = null;
        vendingMachine3 = null;
        vendingMachine4 = null;
        vendingMachine5 = null;
        vendingMachine6 = null;
        vendingMachine7 = null;
	}
}
