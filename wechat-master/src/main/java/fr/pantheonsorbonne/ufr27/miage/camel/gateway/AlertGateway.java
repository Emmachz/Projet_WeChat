package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.exception.AlertNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.model.Region;


import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpHeaders.setHeader;

@ApplicationScoped
public class AlertGateway {

    @Inject
    AlertService alertService;

    @Inject
    CamelContext camelContext;

    public void addAlert(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert){
        Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-HDF","hauts-de-france"));
        this.alertService.addAlert(newAlert);
    }

    public void transfertAlert2(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert) throws AlertNotFoundException, IOException {
        try (ProducerTemplate producerTemplate = camelContext.createProducerTemplate()) {
            if( (alert.getAlertRegion().equals("auvergne-rhone-alpes"))){
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-ARA","auvergne-rhone-alpes"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "auvergne-rhone-alpes");
            }

            else if ((alert.getAlertRegion().equals("bourgogne-franche-comte"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-BFC","bourgogne-franche-comte"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "bourgogne-franche-comte");
            }
            else if ((alert.getAlertRegion().equals("bretagne"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-BRE","bretagne"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "bretagne");
            }
            else if ((alert.getAlertRegion().equals("corse"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-COR","corse"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "corse");
            }
            else if ((alert.getAlertRegion().equals("centre-val-de-loire"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-CVL","centre-val-de-loire"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "centre-val-de-loire");
            }
            else if ((alert.getAlertRegion().equals("grand-est"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-GES","grand-est"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "grand-est");
            }
            else if ((alert.getAlertRegion().equals("hauts-de-france"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-HDF","hauts-de-france"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "hauts-de-france");
            }
            else if ((alert.getAlertRegion().equals("ile-de-france"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-IDF","ile-de-france"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "ile-de-france");
            }
            else if ((alert.getAlertRegion().equals("nouvelle-aquitaine"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-NAQ","nouvelle-aquitaine"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "nouvelle-aquitaine");
            }
            else if ((alert.getAlertRegion().equals("normandie"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-NOR","normandie"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "normandie");
            }
            else if ((alert.getAlertRegion().equals("occitanie"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-ACC","occitanie"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "occitanie");
            }
            else if ((alert.getAlertRegion().equals("provence-alpes-cote-dazur"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-PAC","provence-alpes-cote-dazur"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "provence-alpes-cote-dazur");
            }
            else if ((alert.getAlertRegion().equals("pays-de-la-loire"))) {
                Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region("FR-PDL","pays-de-la-loire"));
                producerTemplate.sendBodyAndHeader("direct:alerttransfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()),  "headerRegion", "pays-de-la-loire");
            }
            //else {
                //producerTemplate.sendBody("direct:transfert", new fr.pantheonsorbonne.ufr27.miage.dto.Alert(newAlert.getId(), newAlert.getDescription(), newAlert.getRegion()));
                //EventNotFoundException(alert.getAlertId())
            //}
            else{
                new AlertNotFoundException(alert.getAlertId());
            }
        } catch (NonUniqueResultException | NoResultException e) {
            throw new AlertNotFoundException(alert.getAlertId());
        }
    }

}
