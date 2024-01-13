package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.exception.AlertNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.RegionNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.model.Region;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AlertDAOImpl implements AlertDAO {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void addAlert(Alert alert) {
        if (isValidRegion(alert.getRegion())) {
            Alert newAlert = createNewAlert(alert);
            mergeAlert(newAlert);
        } else {
            System.out.println("Erreur : Région inconnue - " + alert.getRegion());
        }
    }

    private Alert createNewAlert(Alert alert) {
        String regionCode = getRegionCode(alert.getRegion());
        Region region = new Region("FR-" + regionCode, alert.getRegion());
        return new Alert(alert.getId(), alert.getDescription(), alert.getRegion(), region);
    }

    private String getRegionCode(String regionName)   {
        switch (regionName.toLowerCase()) {
            case "auvergne-rhone-alpes":
                return "ARA";
            case "bourgogne-franche-comte":
                return "BFC";
            case "bretagne":
                return "BRE";
            case "corse":
                return "COR";
            case "centre-val-de-loire":
                return "CVL";
            case "grand-est":
                return "GES";
            case "hauts-de-france":
                return "HDF";
            case "ile-de-france":
                return "IDF";
            case "nouvelle-aquitaine":
                return "NAQ";
            case "normandie":
                return "NOR";
            case "occitanie":
                return "OCC";
            case "provence-alpes-cote-dazur":
                return "PAC";
            case "pays-de-la-loire":
                return "PDL";
            default:
                throw new IllegalArgumentException("Unknown region: " + regionName);
        }
    }

    private void mergeAlert(Alert newAlert) {
        em.merge(newAlert);
    }

    private boolean isValidRegion(String regionName) {
        try {
            getRegionCode(regionName);
            return true;  // La région est valide
        } catch (IllegalArgumentException e) {
            return false; // La région est inconnue
        }
    }

    public void checkRegion(Alert alert) {
        try {
            String regionCode = getRegionCode(alert.getRegion());
            // Si la région existe, aucune exception n'est lancée
            System.out.println("La région existe : " + alert.getRegion() + " (Code : " + regionCode + ")");
        } catch (IllegalArgumentException e) {
            // Si l'exception est lancée, la région n'existe pas dans les propositions
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
