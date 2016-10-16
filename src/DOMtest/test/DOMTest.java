package DOMtest.test;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by james on 2016/10/2.
 * 应用DOM方式解析XML
 */
public class DOMTest {

    /**
     * 解析xml文件
     */
    public static void domXmlParser(){
        // 创建一个DocumentBuilderFactory的对象
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // 创建一个DocumentBuilder的对象
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 通过DocumentBuilder对象的parser方法加载book.xml文件到当前项目下
            Document document = db.parse("book.xml");
            // 获取所有book节点的集合
            NodeList bookList = document.getElementsByTagName("book");
            // 遍历每一个book节点
            for (int i = 0; i < bookList.getLength(); i++){
                System.out.println("==========下面开始遍历第" + (i + 1) + "本书的内容==========");
                // 通过item(i)方法获取一个book节点，nodeList的索引值从0开始
                Node book = bookList.item(i);
                // 获取book节点的所有属性集合
                NamedNodeMap attrs = book.getAttributes();
                System.out.println("第" + (i+1) + "本书共有" + attrs.getLength() + "个属性");
                // 遍历book的属性
                for (int j = 0; j < attrs.getLength(); j++){
                    // 通过item(index)方法获取book节点的某一个属性
                    Node attr = attrs.item(j);
                    // 获取属性名
                    System.out.print("属性名： " + attr.getNodeName());
                    // 获取属性值
                    System.out.println("--属性值" + attr.getNodeValue());
                }

//                // 前提：已经知道book节点有且只有1个id属性
//                // 将book节点进行强制类型转换，转换成Element类型
//                Element book = (Element) bookList.item(i);
//                // 通过getAttribute("id")方法获取属性值
//                String attrValue = book.getAttribute("id");
//                System.out.println("id属性的属性值为" + attrValue);

                // 解析book节点的子节点
                NodeList childNodes = book.getChildNodes();
                // 遍历childNodes获取每个节点的节点名和节点值
                System.out.println("第" + (i + 1) + "本书共有" +
                        childNodes.getLength() + "个子节点");
                for (int k = 0; k < childNodes.getLength(); k++){
                    // 区分出text类型的node以及element类型的node
                    if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                        // 获取Element类型节点的节点名
                        System.out.print("第" + (k + 1) + "个节点的节点名：" +
                                childNodes.item(k).getNodeName());
                        // 获取Element类型节点的节点值
                        System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                        //System.out.println("--节点值是：" + childNodes.item(k).getTextContent());
                    }
                }
                System.out.println("==========结束遍历第" + (i + 1) + "本书的内容==========");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //创建DOMTest对象
        DOMTest test = new DOMTest();
        //调用解析方法，解析xml文件
        test.domXmlParser();
    }
}
