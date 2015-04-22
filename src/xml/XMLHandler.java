package xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
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
		XMLHandler handler = new XMLHandler();
		String strXML = handler.createXML();
		System.out.println(strXML);
		handler.parserXML(strXML);
		System.out.println("-----------");
//		handler.parserXMLbyXPath(strXML);
	}
}
