package br.com.carbigdata.teste.controller.occurrence;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/occurrence")
@AllArgsConstructor
public class OccurrenceControllerImpl implements IOccurrenceController {


    @Override
    public Object createOccurrence(Object request) {
        return null;
    }

    @Override
    public Object updateOccurrence(Long id, Object request) {
        return null;
    }

    @Override
    public void deleteOccurrence(Long id) {

    }

    @Override
    public Object getOccurrence(Long id) {
        return null;
    }

    @Override
    public List<Object> getOccurrences(int page, int size) {
        return List.of();
    }
}
