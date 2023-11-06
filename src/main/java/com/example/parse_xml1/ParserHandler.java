package com.example.parse_xml1;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ParserHandler extends DefaultHandler {

    @Getter
    private List<AddressObjectDTO> addressObjects;
    private AddressObjectDTO currentAddressObject;
    private StringBuilder data;

    @Override
    public void startDocument() throws SAXException {
        addressObjects = new ArrayList<>();
        data = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("OBJECT")) {
            currentAddressObject = new AddressObjectDTO();
            currentAddressObject.setId(attributes.getValue("ID"));
            currentAddressObject.setObjectId(attributes.getValue("OBJECTID"));
            currentAddressObject.setObjectGuid(attributes.getValue("OBJECTGUID"));
            currentAddressObject.setChangeId(attributes.getValue("CHANGEID"));
            currentAddressObject.setName(attributes.getValue("NAME"));
            currentAddressObject.setTypeName(attributes.getValue("TYPENAME"));
            currentAddressObject.setLevel(attributes.getValue("LEVEL"));
            currentAddressObject.setOperTypeId(attributes.getValue("OPERTYPEID"));
            currentAddressObject.setPrevId(attributes.getValue("PREVID"));
            currentAddressObject.setNextId(attributes.getValue("NEXTID"));
            currentAddressObject.setUpdateDate(attributes.getValue("UPDATEDATE"));
            currentAddressObject.setStartDate(attributes.getValue("STARTDATE"));
            currentAddressObject.setEndDate(attributes.getValue("ENDDATE"));
            currentAddressObject.setIsActual(attributes.getValue("ISACTUAL"));
            currentAddressObject.setIsActive(attributes.getValue("ISACTIVE"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("OBJECT")) {
            addressObjects.add(currentAddressObject);
            currentAddressObject = null;
        } else if (qName.equalsIgnoreCase("ADDRESSOBJECTS")) {

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}