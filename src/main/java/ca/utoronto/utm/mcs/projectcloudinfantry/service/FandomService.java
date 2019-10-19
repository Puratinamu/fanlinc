package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


@Service
public interface FandomService {

    Optional<Fandom> getFandom(Fandom fandom);
    Fandom getFandomByName(Fandom fandom);
    Fandom addFandom(Fandom fandom);
    Collection<Fandom> getFandoms();
}
