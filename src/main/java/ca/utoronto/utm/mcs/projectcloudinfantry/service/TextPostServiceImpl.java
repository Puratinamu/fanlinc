package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.TextPostNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.TextPostRepository;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Date;

@Service
public class TextPostServiceImpl implements TextPostService {

    private TextPostRepository textPostRepository;
    private UserRepository userRepository;
    private FandomRepository fandomRepository;

    public TextPostServiceImpl(TextPostRepository textPostRepository, UserRepository userRepository,
                               FandomRepository fandomRepository) {
        this.textPostRepository = textPostRepository;
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
    }

    public TextContent getTextPost(Long oidTextPost){
        Optional<TextContent> result = this.textPostRepository.findById(oidTextPost);
        if (!result.isPresent()){
            throw new TextPostNotFoundException();
        }
        return result.get();
    }


    public TextContent addTextPost(TextContent textPost) {
        // check if the provided user and fandom ids are correct
        Optional<User> user = this.userRepository.findById(textPost.getOidUser());
        Optional<Fandom> fandom = this.fandomRepository.findById(textPost.getOidFandom());
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        else if (!fandom.isPresent()){
            throw new FandomNotFoundException();
        }
        else {
            textPost.setCreationTimestamp(new Date());
            textPost.setLastUpdateTimestamp(new Date());
            return this.textPostRepository.save(textPost);
        }

    }
}
