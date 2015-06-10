package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

public class XMLHandler {
	public XMLHandler() {
		// TODO Auto-generated constructor stub
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
//			file.delete();
		}
		try {
			file.createNewFile();
//			XMLWriter out = new XMLWriter(new FileWriter(file));
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

	
	/**
     * 利用dom4j进行xml文档的写入操作
     */
    public void createXml(File file) {

        // XML 声明 <?xml version="1.0" encoding="UTF-8"?> 自动添加到 XML文档中

        // 使用DocumentHelper类创建文档实例(生成 XML文档节点的 dom4j API工厂类)
        Document document = DocumentHelper.createDocument();

        // 使用addElement()方法创建根元素 employees(用于向 XML 文档中增加元素)
        Element root = document.addElement("employees");

        // 在根元素中使用 addComment()方法添加注释"An XML Note"
        root.addComment("An XML Note");

        // 在根元素中使用 addProcessingInstruction()方法增加一个处理指令
        root.addProcessingInstruction("target", "text");

        // 在根元素中使用 addElement()方法增加employee元素。
        Element empElem = root.addElement("employee");

        // 使用 addAttribute()方法向employee元素添加id和name属性
        empElem.addAttribute("id", "0001");
        empElem.addAttribute("name", "wanglp");

        // 向employee元素中添加sex元素
        Element sexElem = empElem.addElement("sex");
        // 使用setText()方法设置sex元素的文本
        sexElem.setText("m");

        // 在employee元素中增加age元素 并设置该元素的文本。
        Element ageElem = empElem.addElement("age");
        ageElem.setText("25");

        // 在根元素中使用 addElement()方法增加employee元素。
        Element emp2Elem = root.addElement("employee");

        // 使用 addAttribute()方法向employee元素添加id和name属性
        emp2Elem.addAttribute("id", "0002");
        emp2Elem.addAttribute("name", "fox");

        // 向employee元素中添加sex元素
        Element sex2Elem = emp2Elem.addElement("sex");
        // 使用setText()方法设置sex元素的文本
        sex2Elem.setText("f");

        // 在employee元素中增加age元素 并设置该元素的文本。
        Element age2Elem = emp2Elem.addElement("age");
        age2Elem.setText("24");

        // 可以使用 addDocType()方法添加文档类型说明。
        // document.addDocType("employees", null, "file://E:/Dtds/dom4j.dtd");
        // 这样就向 XML 文档中增加文档类型说明：
        // <!DOCTYPE employees SYSTEM "file://E:/Dtds/dom4j.dtd">
        // 如果文档要使用文档类型定义（DTD）文档验证则必须有 Doctype。

        try {
            XMLWriter output = new XMLWriter(new FileWriter(file));
            output.write(document);
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
	
    public static void addXML(String filePath){
    	String strXML = null;
    	SAXReader reader = new SAXReader();
    	StringReader stringReader = new StringReader(filePath);
    	InputSource is = new InputSource(stringReader);
    	try {
    		Document document = reader.read(new FileInputStream(new File("TelePhone.xml")));
    		Element root = document.getRootElement();
    		
    		Element neweElement = root.element("TelePhone");
    		Element nokia = neweElement.addElement("type");
    		nokia.addAttribute("name", "nokia12");
    		Element price_nokia = nokia.addElement("price");
    		price_nokia.addText("599123");
    		Element operator_nokia = nokia.addElement("operator");
    		operator_nokia.addText("CMCC123");
    		
    		///
    		StringWriter strWtr = new StringWriter();
    		OutputFormat format = OutputFormat.createPrettyPrint();
    		format.setEncoding("UTF-8");
    		XMLWriter xmlWriter = new XMLWriter(strWtr, format);
    		//
    		xmlWriter.write(document);
    		////////
    		File file = new File("TelePhone.xml");
    		if (file.exists()) {
//    			file.delete();
    		}
    			file.createNewFile();
    			XMLWriter out = new XMLWriter(new FileWriter(file));
    			out.write(document);
    			out.flush();
    			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
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

	   public static boolean addXML(String filePath,HashMap<String, String> map){
	    	String strXML = null;
	    	if(filePath == null || filePath.equals("")) return false;
	    	File tempFile = new File(filePath);
	    	
	    	Document document = null;
	    	Element phone = null;
	    	Element root = null;
	    	Element number = null;
	    	Element deviceID = null;
	    	Element regID  = null;
	    	boolean isSame = false;
	    	
	    	if(tempFile.exists()){
	    		//存在就添加
		    	try {
		    		System.out.println("ex----------------");
		    		SAXReader reader = new SAXReader();
		    		document = reader.read(new FileInputStream(new File(filePath)));
		    		root = document.getRootElement();
		    		//
		    		List<Element> elements = root.elements("Device");
		    		//number 
		    		for(Element element : elements){
		    			// deviceID ,regID
		    			List<Element> childList = element.elements();
		    			for(Element element2 : childList){
		    				//0 -> deviceID ,1 ->regID
		    				List<Element> elementChild = element2.elements();
		    				for(int i = 0;i<elementChild.size(); i++ ){
		    					String device = elementChild.get(i).getText();
		    					if(!device.equals("") && device.equals(map.get("deviceID"))){
		    						System.out.println("change the valuye");
		    						elementChild.get(i+1).setText(map.get("regID"));
		    						isSame = true;
		    					};
		    				}
		    			}
		    		}
		    		if(!isSame){
		    			System.out.println("add-------------");
		    			phone = root.addElement("Device");
		    			number = phone.addElement("number");
		    			number.addAttribute("id", elements.size()+1+"");
		    			deviceID = number.addElement("deviceID");
		    			deviceID.addText(map.get("deviceID"));
		    			regID = number.addElement("regID");
		    			regID.addText(map.get("regID"));
		    		}
		    		
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}else {
				//不存在就新建
	    		System.out.println("****************");
	    		document = DocumentHelper.createDocument();
	    		root = document.addElement("Devices");
	    		phone = root.addElement("Device");
	    		number = phone.addElement("number");
//	    		Element phone = root.addElement("Device");
//	    		Element number = phone.addElement("number");
//	    		Element deviceID = null;
//	    		Element regID = null;
	    		number.addAttribute("id", 1 + "");
	    		deviceID = number.addElement("deviceID");
	    		deviceID.addText(map.get("deviceID"));
	    		regID = number.addElement("regID");
	    		regID.addText(map.get("regID"));
			}
	    	
   		try {
   	    	///
       		StringWriter strWtr = new StringWriter();
       		OutputFormat format = OutputFormat.createPrettyPrint();
       		format.setEncoding("UTF-8");
       		XMLWriter xmlWriter = new XMLWriter(strWtr, format);
				xmlWriter.write(document);
	    		File file = new File(filePath);
	    		if (file.exists()) {
//	    			file.delete();
	    		}
	    			file.createNewFile();
	    			XMLWriter out = new XMLWriter(new FileWriter(file));
	    			out.write(document);
	    			out.flush();
	    			out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	return true;
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
//		XMLHandler handler = new XMLHandler();
//		String strXML = handler.createXML();
//		System.out.println(strXML);
////		handler.parserXML(strXML);
//		System.out.println("-----------");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("deviceID", "");
		map.put("regID", "8888888888888888888");
		//
//		map.put("deviceID", "001");
//		map.put("regID", "100");
		
		addXML("test.xml",map);
		System.out.println("-------------ok");
		
		
//		handler.parserXMLbyXPath(strXML);
	}
}
