package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AlertDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Region;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class AlertServiceImpl implements AlertService {

    @PersistenceContext
    EntityManager em;

    @Inject
    AlertDAO alertDAO;

    @Override
    @Transactional
    public Alert alert(Alert alert) throws EventNotFoundException {
        try {

        } catch (NonUniqueResultException | NoResultException e) {
            throw new EventNotFoundException(alert.getId());
        }
        return alert;

    }

    public void addAlert(fr.pantheonsorbonne.ufr27.miage.dto.Alert alert){
        Alert newAlert=new Alert(alert.getAlertId(), alert.getAlertDescription(), alert.getAlertRegion(), new Region(getRegionId(alert.getAlertRegion()),alert.getAlertRegion()));
        this.alertDAO.addAlert(newAlert);
    }

    public String getRegionId(String regionName) {
        Map<String, String> regionNameMapping = new HashMap<>();
        regionNameMapping.put("auvergne-rhone-alpes", "FR-ARA");
        regionNameMapping.put("bourgogne-franche-comte", "FR-BFC");
        regionNameMapping.put("bretagne", "FR-BRE");
        regionNameMapping.put("corse", "FR-COR");
        regionNameMapping.put("centre-val-de-loire", "FR-CVL");
        regionNameMapping.put("grand-est", "FR-GES");
        regionNameMapping.put("hauts-de-france", "FR-HDF");
        regionNameMapping.put("ile-de-france", "FR-IDF");
        regionNameMapping.put("nouvelle-aquitaine", "FR-NAQ");
        regionNameMapping.put("normandie", "FR-NOR");
        regionNameMapping.put("occitanie", "FR-ACC");
        regionNameMapping.put("provence-alpes-cote-dazur", "FR-PAC");
        regionNameMapping.put("pays-de-la-loire", "FR-PDL");
        return regionNameMapping.get(regionName);
    }

}
