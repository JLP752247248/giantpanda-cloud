/**
 * $RCSfile: XMLProperties.java,v $
 * $Revision: 1.1.1.1 $
 * $Date: 2002/09/09 13:51:08 $
 *
 * New Jive  from Jdon.com.
 *
 * This software is the proprietary information of CoolServlets, Inc.
 * Use is subject to license terms.
 */

package com.couldtec.webserver.apps.init.xml;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides the the ability to use simple XML property files. Each property is
 * in the form X.Y.Z, which would map to an XML snippet of:
 * 
 * <pre>
 *          &lt;X&gt;
 *              &lt;Y&gt;
 *                  &lt;Z&gt;someValue&lt;/Z&gt;
 *              &lt;/Y&gt;
 *          &lt;/X&gt;
 * </pre>
 * 
 * The XML file is passed in to the constructor and must be readable and
 * writtable. Setting property values will automatically persist those value to
 * disk.
 */
public class XMLProperties {
	private static final Logger m_logger = LoggerFactory
			.getLogger(XMLProperties.class);

	private File file;

	private Document doc;

	private Element root;

	// private Element element;

	/**
	 * Parsing the XML file every time we need a property is slow. Therefore, we
	 * use a Map to cache property values that are accessed more than once.
	 */
	private Map<String, String> propertyCache = new HashMap<String, String>();

	/**
	 * Creates a new XMLProperties object.
	 * 
	 * @param file
	 *            the full path the file that properties should be read from and
	 *            written to.
	 */
	public XMLProperties(String file) {
		this.file = new File(file);

		try {
			SAXBuilder builder = new SAXBuilder();
			// Strip formatting
			DataUnformatFilter format = new DataUnformatFilter();
			builder.setXMLFilter(format);
			doc = builder.build(new File(file));
			root = doc.getRootElement();
		} catch (Exception e) {
			m_logger.error("Error creating XML parser in "
					+ "PropertyManager.java");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the value of the specified property.
	 * 
	 * @param name
	 *            the name of the property to get.
	 * @return the value of the specified property.
	 */
	public String getProperty(String name) {
		String[] propName = parsePropertyName(name);
		// Search for this property by traversing down the XML heirarchy.
		Element element = doc.getRootElement();
		for (int i = 0; i < propName.length; i++) {
			element = element.getChild(propName[i]);
			if (element == null) {
				// This node doesn't match this part of the property name which
				// indicates this property doesn't exist so return null.
				return null;
			}
		}
		// At this point, we found a matching property, so return its value.
		// Empty strings are returned as null.
		String value = element.getText();

		if ("".equals(value)) {
			return null;
		} else {
			// Add to cache so that getting property next time is fast.
			value = value.trim();
			propertyCache.put(name, value);

			return value;
		}
	}

	/**
	 * Return all children property names of a parent property as a String
	 * array, or an empty array if the if there are no children. For example,
	 * given the properties <tt>X.Y.A</tt>, <tt>X.Y.B</tt>, and <tt>X.Y.C</tt>,
	 * then the child properties of <tt>X.Y</tt> are <tt>A</tt>, <tt>B</tt>, and
	 * <tt>C</tt>.
	 * 
	 * @param parent
	 *            the name of the parent property.
	 * @return all child property values for the given parent.
	 */
	@SuppressWarnings("rawtypes")
	public String[] getChildrenProperties(String parent) {
		String[] propName = parsePropertyName(parent);
		// Search for this property by traversing down the XML heirarchy.
		Element element = doc.getRootElement();

		for (int i = 0; i < propName.length; i++) {
			element = element.getChild(propName[i]);

			if (element == null) {
				// This node doesn't match this part of the property name which
				// indicates this property doesn't exist so return empty array.
				return new String[] {};
			}
		}
		// We found matching property, return names of children.
		List children = element.getChildren();
		int childCount = children.size();
		String[] childrenNames = new String[childCount];

		for (int i = 0; i < childCount; i++) {
			childrenNames[i] = ((Element) children.get(i)).getName();
		}

		return childrenNames;
	}

	/**
	 * Sets the value of the specified property. If the property doesn't
	 * currently exist, it will be automatically created.
	 * 
	 * @param name
	 *            the name of the property to set.
	 * @param value
	 *            the new value for the property.
	 */
	public void setProperty(String name, String value) {
		// Set cache correctly with prop name and value.
		propertyCache.put(name, value);

		String[] propName = parsePropertyName(name);
		// Search for this property by traversing down the XML heirarchy.
		Element element = doc.getRootElement();

		for (int i = 0; i < propName.length; i++) {
			// If we don't find this part of the property in the XML heirarchy
			// we add it as a new node
			if (element.getChild(propName[i]) == null) {
				element.addContent(new Element(propName[i]));
			}

			element = element.getChild(propName[i]);
		}
		// Set the value of the property in this node.
		element.setText(value);
		// write the XML properties to disk
		saveProperties();
	}

	/**
	 * Deletes the specified property.
	 * 
	 * @param name
	 *            the property to delete.
	 */
	public void deleteProperty(String name) {
		String[] propName = parsePropertyName(name);
		// Search for this property by traversing down the XML heirarchy.
		Element element = doc.getRootElement();

		for (int i = 0; i < propName.length - 1; i++) {
			element = element.getChild(propName[i]);
			// Can't find the property so return.
			if (element == null) {
				return;
			}
		}
		// Found the correct element to remove, so remove it...
		element.removeChild(propName[propName.length - 1]);
		// .. then write to disk.
		saveProperties();
	}

	/**
	 * Saves the properties to disk as an XML document. A temporary file is used
	 * during the writing process for maximum safety.
	 */
	private synchronized void saveProperties() {
		OutputStream out = null;
		boolean error = false;
		// Write data out to a temporary file first.
		File tempFile = null;

		try {
			tempFile = new File(file.getParentFile(), file.getName() + ".tmp");
			// Use JDOM's XMLOutputter to do the writing and formatting. The
			// file should always come out pretty-printed.
			XMLOutputter outputter = new XMLOutputter();
			out = new BufferedOutputStream(new FileOutputStream(tempFile));
			outputter.output(doc, out);
		} catch (Exception e) {
			e.printStackTrace();
			// There were errors so abort replacing the old property file.
			error = true;
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				error = true;
			}
		}
		// No errors occured, so we should be safe in replacing the old
		if (!error) {
			// Delete the old file so we can replace it.
			file.delete();
			// Rename the temp file. The delete and rename won't be an
			// automic operation, but we should be pretty safe in general.
			// At the very least, the temp file should remain in some form.
			tempFile.renameTo(file);
		}
	}

	/**
	 * Returns an array representation of the given Jive property. Jive
	 * properties are always in the format "prop.name.is.this" which would be
	 * represented as an array of four Strings.
	 * 
	 * @param name
	 *            the name of the Jive property.
	 * @return an array representation of the given Jive property.
	 */
	private String[] parsePropertyName(String name) {
		// Figure out the number of parts of the name (this becomes the size
		// of the resulting array).
		int size = 1;
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == '.') {
				size++;
			}
		}
		String[] propName = new String[size];
		// Use a StringTokenizer to tokenize the property name.
		StringTokenizer tokenizer = new StringTokenizer(name, ".");
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			propName[i] = tokenizer.nextToken();
			i++;
		}
		return propName;
	}

	/**
	 * Return XML File Document Object
	 * 
	 * @return Document
	 */
	public Document getDocument() {
		return doc;
	}

	/**
	 * Return Document Root Element
	 * 
	 * @return Element
	 */
	public Element getRootElement() {
		return root;
	}

	/***************************************************************************
	 * XPATH基础
	 * XPATH遵循文档对象模型(DOM)的路径格式，基本语法由表达式构成。在计算表达式的值之后产生一个对象，这种对象有以下四种基本类型
	 * 节点集合、布尔型、数字型和字符串型。XPATH基本上和在文件系统中寻找文件类似，如果路径是以"/"开头的，就表明该路径表示的是一个绝对路径，
	 * 这和在UNIX系统中关于文件路径的定义是一致的。以"//"开头 则表示在文档中的任意位置查找。
	 * 
	 * 以样例XML文档（friends.xml）为例来了解XPATH <?xml version="1.0" encoding="UTF-8"?>
	 * <friends comment="Friends List"> <friend number="1"> <name>zoof</name>
	 * <sex value="male" /> <phone>87654321</phone> </friend> <friend
	 * number="2"> <name>joe</name> <sex value="male" /> <phone>87654322</phone>
	 * </friend> <friend number="3"> <name>joe</name> <sex value="female" />
	 * <phone>87654323</phone> </friend> </friends>
	 * 
	 * 在XML文档中使用位置路径表达式来查找信息，这些表达式有很多种组成方式。一般我们用得最多的恐怕是 节点元素
	 * 查找。XPATH中用正斜杠（/）来分隔子结点，返回所有与 模式相匹配的元素。下面以几个 表达式 的例子来说明一下返回结果
	 * 表达式/friends/friend 返回根元素friends下所有的 friend 元素（或节点）。
	 * 
	 * 表达式/friends/* 返回根元素friends下所有的元素（或节点）。（“*”相当于通配符，表示“所有”的）
	 * 
	 * 表达式//friend 返回任意元素（或节点）下的所有 friend 元素（或节点）。（注意不仅仅是1中
	 * 根元素friends下面的friend元素,如果存在的话）
	 * 
	 * 表达式/friends/friend[@number='1'] 返回根元素下元素名称为
	 * friend，number属性为'1'的全部元素（或节点）。（对于元素或节点的附加元素，比如属性，函数等都要用方括号"[]"扩起来，属性前面要加
	 * 上"@"号）
	 * 
	 * 表达式/friends/friend/phone[text()='87654321'] 返回元素friends/friend下电话号码为
	 * 87654321 的全部元素。（text()是XPATH的函数，功能是取出当前节点的文本内容，即content。）
	 * 
	 * 表达式//name/parent::* 返回name元素的所有父元素（或节点）。(parent::* 表示这个元素的所有的父节点的集合)
	 * 
	 * 上面的介绍对于我们一般的应用基本上够用了，如果你需要进一步的深入，请查看W3C发布的关于XAPH的官方资料。
	 * 
	 * 
	 * 现在我们开始使用XPATH结合JDOM来操作XML文件了。JDOM的关于XPATH的api主要有一个类Xpath.java，在org.jdom.
	 * xpath这个包中。这个类中的核心方法主要是两个静态方法selectNodes()和selectSingleNode()。
	 * 前者根据一个xpath语句返回一组节点:List；后者根据一个xpath语句返回符合条件的第一个节点:Object。 public static
	 * List selectNodes(Object arg1,String arg2) throws org.jdom.JDOMException;
	 * public static Object selectSingleNode(Object arg1,String arg2) throws
	 * org.jdom.JDOMException;
	 * 
	 * 在使用XPATH之前，请先确定你的classpath路径里面有如下几个JAR包，如果没有，请从JDOM的发行包中lib目录下copy:
	 * saxpaht.jar jaxen-core.jar jaxen-jdom.jar
	 **************************************************************************/

	/**
	 * 根据XPATH地址路径获得所有满足条件的对象
	 * 
	 * @param xPath
	 * 
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List selectNodes(String xPath) {
		List list = new ArrayList();
		list.clear();

		try {
			list = XPath.selectNodes(root, xPath);
		} catch (JDOMException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 根据XPATH地址路径获得所有满足条件的对象
	 * 
	 * @param xPath
	 * 
	 * @return Element
	 */
	public Element selectSingleNode(String xPath) {
		Element element = null;

		try {
			element = (Element) XPath.selectSingleNode(root, xPath);
		} catch (JDOMException e) {
			e.printStackTrace();
		}

		return element;
	}

	/**
	 * 根据节点名称获得节点值
	 * 
	 * @param element
	 * @param name
	 * @return String
	 */
	public String getText(Element element, String name) {
		name = element.getText();

		return name;
	}

	/**
	 * 根据属性名称获得属性值
	 * 
	 * @param element
	 * @param name
	 * @return String
	 */
	public String getAttributeValue(Element element, String name) {
		name = element.getAttributeValue(name);

		return name;
	}
}