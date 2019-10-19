package ca.utoronto.utm.mcs.projectcloudinfantry;

import static org.junit.Assert.*;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.FandomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class FandomRepositoryTests {

    @Autowired
    private FandomRepository fandomRepository;

    @Before
    public void setUp() {
        Fandom lol = new Fandom("LOL", "League of legends fandom");
        fandomRepository.save(lol);
    }

    @Test
    public void testFindByName() {

        String name = "LOL";
        Fandom result = fandomRepository.getFandomByName(name);
        assertNotNull(result);
        assertEquals("League of legends fandom", result.getDescription());
    }

}
