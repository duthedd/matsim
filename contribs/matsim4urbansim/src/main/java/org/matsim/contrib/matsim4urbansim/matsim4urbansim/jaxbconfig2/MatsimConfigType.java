//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-558 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.19 at 12:45:10 PM CEST 
//


package org.matsim.contrib.matsim4urbansim.matsim4urbansim.jaxbconfig2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for matsim_configType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="matsim_configType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="config" type="{}configType"/>
 *         &lt;element name="matsim4urbansim" type="{}matsim4urbansimType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matsim_configType", propOrder = {
    "config",
    "matsim4Urbansim"
})
public class MatsimConfigType {

    @XmlElement(required = true)
    protected ConfigType config;
    @XmlElement(name = "matsim4urbansim", required = true)
    protected Matsim4UrbansimType matsim4Urbansim;

    /**
     * Gets the value of the config property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigType }
     *     
     */
    public ConfigType getConfig() {
        return config;
    }

    /**
     * Sets the value of the config property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigType }
     *     
     */
    public void setConfig(ConfigType value) {
        this.config = value;
    }

    /**
     * Gets the value of the matsim4Urbansim property.
     * 
     * @return
     *     possible object is
     *     {@link Matsim4UrbansimType }
     *     
     */
    public Matsim4UrbansimType getMatsim4Urbansim() {
        return matsim4Urbansim;
    }

    /**
     * Sets the value of the matsim4Urbansim property.
     * 
     * @param value
     *     allowed object is
     *     {@link Matsim4UrbansimType }
     *     
     */
    public void setMatsim4Urbansim(Matsim4UrbansimType value) {
        this.matsim4Urbansim = value;
    }

}
