//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.21 at 02:25:32 AM EDT 
//


package model.untransformed.map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sea_zone_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sea_zone_type"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{model/untransformed/map}area_type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="zone_number" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sea_zone_type", propOrder = {
    "zoneNumber"
})
public class SeaZoneType
    extends AreaType
{

    @XmlElement(name = "zone_number")
    protected int zoneNumber;

    /**
     * Gets the value of the zoneNumber property.
     * 
     */
    public int getZoneNumber() {
        return zoneNumber;
    }

    /**
     * Sets the value of the zoneNumber property.
     * 
     */
    public void setZoneNumber(int value) {
        this.zoneNumber = value;
    }

}
