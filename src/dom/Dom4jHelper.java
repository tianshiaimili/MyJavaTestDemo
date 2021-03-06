package dom;

/**
 * 
 */
//package com.gootrip.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

/**
 * @author advance
 *
 */
public class Dom4jHelper {
	/**
	 *  解析url xml文档
	 * @param url
	 * @return
	 * @throws DocumentException
	 */
    public Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }
    /**
     * 遍历解析文档
     * @param document
     */
    public void treeWalk(Document document) {
        treeWalk( document.getRootElement() );
    }
    /**
     * 遍历解析元素
     * @param element
     */
    public void treeWalk(Element element) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
                treeWalk( (Element) node );
            }
            else {
                // 处理....
            }
        }
    }

	/** 
	 * 解析文件，获得根元素
	 * @param xmlPath
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static Element parse(String xmlPath,String encoding)throws Exception{
		//文件是否存在
		File file = new File(xmlPath);
        if(!file.exists()){
        	throw new Exception("找不到xml文件："+xmlPath);
        }
        
		//解析
		SAXReader reader = new SAXReader(false);
		Document doc = reader.read(new FileInputStream(file),encoding);
		Element root = doc.getRootElement();
		return root;
	}
	
	/**
	 * 保存文档
	 * @param doc
	 * @param xmlPath
	 * @param encoding
	 * @throws Exception
	 */
	public static void save(Document doc,String xmlPath,String encoding)throws Exception{
		OutputFormat format=OutputFormat.createPrettyPrint();
	    format.setEncoding(encoding);
	    XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlPath),encoding),format);
		writer.write(doc);
		writer.flush();
		writer.close();	
	}
	/**
	 * 修改xml某节点的值
	 * @param inputXml 原xml文件
	 * @param nodes 要修改的节点
	 * @param attributename 属性名称
	 * @param value 新值
	 * @param outXml 输出文件路径及文件名 如果输出文件为null，则默认为原xml文件
	 */
	public static void modifyDocument(File inputXml, String nodes, String attributename, String value, String outXml) {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputXml);
			List list = document.selectNodes(nodes);
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Attribute attribute = (Attribute) iter.next();
				if (attribute.getName().equals(attributename))
					attribute.setValue(value);
			}
			XMLWriter output;
			if (outXml != null){ //指定输出文件
				output = new XMLWriter(new FileWriter(new File(outXml)));
			}else{ //输出文件为原文件
				output = new XMLWriter(new FileWriter(inputXml));
			}
			output.write(document);
			output.close();
		}

		catch (DocumentException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}	
	
	/**
	 * xml转换为字符串
	 * @param doc
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String toString(Document doc,String encoding)throws Exception{
		OutputFormat format=OutputFormat.createPrettyPrint();
	    format.setEncoding(encoding);
	    ByteArrayOutputStream byteOS=new ByteArrayOutputStream();
	    XMLWriter writer = new XMLWriter(new OutputStreamWriter(byteOS,encoding),format);
		writer.write(doc);
		writer.flush();
		writer.close();		
		writer=null;
		
		return byteOS.toString(encoding);
	}
	/**
	 * 字符串转换为Document
	 * @param text
	 * @return
	 * @throws DocumentException
	 */
	public static Document str2Document(String text) throws DocumentException{
		Document document = DocumentHelper.parseText(text);
        return document;
	}
	
	
	public String createXML() {
		String strXML = null;
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");

		Element phone = root.addElement("TelePhone");

		Element nokia = phone.addElement("type");
		nokia.addAttribute("name", "nokia");
		Element price_nokia = nokia.addElement("price");
		price_nokia.addText("599");
		Element operator_nokia = nokia.addElement("operator");
		operator_nokia.addText("CMCC");

		Element xiaomi = phone.addElement("type");
		xiaomi.addAttribute("name", "xiaomi");
		Element price_xiaomi = xiaomi.addElement("price");
		price_xiaomi.addText("699");
		Element operator_xiaomi = xiaomi.addElement("operator");
		operator_xiaomi.addText("ChinaNet");

		// --------
		StringWriter strWtr = new StringWriter();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter xmlWriter = new XMLWriter(strWtr, format);
		try {
			xmlWriter.write(document);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		strXML = strWtr.toString();
		// --------

		// -------
		// strXML=document.asXML();
		// ------

		// -------------
		File file = new File("TelePhone.xml");
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			XMLWriter out = new XMLWriter(new FileWriter(file));
			out.write(document);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// --------------

		return strXML;
	}

	public void parserXML(String strXML) {
		SAXReader reader = new SAXReader();
		StringReader sr = new StringReader(strXML);
		InputSource is = new InputSource(sr);
		try {
			Document document = reader.read(is);

			Element root = document.getRootElement();

			// get element
			List<Element> phoneList = root.elements("TelePhone");
			List<Element> typeList = phoneList.get(0).elements("type");
			for (int i = 0; i < typeList.size(); i++) {
				Element element = typeList.get(i);
				String phoneName = element.attributeValue("name");
				System.out.println("phonename = " + phoneName);
				// get all element
				List<Element> childList = element.elements();
				for (int j = 0; j < childList.size(); j++) {
					Element e = childList.get(j);
					System.out.println(e.getName() + "=" + e.getText());
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void parserXMLbyXPath(String strXML) {
		SAXReader reader = new SAXReader();
		StringReader sr = new StringReader(strXML);
		InputSource is = new InputSource(sr);
		try {
			Document document = reader.read(is);
			List list = document.selectNodes("/root/TelePhone/type");
			for (int i = 0; i < list.size(); i++) {
				Element e = (Element) list.get(i);
				System.out.println("phonename=" + e.attributeValue("name"));
				List list1 = e.selectNodes("./*");
				for (int j = 0; j < list1.size(); j++) {
					Element e1 = (Element) list1.get(j);
					System.out.println(e1.getName() + "=" + e1.getText());
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
