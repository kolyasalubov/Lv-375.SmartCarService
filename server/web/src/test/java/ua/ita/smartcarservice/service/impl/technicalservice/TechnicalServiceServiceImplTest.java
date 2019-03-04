package ua.ita.smartcarservice.service.impl.technicalservice;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import ua.ita.smartcarservice.dto.technicalservice.TechnicalServiceDto;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.service.technicalservice.TechnicalServiceService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@TestExecutionListeners(MockitoTestExecutionListener.class)
@SpringBootTest
public class TechnicalServiceServiceImplTest {

    @MockBean
    TechnicalServiceRepository technicalServiceRepository;

    @Autowired
    TechnicalServiceService technicalServiceService;

    @Test
    public void getTechnicalServiceDtoByUser() {
        //Preconditions
        List<TechnicalServiceDto> expectedDtoList = new ArrayList<>();

    }
}