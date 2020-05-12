package ua.cruise.company.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ua.cruise.company.entity.Cruise;
import ua.cruise.company.entity.User;
import ua.cruise.company.repository.CruiseRepository;
import ua.cruise.company.repository.OrderRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BookCruiseParameterizedTest {
    private static final Long ID = 1L;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @InjectMocks
    private TouristOrderService instance;
    @Mock
    private CruiseRepository cruiseRepository;
    @Mock
    private OrderRepository orderRepository;


    @Parameter(0)
    public int initialVacancies;
    @Parameter(1)
    public int takenByOrder;
    @Parameter(2)
    public int expectedVacanciesLeft;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 10, 2, 8 },
                { 0, 1, -1 },
                { 2, 10, -8 },
                { 450, 300, 150 },
                { 200, 200, 0 }
        });
    }

    @Test
    public void shouldDecreaseCruiseVacanciesWhenOrderCreated() throws NoEntityFoundException {
        Cruise cruise = new Cruise();
        cruise.setPrice(BigDecimal.valueOf(1));
        cruise.setVacancies(initialVacancies);
        when(cruiseRepository.findById(ID)).thenReturn(Optional.of(cruise));

        instance.bookCruise(new User(), ID, takenByOrder);

        ArgumentCaptor<Cruise> saveCruiseArgument = ArgumentCaptor.forClass(Cruise.class);
        verify(cruiseRepository).save(saveCruiseArgument.capture());
        assertThat(saveCruiseArgument.getValue().getVacancies()).isEqualTo(expectedVacanciesLeft);
    }
}
