/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.su25.swp391.dal.implement;

import com.su25.swp391.entity.Account;
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
 * @author Hang
 */
public class AccountDAOTest {
    
    public AccountDAOTest() {
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
     * Test of getFromResultSet method, of class AccountDAO.
     */
    @Test
    public void testGetFromResultSet() throws Exception {
        System.out.println("getFromResultSet");
        ResultSet resultSet = null;
        AccountDAO instance = new AccountDAO();
        Account expResult = null;
        Account result = instance.getFromResultSet(resultSet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAccountByRole method, of class AccountDAO.
     */
    @Test
    public void testFindAccountByRole() {
        System.out.println("findAccountByRole");
        String nutri = "";
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = null;
        List<Account> result = instance.findAccountByRole(nutri);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class AccountDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = null;
        List<Account> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllMap method, of class AccountDAO.
     */
    @Test
    public void testFindAllMap() {
        System.out.println("findAllMap");
        AccountDAO instance = new AccountDAO();
        Map<Integer, Account> expResult = null;
        Map<Integer, Account> result = instance.findAllMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePasswordByEmail method, of class AccountDAO.
     */
    @Test
    public void testUpdatePasswordByEmail() {
        System.out.println("updatePasswordByEmail");
        Account t = null;
        AccountDAO instance = new AccountDAO();
        boolean expResult = false;
        boolean result = instance.updatePasswordByEmail(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class AccountDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Account t = null;
        AccountDAO instance = new AccountDAO();
        boolean expResult = false;
        boolean result = instance.update(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAdmin method, of class AccountDAO.
     */
    @Test
    public void testUpdateAdmin() {
        System.out.println("updateAdmin");
        Account t = null;
        AccountDAO instance = new AccountDAO();
        boolean expResult = false;
        boolean result = instance.updateAdmin(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class AccountDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Account t = null;
        AccountDAO instance = new AccountDAO();
        boolean expResult = false;
        boolean result = instance.delete(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pagingAccount method, of class AccountDAO.
     */
    @Test
    public void testPagingAccount() {
        System.out.println("pagingAccount");
        int index = 0;
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = null;
        List<Account> result = instance.pagingAccount(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalAccount method, of class AccountDAO.
     */
    @Test
    public void testGetTotalAccount() {
        System.out.println("getTotalAccount");
        AccountDAO instance = new AccountDAO();
        int expResult = 0;
        int result = instance.getTotalAccount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterAccountsPaging method, of class AccountDAO.
     */
    @Test
    public void testFilterAccountsPaging() {
        System.out.println("filterAccountsPaging");
        String role = "";
        String status = "";
        int pageIndex = 0;
        int pageSize = 0;
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = null;
        List<Account> result = instance.filterAccountsPaging(role, status, pageIndex, pageSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class AccountDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Account t = null;
        AccountDAO instance = new AccountDAO();
        int expResult = 0;
        int result = instance.insert(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByEmail method, of class AccountDAO.
     */
    @Test
    public void testFindByEmail() {
        System.out.println("findByEmail");
        Account t = null;
        AccountDAO instance = new AccountDAO();
        Account expResult = null;
        Account result = instance.findByEmail(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class AccountDAO.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Integer id = null;
        AccountDAO instance = new AccountDAO();
        Account expResult = null;
        Account result = instance.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByEmailOrUsernameAndPass method, of class AccountDAO.
     */
    @Test
    public void testFindByEmailOrUsernameAndPass() {
        System.out.println("findByEmailOrUsernameAndPass");
        Account t = null;
        AccountDAO instance = new AccountDAO();
        Account expResult = null;
        Account result = instance.findByEmailOrUsernameAndPass(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllWithPagination method, of class AccountDAO.
     */
    @Test
    public void testFindAllWithPagination() {
        System.out.println("findAllWithPagination");
        int page = 0;
        int pageSize = 0;
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = null;
        List<Account> result = instance.findAllWithPagination(page, pageSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalAccountCount method, of class AccountDAO.
     */
    @Test
    public void testGetTotalAccountCount() {
        System.out.println("getTotalAccountCount");
        AccountDAO instance = new AccountDAO();
        int expResult = 0;
        int result = instance.getTotalAccountCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchAccountsByNameOrEmail method, of class AccountDAO.
     */
    @Test
    public void testSearchAccountsByNameOrEmail() {
        System.out.println("searchAccountsByNameOrEmail");
        String keyword = "";
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = null;
        List<Account> result = instance.searchAccountsByNameOrEmail(keyword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmailExists method, of class AccountDAO.
     */
    @Test
    public void testIsEmailExists() {
        System.out.println("isEmailExists");
        String email = "";
        AccountDAO instance = new AccountDAO();
        boolean expResult = false;
        boolean result = instance.isEmailExists(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filterAccountsWithPagination method, of class AccountDAO.
     */
    @Test
    public void testFilterAccountsWithPagination() {
        System.out.println("filterAccountsWithPagination");
        String role = "";
        String status = "";
        int page = 0;
        int pageSize = 0;
        AccountDAO instance = new AccountDAO();
        List<Account> expResult = null;
        List<Account> result = instance.filterAccountsWithPagination(role, status, page, pageSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalAccountCountWithFilter method, of class AccountDAO.
     */
    @Test
    public void testGetTotalAccountCountWithFilter() {
        System.out.println("getTotalAccountCountWithFilter");
        String role = "";
        String status = "";
        AccountDAO instance = new AccountDAO();
        int expResult = 0;
        int result = instance.getTotalAccountCountWithFilter(role, status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of activateAccount method, of class AccountDAO.
     */
    @Test
    public void testActivateAccount() {
        System.out.println("activateAccount");
        int accountId = 0;
        AccountDAO instance = new AccountDAO();
        boolean expResult = false;
        boolean result = instance.activateAccount(accountId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deactivateAccount method, of class AccountDAO.
     */
    @Test
    public void testDeactivateAccount() {
        System.out.println("deactivateAccount");
        int accountId = 0;
        AccountDAO instance = new AccountDAO();
        boolean expResult = false;
        boolean result = instance.deactivateAccount(accountId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AccountDAO.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AccountDAO.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
