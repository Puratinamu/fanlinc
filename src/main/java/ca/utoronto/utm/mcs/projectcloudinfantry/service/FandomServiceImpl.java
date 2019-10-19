package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
public class FandomServiceImpl implements FandomService {

    @Autowired
    FandomRepository fandomRepository;

    @Autowired
    public FandomServiceImpl(FandomRepository fandomRepository) {
        this.fandomRepository = fandomRepository;
    }

    public Fandom getFandom(UUID oid){
        Fandom fandom = fandomRepository.getFandom(oid);
        return fandom;
    }

    @Override
    public Fandom getFandomByName(String name) {
        Fandom fandom = fandomRepository.getFandomByName(name);
        return fandom;
    }

    @Override
    public Fandom addFandom(Fandom fandom) {
        // check if fandom already exists
        Fandom existingFandom = fandomRepository.getFandomByName(fandom.getName());
        if (existingFandom == null) {
            fandom.setOidFandom(UUID.randomUUID());
            fandom.setCreationTimestamp(new Date());
            fandom.setLastUpdateTimestamp(new Date());
            return fandomRepository.save(fandom);
        }
        else {return existingFandom;}
    }

    public Collection<Fandom> getAll(){
        return (Collection<Fandom>) fandomRepository.findAll();
    }

}
