package org.example.nminexcel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Parser {
    private static NMinStorage nMinStorage;

    public static Optional<Integer> parse(int n, String filepath)
        throws OpenXML4JException, IOException, SAXException {
        init(n);

        try(OPCPackage opcPackage = OPCPackage.open(filepath)){
            XSSFReader xssfReader = new XSSFReader(opcPackage);

            XMLReader parser = SAXParserFactory.newDefaultInstance().newSAXParser().getXMLReader();
            parser.setContentHandler(new ExcelIntListHandler());

            InputStream sheet = xssfReader.getSheetsData().next();

            parser.parse(new InputSource(sheet));
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        return nMinStorage.getNthMin();
    }

    private static void init(int n){
        nMinStorage = new NMinStorage(n);
    }

    private static class ExcelIntListHandler extends DefaultHandler {
        private boolean isRightColumn;
        private String content;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes){
            if("c".equals(qName)){
                String columnName = attributes.getValue("r");
                isRightColumn = columnName.matches("A\\d+");
            }
            content = "";
        }

        @Override
        public void endElement(String uri, String localName, String qName){
            if(isRightColumn && "v".equals(qName) && content.matches("^-?\\d+$")){
                nMinStorage.push(Integer.parseInt(content));
            }
        }

        @Override
        public void characters(char[] chars, int start, int length){
            content += new String(chars, start, length);
        }
    }
}
