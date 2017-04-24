package com.mmp.cq.utils;

import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.BeanWrapperImpl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlUtils {
    
    public static <T> T toObject(String xml ,Class<T> clazz){
        xstream.alias(xml, clazz);
        xstream.registerConverter(messageConverer);
        return (T)xstream.fromXML(xml);
    }
     
    
    public static String toXML(Object obj, Map<String ,Class> alias){
        xstream.alias("xml", obj.getClass());
        if(null != alias){
            Set<Entry<String,Class>> entrySet = alias.entrySet();
            for(Entry<String,Class> entry: entrySet){
                xstream.alias(entry.getKey(), entry.getValue());
            }
        }
        return xstream.toXML(obj);
    }
    
   public static XStream xstream = new XStream(new XppDriver() {  
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
    
   public static Converter messageConverer = new Converter() {
    
    @Override
    public boolean canConvert(Class type) {
        return true;
    }
    
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        BeanWrapperImpl beanWraper = new BeanWrapperImpl(context.getRequiredType());
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            try {
                beanWraper.setPropertyValue(reader.getNodeName(), reader.getValue());
            }
            catch (Exception e) {
                // ignore
            }
            reader.moveUp();
        }
        return beanWraper.getWrappedInstance();
    }
    
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        
    }
}; 
    
}
