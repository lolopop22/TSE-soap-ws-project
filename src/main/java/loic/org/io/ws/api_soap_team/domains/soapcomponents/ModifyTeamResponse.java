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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="teamSoap" type="{http://io.org.loic/ws/api_soap_team/domains/soapComponents}teamSoap"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "teamSoap"
})
@XmlRootElement(name = "modifyTeamResponse")
public class ModifyTeamResponse {

    @XmlElement(required = true)
    protected TeamSoap teamSoap;

    /**
     * Obtient la valeur de la propriété teamSoap.
     * 
     * @return
     *     possible object is
     *     {@link TeamSoap }
     *     
     */
    public TeamSoap getTeamSoap() {
        return teamSoap;
    }

    /**
     * Définit la valeur de la propriété teamSoap.
     * 
     * @param value
     *     allowed object is
     *     {@link TeamSoap }
     *     
     */
    public void setTeamSoap(TeamSoap value) {
        this.teamSoap = value;
    }

}
