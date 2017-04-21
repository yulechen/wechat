package com.mmp.cq.xstream.test.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Writer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XStreamTest {

    
    @Test
    public void testObject2Xml(){
        XStream xStream = genStaXStream();
        String xml = xStream.toXML(getStudentDetails());
        System.out.println(formatXml(xml));
    }
    
    @Test
    public void testXML2Object(){
        XStream xStream = genStaXStream();
        String xml = xStream.toXML(getStudentDetails());
        Object fromXML = xStream.fromXML(xml);
        System.out.println(fromXML);
    }
    
    
    @Test
    public void testObject2Xml_alias(){
        XStream xStream = genStaXStream();
        xStream.alias("student", Student.class);
        String xml = xStream.toXML(getStudentDetails());
        System.out.println(formatXml(xml));
    }
    
    @Test
    public void testExtObject2Xml(){
        XStream xStream = genExtXStream();
        xStream.alias("student", Student.class);
        String xml = xStream.toXML(getStudentDetails());
        System.out.println(xml);
    }
    
    
    @Test
    public void testExtXML2Object(){
        XStream xStream = genExtXStream();
        xStream.alias("student", Student.class);
        String xml = xStream.toXML(getStudentDetails());
        System.out.println(xml);
        System.out.println("---------------------------");
        Object object = xStream.fromXML(xml);
        System.out.println(object.getClass());
    }
    
    public XStream genStaXStream(){
       return new XStream(new StaxDriver());
    }
    
    public XStream genExtXStream(){
        return new XStream(new XppDriver() {  
            public HierarchicalStreamWriter createWriter(Writer out) {  
                return new PrettyPrintWriter(out) {  
                    // 对所有xml节点的转换都增加CDATA标记  
                    boolean cdata = true; 
                    @SuppressWarnings("unchecked")  
                    public void startNode(String name, Class clazz) {  
                        super.startNode(name, clazz);  
                    }  
      
                    protected void writeText(QuickWriter writer, String text) {  
                        if (cdata) {  
                            writer.write("<![CDATA[");  
                            writer.write(text);  
                            writer.write("]]>");  
                        } else {  
                            writer.write(text);  
                        }  
                    }  
                };  
            }  
        });  
     }
    
    private Student getStudentDetails(){
        Student student = new Student();
        student.setFirstName("Mahesh");
        student.setLastName("Parashar");
        student.setRollNo(1);
        student.setClassName("1st");

        Address address = new Address();
        
        address.setArea("H.No. 16/3, Preet Vihar.");
        address.setCity("Delhi");
        address.setState("Delhi");
        address.setCountry("India");
        address.setPincode(110012);

        student.setAddress(address);
        
        return student;
     }
    
    public static String formatXml(String xml){
        try{
           Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
           serializer.setOutputProperty(OutputKeys.INDENT, "yes");
           serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
           
           Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
           StreamResult res =  new StreamResult(new ByteArrayOutputStream());            
           
           serializer.transform(xmlSource, res);
           
           return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
           
        }catch(Exception e){
           return xml;
        }
     }
}
