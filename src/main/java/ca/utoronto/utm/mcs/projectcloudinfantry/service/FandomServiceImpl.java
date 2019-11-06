package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class FandomServiceImpl implements FandomService {

    private FandomRepository fandomRepository;

    public FandomServiceImpl(FandomRepository fandomRepository) {
        this.fandomRepository = fandomRepository;
    }

    public Fandom getFandom(Long oidFandom) {
        Optional<Fandom> result = this.fandomRepository.findById(oidFandom);
        if (!result.isPresent()){
            throw new FandomNotFoundException();
        }
        return result.get();
    }

    @Override
    public Fandom getFandomByName(Fandom fandom) {
        String name = fandom.getName();
        Fandom result = this.fandomRepository.getFandomByName(name);
        return result;
    }

    @Override
    public Fandom addFandom(Fandom fandom) {
        // check if fandom already exists
        Fandom existingFandom = this.fandomRepository.getFandomByName(fandom.getName());
        if (existingFandom == null) {
            fandom.setCreationTimestamp(new Date());
            fandom.setLastUpdateTimestamp(new Date());
            return this.fandomRepository.save(fandom);
        } else {
            throw new FandomAlreadyExistsException();
        }

    }

    public Collection<Fandom> getFandoms() {
        return (Collection<Fandom>) this.fandomRepository.findAll();
    }
}