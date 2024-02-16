//package com.example.clothes.service;
//
//import com.example.clothes.simulator.IncomeObject;
//import com.example.clothes.simulator.UserObject;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertThat;
//
//@RunWith(SpringRunner.class)
//public class IncomeServiceTest {
//    @InjectMocks
//    private IncomeServiceImpl incomeService;
//    @Mock
//    private IncomeRepository incomeRepo;
//    @Mock
//    private UserRepository userRepo;
//
//
//    @Test
//    @DisplayName("Custom test name contain spaces")
//    @Disabled("Disabled util bug #42 done")
//    public void createTest(){
//        Mockito.when( userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(UserObject.getEntity()));
//        Mockito.when(incomeRepo.save(Mockito.any())).thenReturn(IncomeObject.getEntity());
//        IncomeDTO incomeDTO = incomeService.create(IncomeObject.getDTO());
//        Assertions.assertEquals( IncomeObject.getDTO().getIncomeId(), incomeDTO.getIncomeId());
//    }
//
//
//
//}
