package ua.cruise.company.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.cruise.company.entity.Cruise;
import ua.cruise.company.entity.Order;
import ua.cruise.company.entity.User;
import ua.cruise.company.repository.CruiseRepository;
import ua.cruise.company.repository.ExcursionRepository;
import ua.cruise.company.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TouristOrderServiceUnitTest {
    private static final Long ID = 1L;

    @InjectMocks
    private TouristOrderService instance;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CruiseRepository cruiseRepository;
    @Mock
    private ExcursionRepository excursionRepository;


    @Test
    public void shouldDecreaseCruiseVacanciesWhenOrderCreated(){
        int testArraySize = 5;
        int[] initialVacancies = {10, 0, 2, 450, 200};
        int[] takenByOrder =    {2, 1, 10, 300, 200};
        int[] expected =        {8, -1, -8, 150, 0};

        for(int i=0; i < testArraySize; i++) {
            Cruise cruise = new Cruise();
            cruise.setPrice(BigDecimal.valueOf(1));
            cruise.setVacancies(initialVacancies[i]);
            when(cruiseRepository.findById(ID)).thenReturn(Optional.of(cruise));
            instance.bookCruise(new User(), ID, takenByOrder[i]);
        }

        ArgumentCaptor<Cruise> argument = ArgumentCaptor.forClass(Cruise.class);
        verify(cruiseRepository, times(testArraySize)).save(argument.capture());

        for(int i=0; i < testArraySize; i++) {
            assertThat(argument.getAllValues().get(i).getVacancies()).isEqualTo(expected[i]);
        }
    }

    @Test
    public void shouldIncreaseCruiseVacanciesWhenOrderCanceled(){
        int testArraySize = 5;
        int[] initialVacancies = {10, 0, 2, 150, 0};
        int[] takenByOrder =    {2, 1, 10, 300, 200};
        int[] expected =        {12, 1, 12, 450, 200};

        for(int i=0; i < testArraySize; i++) {
            Order order = new Order();
            Cruise cruise = new Cruise();
            cruise.setVacancies(initialVacancies[i]);
            order.setCruise(cruise);
            order.setQuantity(takenByOrder[i]);
            when(orderRepository.findById(ID)).thenReturn(Optional.of(order));
            instance.cancelBooking(ID);
        }

        ArgumentCaptor<Cruise> argument = ArgumentCaptor.forClass(Cruise.class);
        verify(cruiseRepository, times(testArraySize)).save(argument.capture());

        for(int i=0; i < testArraySize; i++) {
            assertThat(argument.getAllValues().get(i).getVacancies()).isEqualTo(expected[i]);
        }
    }
}
