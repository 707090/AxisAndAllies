//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.21 at 02:25:32 AM EDT 
//


package model.untransformed.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for power_type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="power_type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Soviet Union"/&gt;
 *     &lt;enumeration value="Germany"/&gt;
 *     &lt;enumeration value="United Kingdom"/&gt;
 *     &lt;enumeration value="Japan"/&gt;
 *     &lt;enumeration value="United States"/&gt;
 *     &lt;enumeration value="Neutral"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "power_type", namespace = "model/untransformed/common")
@XmlEnum
public enum PowerType {

    @XmlEnumValue("Soviet Union")
    SOVIET_UNION("Soviet Union"),
    @XmlEnumValue("Germany")
    GERMANY("Germany"),
    @XmlEnumValue("United Kingdom")
    UNITED_KINGDOM("United Kingdom"),
    @XmlEnumValue("Japan")
    JAPAN("Japan"),
    @XmlEnumValue("United States")
    UNITED_STATES("United States"),
    @XmlEnumValue("Neutral")
    NEUTRAL("Neutral");
    private final String value;

    PowerType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PowerType fromValue(String v) {
        for (PowerType c: PowerType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
