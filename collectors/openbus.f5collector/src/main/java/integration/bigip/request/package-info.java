@XmlSchema(namespace = "",
           elementFormDefault = XmlNsForm.QUALIFIED,
           xmlns = {@XmlNs(prefix="pool", namespaceURI="urn:iControl:LocalLB/PoolMember"),
                    @XmlNs(prefix="pool", namespaceURI="urn:iControl:LocalLB/Pool"),
                    @XmlNs(prefix="vir", namespaceURI="urn:iControl:LocalLB/VirtualServer"),
                    @XmlNs(prefix="par", namespaceURI = "urn:iControl:Management/Partition")})
package integration.bigip.request;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;