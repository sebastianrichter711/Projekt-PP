package pl.polsl.pp.backapp.section;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.pp.backapp.exception.IdNotFoundInDatabaseException;


@Service
public class SectionService {

    private SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public Iterable<Section> getSections() {
        return sectionRepository.findAll();
    }

    public Section getSection(String id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Section of id " + id + " not found"));

        return section;
    }

    public Section addSection(Section section) {
        section.setTopicsNumber(0);
        return sectionRepository.save(section);
    }

    public Section updateSection(String id, Section updatedSection) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Section of id " + id + " not found"));

        section.setModerator(updatedSection.getModerator());    // TODO change to moderator ID
        section.setName(updatedSection.getName());

        return sectionRepository.save(section);
    }

    public void deleteSection(String id) {
        try {
            sectionRepository.deleteById(id);
        } catch (IdNotFoundInDatabaseException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
