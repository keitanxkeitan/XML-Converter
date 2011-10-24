package jp.ac.titech.keitanxkeitan.xmlconverter;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ObjcUtilTest {

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
        actual = ObjcUtil.createImportSentence("Foundation/Foundation.h", true);
        assertEquals(expected, actual);

        expected = "#import \"TbTodo.h\"";
        actual = ObjcUtil.createImportSentence("TbTodo.h", false);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToDataTypeString() {
        String expected;
        String actual;
        
        expected = "";
        actual = ObjcUtil.toDataTypeString(DataType.NULL);
        assertEquals(expected, actual);
        
        expected = "int";
        actual = ObjcUtil.toDataTypeString(DataType.INTEGER);
        assertEquals(expected, actual);
        
        expected = "float";
        actual = ObjcUtil.toDataTypeString(DataType.REAL);
        assertEquals(expected, actual);
        
        expected = "NSString *";
        actual = ObjcUtil.toDataTypeString(DataType.TEXT);
        assertEquals(expected, actual);
        
        expected = "";
        actual = ObjcUtil.toDataTypeString(DataType.BLOB);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCreateVariableDeclaration() {
        String expected;
        String actual;
        
        expected = "int hoge";
        actual = ObjcUtil.createVariableDeclaration(DataType.INTEGER, "hoge");
        assertEquals(expected, actual);
        
        expected = "float foo";
        actual = ObjcUtil.createVariableDeclaration(DataType.REAL, "foo");
        assertEquals(expected, actual);
        
        expected = "NSString *bar";
        actual = ObjcUtil.createVariableDeclaration(DataType.TEXT, "bar");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCreateVariableDeclarationStatement() {
        String expected;
        String actual;
        
        expected = "int hoge;";
        actual = ObjcUtil.createVariableDeclarationStatement(DataType.INTEGER, "hoge");
        assertEquals(expected, actual);
        
        expected = "float foo;";
        actual = ObjcUtil.createVariableDeclarationStatement(DataType.REAL, "foo");
        assertEquals(expected, actual);
        
        expected = "NSString *bar;";
        actual = ObjcUtil.createVariableDeclarationStatement(DataType.TEXT, "bar");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToClassMemberVariableName() {
        String expected;
        String actual;
        
        expected = "hogeFoo_";
        actual = ObjcUtil.toClassMemberVariableName("hoge_foo");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCreateProperty() {
        String expected;
        String actual;
        
        expected = "@property (nonatomic, assign) int hoge;";
        actual = ObjcUtil.createProperty(DataType.INTEGER, "hoge");
        assertEquals(expected, actual);
        
        expected = "@property (nonatomic, assign) float foo;";
        actual = ObjcUtil.createProperty(DataType.REAL, "foo");
        assertEquals(expected, actual);
        
        expected = "@property (nonatomic, copy) NSString *bar;";
        actual = ObjcUtil.createProperty(DataType.TEXT, "bar");
        assertEquals(expected, actual);
    }

}
