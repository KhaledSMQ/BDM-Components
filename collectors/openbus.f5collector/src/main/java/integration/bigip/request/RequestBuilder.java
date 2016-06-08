package integration.bigip.request;

import integration.bigip.request.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RequestBuilder {

    public static String generateGetListSoapEnvelope() {
        return marshal("xmlns:pool","urn:iControl:LocalLB/Pool",PoolGetList.class,new PoolGetList());
    }

    public static String generateVirtualServerGetListSoapEnvelope() {
        return marshal("xmlns:pool","urn:iControl:LocalLB/VirtualServer",VirtualServerGetList.class,new VirtualServerGetList());
    }

    public static String generateGetDestinationSoapEnvelope(List<String> virtualServers) {
        GetDestination gd = new GetDestination();
        gd.setVirtualServers(new VirtualServers(virtualServers));

        return marshal("xmlns:pool","urn:iControl:LocalLB/VirtualServer",GetDestination.class,gd);
    }

    public static String generateGetDefaultPoolNameSoapEnvelope(List<String> virtualServers) {
        GetDefaultPoolName gdpn = new GetDefaultPoolName();
        gdpn.setVirtualServers(new VirtualServers(virtualServers));

        return marshal("xmlns:pool","urn:iControl:LocalLB/VirtualServer",GetDefaultPoolName.class,gdpn);
    }

    public static String generateGetAllStatisticsSoapEnvelope(List<String> pools) {
        GetAllStatistics gas = new GetAllStatistics();
        gas.setPoolNames(new PoolNames(pools));

        return marshal("xmlns:pool","urn:iControl:LocalLB/PoolMember",GetAllStatistics.class,gas);
    }

    public static String generateGetPartitionListSoapEnvelope() {
        return marshal("xmlns:par","urn:iControl:Management/Partition",GetPartitionList.class,new GetPartitionList());
    }

    public static String generateSetActivePartitionSoapEnvelope(String partition) {
        SetActivePartition activePartition = new SetActivePartition();
        activePartition.setActivePartition(partition);

        return marshal("xmlns:par","urn:iControl:Management/Partition",SetActivePartition.class,activePartition);
    }

    private static <T> String marshal(String namespacePrefix, String namespaceUrl, Class<T> type, T bodyContent) {
        try {
            SOAPMessage message = MessageFactory.newInstance().createMessage();
            message.getSOAPPart().getEnvelope().addAttribute(QName.valueOf(namespacePrefix), namespaceUrl);
            SOAPBody body = message.getSOAPBody();
            JAXBContext jc = JAXBContext.newInstance(type);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(bodyContent, body);
            message.saveChanges();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            message.writeTo(out);
            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
