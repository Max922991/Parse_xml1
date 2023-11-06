package com.example.parse_xml1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;


@SpringBootApplication
public class ParseXml1Application {

    public static void main(String[] args) {
        SpringApplication.run(ParseXml1Application.class, args);

        try {
            File file = new File("src/main/resources/test.XML");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ParserHandler handler = new ParserHandler();
            saxParser.parse(file, handler);

            List<AddressObjectDTO> addressObjects = handler.getAddressObjects();

            DatabaseUploader uploader = new DatabaseUploader();
            uploader.uploadData(addressObjects);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
