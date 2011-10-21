package jp.ac.titech.keitanxkeitan.xmlconverter;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommonUtilTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToUpperCamelCase() {
        assertEquals("TodoList", CommonUtil.toUpperCamelCase("todo_list"));
        assertEquals("TodoList", CommonUtil.toUpperCamelCase("todo list"));
        assertEquals("TodoList", CommonUtil.toUpperCamelCase("TodoList"));
        assertEquals("TodoList", CommonUtil.toUpperCamelCase("todoList"));
    }

    @Test
    public void testToLowerCamelCase() {
        assertEquals("todoList", CommonUtil.toLowerCamelCase("todo_list"));
        assertEquals("todoList", CommonUtil.toLowerCamelCase("todo list"));
        assertEquals("todoList", CommonUtil.toLowerCamelCase("TodoList"));
        assertEquals("todoList", CommonUtil.toLowerCamelCase("todoList"));
    }
    
    @Test
    public void testCreateCopyright() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yy/MM/dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        String date = sdf1.format(new Date());
        String year = sdf2.format(new Date());
        String expected =
                "//\n" +
                "// TbTodoList.h\n" +
                "// TodoList\n" +
                "//\n" +
                "// Created by Keita Tsutsui on " + date + ".\n" +
                "// Copyright " + year + " keitanxkeitan. All rights reserved.\n" +
                "//";
        String actual = CommonUtil.createCopyright("TbTodoList.h", "TodoList", "Keita Tsutsui", "keitanxkeitan");
        assertEquals(expected, actual);
    }

}
