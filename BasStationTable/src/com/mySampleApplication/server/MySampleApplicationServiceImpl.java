package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.shared.Bus;
import com.mySampleApplication.shared.Reqest;
import com.mySampleApplication.shared.Responce;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public Responce myMessage(Reqest reqest) {


        //String xmlPath = "src.com.mySampleApplication.server.BusList.xml";

        ClassLoader classLoader = getClass().getClassLoader();
        String xmlPath = classLoader.getResource("BusList.xml").getFile();

        List<FullBus> busList = new ArrayList<>();

        Responce responce = new Responce();
        List<Bus> respBusList = new ArrayList<>();

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(xmlPath);
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("bus");

            for (int i = 0; i < list.size(); i++) {

                Element node = (Element) list.get(i);

                busList.add(new FullBus(node.getChildText("num"),node.getChildText("firstSt"),
                        node.getChildText("lastSt"),Integer.parseInt(node.getChildText("hour")),
                        Integer.parseInt(node.getChildText("min"))));

            }
        }
        catch (IOException io){
            System.out.println(io.getMessage());
        }
        catch (JDOMException jdomex){
            System.out.println(jdomex.getMessage());
        }


        if(reqest.isRead()){

            if (!busList.isEmpty()){

                switch (reqest.getSortField()){
                    case "name":
                        busList.sort(FullBus.numberComparator);
                        break;
                    case "startStation":
                        busList.sort(FullBus.firstStationComparator);
                        break;
                    case "lastStation":
                        busList.sort(FullBus.lastStationComparator);
                        break;
                    case "time":
                        busList.sort(FullBus.timeComparator);
                        break;
                }

                int index = 0;

                for (FullBus bus: busList) {

                    boolean exist = true;
                    if(reqest.getNumFiltr()!=null&&!bus.getNumber().equalsIgnoreCase(reqest.getNumFiltr())){
                        exist = false;
                    }
                    if(reqest.getStartStationFiltr()!=null&&!bus.getFirstStation().equalsIgnoreCase(reqest.getStartStationFiltr())){
                        exist = false;
                    }
                    if(reqest.getLastStationFiltr()!=null&&!bus.getLastStation().equalsIgnoreCase(reqest.getLastStationFiltr())){
                        exist = false;
                    }
                    if(reqest.getStartTime()!=null&&bus.getTime().before(reqest.getStartTime())){
                        exist = false;
                    }
                    if(reqest.getEndTime()!=null&&bus.getTime().after(reqest.getEndTime())){
                        exist = false;
                    }
                    if(exist&&index>=reqest.getStationsNum()){
                        exist = false;
                    }
                    if(exist){
                        index = index+1;
                        Bus newBus = new Bus(bus.getNumber(),bus.getFirstStation(),bus.getLastStation(),bus.getTime());
                        respBusList.add(newBus);
                    }
                }
            }
        }
        else{

            Bus wBus = reqest.getBus();

            try {

                FileInputStream fis = new FileInputStream(xmlFile);

                SAXBuilder sb = new SAXBuilder();

                Document document = sb.build(fis);

                Element root = document.getRootElement();
                fis.close();

                Element child = new Element("bus");
                child.addContent(new Element("num").setText(wBus.getNumber()));
                child.addContent(new Element("firstSt").setText(wBus.getFirstStation()));
                child.addContent(new Element("lastSt").setText(wBus.getLastStation()));
                child.addContent(new Element("hour").setText("" + wBus.getTime().getH()));
                child.addContent(new Element("min").setText("" + wBus.getTime().getM()));

                root.addContent(child);
                document.setContent(root);

                FileWriter writer = new FileWriter(xmlPath);
                XMLOutputter outputter = new XMLOutputter();
                outputter.setFormat(Format.getPrettyFormat());
                outputter.output(document, writer);
                outputter.output(document, System.out);
                writer.close();

            }
            catch (IOException io){
                System.out.println(io.getMessage());
            }
            catch (JDOMException jdomex){
                System.out.println(jdomex.getMessage());
            }
        }

        responce.setBusList(respBusList);

        return responce;
    }
}