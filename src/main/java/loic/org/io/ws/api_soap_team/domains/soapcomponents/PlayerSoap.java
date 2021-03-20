//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.2 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2021.03.20 à 07:21:07 PM CET 
//


package loic.org.io.ws.api_soap_team.domains.soapcomponents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour playerSoap complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="playerSoap"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="playerSoapInfo" type="{http://io.org.loic/ws/api_soap_team/domains/soapComponents}playerSoapInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "playerSoap", propOrder = {
    "pid",
    "playerSoapInfo"
})
public class PlayerSoap {

    protected int pid;
    @XmlElement(required = true)
    protected PlayerSoapInfo playerSoapInfo;

    /**
     * Obtient la valeur de la propriété pid.
     * 
     */
    public int getPid() {
        return pid;
    }

    /**
     * Définit la valeur de la propriété pid.
     * 
     */
    public void setPid(int value) {
        this.pid = value;
    }

    /**
     * Obtient la valeur de la propriété playerSoapInfo.
     * 
     * @return
     *     possible object is
     *     {@link PlayerSoapInfo }
     *     
     */
    public PlayerSoapInfo getPlayerSoapInfo() {
        return playerSoapInfo;
    }

    /**
     * Définit la valeur de la propriété playerSoapInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayerSoapInfo }
     *     
     */
    public void setPlayerSoapInfo(PlayerSoapInfo value) {
        this.playerSoapInfo = value;
    }

}
