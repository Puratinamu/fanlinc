package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class FandomServiceImpl implements FandomService {

    @Autowired
    FandomRepository fandomRepository;

    @Autowired
    public FandomServiceImpl(FandomRepository fandomRepository) {
        this.fandomRepository = fandomRepository;
    }

    public Optional<Fandom> getFandom(Fandom fandom){
        UUID oid = fandom.getOidFandom();
        return fandomRepository.findById(oid);
    }

    @Override
    public Fandom getFandomByName(Fandom fandom) {
        String name = fandom.getName();
        Fandom result = fandomRepository.getFandomByName(name);
        return result;
    }

    @Override
    public Fandom addFandom(Fandom fandom) {
        // check if fandom already exists
        Fandom existingFandom = fandomRepository.getFandomByName(fandom.getName());
        if (existingFandom == null) {
            fandom.setCreationTimestamp(new Date());
            fandom.setLastUpdateTimestamp(new Date());
            return fandomRepository.save(fandom);
        } else {
            return existingFandom;
        }
    }

    public Collection<Fandom> getFandoms() {
        return (Collection<Fandom>) fandomRepository.findAll();
    }
}