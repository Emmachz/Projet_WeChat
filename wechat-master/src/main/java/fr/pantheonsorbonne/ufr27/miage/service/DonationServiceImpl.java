package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.DonationDAO;
import fr.pantheonsorbonne.ufr27.miage.model.Donation;
import fr.pantheonsorbonne.ufr27.miage.model.Region;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class DonationServiceImpl implements DonationService {
    @PersistenceContext
    EntityManager em;

    @Inject
    DonationDAO donationDAO;

    @Override
    public void addDonation(fr.pantheonsorbonne.ufr27.miage.dto.Donation donation){
        this.donationDAO.addDonation(donation);
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