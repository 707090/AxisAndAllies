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
 * <p>Java class for military_land_unit_type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="military_land_unit_type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Infantry"/&gt;
 *     &lt;enumeration value="Tank"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "military_land_unit_type", namespace = "model/untransformed/common")
@XmlEnum
public enum MilitaryLandUnitType {

    @XmlEnumValue("Infantry")
    INFANTRY("Infantry"),
    @XmlEnumValue("Tank")
    TANK("Tank");
    private final String value;

    MilitaryLandUnitType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MilitaryLandUnitType fromValue(String v) {
        for (MilitaryLandUnitType c: MilitaryLandUnitType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
