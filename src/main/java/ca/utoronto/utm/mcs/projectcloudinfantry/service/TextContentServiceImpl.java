package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.TextPostNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.TextContentRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Date;

@Service
public class TextContentServiceImpl implements TextContentService {

    private TextContentRepository textContentRepository;
    private UserRepository userRepository;
    private FandomRepository fandomRepository;

    public TextContentServiceImpl(TextContentRepository textContentRepository, UserRepository userRepository,
                                  FandomRepository fandomRepository) {
        this.textContentRepository = textContentRepository;
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
    }

    public TextContent getTextContent(Long oidTextContent){
        Optional<TextContent> result = this.textContentRepository.findById(oidTextContent);
        if (!result.isPresent()){
            throw new TextPostNotFoundException();
        }
        return result.get();
    }

    public TextContent addTextContent(TextContent textContent) {
        // check if the provided user and fandom ids are correct
        Optional<User> user = this.userRepository.findById(textContent.getOidUser());
        Optional<Fandom> fandom = this.fandomRepository.findById(textContent.getOidFandom());
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        else if (!fandom.isPresent()){
            throw new FandomNotFoundException();
        }
        else {
            textContent.setCreationTimestamp(new Date());
            textContent.setLastUpdateTimestamp(new Date());
            return this.textContentRepository.save(textContent);
        }

    }
}
