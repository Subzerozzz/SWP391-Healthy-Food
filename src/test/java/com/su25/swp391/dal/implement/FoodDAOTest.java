/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.entity.Food;
import com.su25.swp391.entity.FoodDraft;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class FoodDAOTest {
    
    public FoodDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class FoodDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        FoodDAO instance = new FoodDAO();
        List<Food> expResult = null;
        List<Food> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFromResultSet method, of class FoodDAO.
     */
    @Test
    public void testGetFromResultSet() throws Exception {
        System.out.println("getFromResultSet");
        ResultSet resultSet = null;
        FoodDAO instance = new FoodDAO();
        Food expResult = null;
        Food result = instance.getFromResultSet(resultSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkExistFoodName method, of class FoodDAO.
     */
    @Test
    public void testCheckExistFoodName() {
        System.out.println("checkExistFoodName");
        String name = "";
        FoodDAO instance = new FoodDAO();
        Food expResult = null;
        Food result = instance.checkExistFoodName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByCategoryId method, of class FoodDAO.
     */
    @Test
    public void testFindByCategoryId() {
        System.out.println("findByCategoryId");
        Integer categoryId = null;
        FoodDAO instance = new FoodDAO();
        List<Food> expResult = null;
        List<Food> result = instance.findByCategoryId(categoryId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFoodByName method, of class FoodDAO.
     */
    @Test
    public void testGetFoodByName() {
        System.out.println("getFoodByName");
        String foodName = "";
        FoodDAO instance = new FoodDAO();
        List<Food> expResult = null;
        List<Food> result = instance.getFoodByName(foodName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRecordByPage method, of class FoodDAO.
     */
    @Test
    public void testFindRecordByPage() {
        System.out.println("findRecordByPage");
        int i = 0;
        FoodDAO instance = new FoodDAO();
        List<Food> expResult = null;
        List<Food> result = instance.findRecordByPage(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRecordByPageForCategory method, of class FoodDAO.
     */
    @Test
    public void testFindRecordByPageForCategory() {
        System.out.println("findRecordByPageForCategory");
        Integer categoryID = null;
        int i = 0;
        FoodDAO instance = new FoodDAO();
        List<Food> expResult = null;
        List<Food> result = instance.findRecordByPageForCategory(categoryID, i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecordByPageForSearch method, of class FoodDAO.
     */
    @Test
    public void testGetRecordByPageForSearch() {
        System.out.println("getRecordByPageForSearch");
        String foodName = "";
        int i = 0;
        FoodDAO instance = new FoodDAO();
        List<Food> expResult = null;
        List<Food> result = instance.getRecordByPageForSearch(foodName, i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllMap method, of class FoodDAO.
     */
    @Test
    public void testFindAllMap() {
        System.out.println("findAllMap");
        FoodDAO instance = new FoodDAO();
        Map<Integer, Food> expResult = null;
        Map<Integer, Food> result = instance.findAllMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class FoodDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Food t = null;
        FoodDAO instance = new FoodDAO();
        int expResult = 0;
        int result = instance.insert(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class FoodDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Food t = null;
        FoodDAO instance = new FoodDAO();
        boolean expResult = false;
        boolean result = instance.update(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class FoodDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Food t = null;
        FoodDAO instance = new FoodDAO();
        boolean expResult = false;
        boolean result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFromResultFood_Draft method, of class FoodDAO.
     */
    @Test
    public void testGetFromResultFood_Draft() {
        System.out.println("getFromResultFood_Draft");
        FoodDraft f = null;
        FoodDAO instance = new FoodDAO();
        Food expResult = null;
        Food result = instance.getFromResultFood_Draft(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class FoodDAO.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Integer id = null;
        FoodDAO instance = new FoodDAO();
        Food expResult = null;
        Food result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterChanning method, of class FoodDAO.
     */
    @Test
    public void testFilterChanning() {
        System.out.println("filterChanning");
        Integer categoryId = null;
        String foodName = "";
        Integer limit = null;
        Integer currentPage = null;
        FoodDAO instance = new FoodDAO();
        List<Food> expResult = null;
        List<Food> result = instance.filterChanning(categoryId, foodName, limit, currentPage);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findFoodNameList method, of class FoodDAO.
     */
    @Test
    public void testFindFoodNameList() {
        System.out.println("findFoodNameList");
        FoodDAO instance = new FoodDAO();
        List<String> expResult = null;
        List<String> result = instance.findFoodNameList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFoodByFoodIdFromOrderItems method, of class FoodDAO.
     */
    @Test
    public void testGetFoodByFoodIdFromOrderItems() {
        System.out.println("getFoodByFoodIdFromOrderItems");
        int foodId = 0;
        FoodDAO instance = new FoodDAO();
        List<Food> expResult = null;
        List<Food> result = instance.getFoodByFoodIdFromOrderItems(foodId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class FoodDAO.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FoodDAO.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
