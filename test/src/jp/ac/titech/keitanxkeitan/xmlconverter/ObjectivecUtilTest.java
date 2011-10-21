package jp.ac.titech.keitanxkeitan.xmlconverter;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ObjectivecUtilTest {

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
    public void testCreateImportSentence() {
        String expected;
        String actual;

        expected = "#import <Foundation/Foundation.h>";
        actual = ObjectivecUtil.createImportSentence("Foundation/Foundation.h", true);
        assertEquals(expected, actual);

        expected = "#import \"TbTodo.h\"";
        actual = ObjectivecUtil.createImportSentence("TbTodo.h", false);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToDataTypeStringWithSpace() {
        String expected;
        String actual;
        
        expected = "";
        actual = ObjectivecUtil.toDataTypeStringWithSpace(DataType.NULL);
        assertEquals(expected, actual);
        
        expected = "int ";
        actual = ObjectivecUtil.toDataTypeStringWithSpace(DataType.INTEGER);
        assertEquals(expected, actual);
        
        expected = "float ";
        actual = ObjectivecUtil.toDataTypeStringWithSpace(DataType.REAL);
        assertEquals(expected, actual);
        
        expected = "NSString *";
        actual = ObjectivecUtil.toDataTypeStringWithSpace(DataType.TEXT);
        assertEquals(expected, actual);
        
        expected = "";
        actual = ObjectivecUtil.toDataTypeStringWithSpace(DataType.BLOB);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCreateVariableDeclaration() {
        String expected;
        String actual;
        
        expected = "int hoge";
        actual = ObjectivecUtil.createVariableDeclaration(DataType.INTEGER, "hoge");
        assertEquals(expected, actual);
        
        expected = "float foo";
        actual = ObjectivecUtil.createVariableDeclaration(DataType.REAL, "foo");
        assertEquals(expected, actual);
        
        expected = "NSString *bar";
        actual = ObjectivecUtil.createVariableDeclaration(DataType.TEXT, "bar");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCreateVariableDeclarationStatement() {
        String expected;
        String actual;
        
        expected = "int hoge;";
        actual = ObjectivecUtil.createVariableDeclarationStatement(DataType.INTEGER, "hoge");
        assertEquals(expected, actual);
        
        expected = "float foo;";
        actual = ObjectivecUtil.createVariableDeclarationStatement(DataType.REAL, "foo");
        assertEquals(expected, actual);
        
        expected = "NSString *bar;";
        actual = ObjectivecUtil.createVariableDeclarationStatement(DataType.TEXT, "bar");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToClassMemberVariableName() {
        String expected;
        String actual;
        
        expected = "hogeFoo_";
        actual = ObjectivecUtil.toClassMemberVariableName("hoge_foo");
        assertEquals(expected, actual);
    }

}
