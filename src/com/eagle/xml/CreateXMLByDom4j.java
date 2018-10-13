package com.eagle.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CreateXMLByDom4j {

    public static void main(String[] args) {

        //创建一个xml文档对象
        Document document = DocumentHelper.createDocument();
        //向xml文件中添加注释
        document.addComment("这里是注释");
        //创建一个名为students的节点，因为是第一个创建，所以是根节点,再通过doc创建一个则会报错。
        Element root = document.addElement("students");

        for(int i=0; i<10;i++){
            //在root节点下创建一个名为student的节点
            Element childElement = root.addElement("student");
            //给student节点添加属性
            childElement.addAttribute("id",i+"");
            //给student节点添加一个子节点
            Element leafElementOne = childElement.addElement("name");
            //设置name节点的文本
            leafElementOne.setText(String.valueOf(Math.random()));
            //给student节点再添加一个子节点
            Element leafElementTwo = childElement.addElement("age");
            //设置name节点的文本
            leafElementTwo.setText(String.valueOf(Math.random()));
        }

        //用于格式化xml内容和设置头部标签
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("utf-8");
        Writer writer = null;
        XMLWriter xmlWriter = null;
        try{
            //创建一个输出流对象
            writer = new FileWriter("new.xml");
            //创建一个dom4j的XMLWriter对象
            xmlWriter = new XMLWriter(writer, outputFormat);
            xmlWriter.write(document);

        }catch (IOException e){
            System.out.print("生成XML文件失败!");
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                xmlWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.print("生成XML文件成功!");
    }

}
