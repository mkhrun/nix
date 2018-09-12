package nix.exercise.one.controller;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import nix.exercise.one.domain.Phone;
import nix.exercise.one.repository.PhoneRepository;

import static org.hamcrest.Matchers.equalTo;
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
        var phone = new Phone();
        phone.setArticle("aaa");

        when(phoneRepository.findAll()).thenReturn(Arrays.asList(phone));

        mockMvc.perform(get("/phones/all"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].article", equalTo("aaa")));

        verify(phoneRepository).findAll();
    }
}
