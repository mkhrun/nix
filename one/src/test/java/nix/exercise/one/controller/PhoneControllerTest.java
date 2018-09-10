package nix.exercise.one.controller;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import nix.exercise.one.repository.PhoneRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PhoneController.class)
public class PhoneControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneRepository phoneRepository;

    @Test
    public void getAllPhones() throws Exception {
        when(phoneRepository.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/phones/all"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(0)));

        verify(phoneRepository).findAll();
    }
}
