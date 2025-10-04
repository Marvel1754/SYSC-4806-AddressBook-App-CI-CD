package org.sysc4806.labs.lab5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AddressBookGUIViewController.class)
@ContextConfiguration(classes = AddressBookGUIViewController.class)
public class AddressBookGUIViewControllerWebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AddressBookRepository repository;

    @MockitoBean
    private AddressBookGUIViewController controller;

    private String routing = "/view";
    private String endpointCommand = "/";

    @Test
    public void testReturnHomeWebpage() throws Exception {
        when(controller.home(any())).thenReturn("home");
        endpointCommand = routing + "/";
        this.mockMvc.perform(get(endpointCommand)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testAddCommandWorks() throws Exception {
        when(controller.add(any(), any(), any(), any(), any(), any())).thenReturn("buddies");
        endpointCommand = routing + "/add";
        this.mockMvc.perform(get(endpointCommand)
                .param("addressBookName", "Test AddressBook")
                .param("firstname", "First")
                .param("lastname", "Last")
                .param("contactNumber", "13432623063")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testAddressBooksCommandWorks() throws Exception {
        when(controller.addressBooks(any())).thenReturn("addressbooks");
        endpointCommand = routing + "/addressbooks";
        this.mockMvc.perform(get(endpointCommand)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testCreateCommandWorks() throws Exception {
        when(controller.create(any(), any())).thenReturn("addressbooks");
        endpointCommand = routing + "/create";
        this.mockMvc.perform(get(endpointCommand)
                .param("name", "Test AddressBook")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testRemoveCommandWorks() throws Exception {
        when(controller.remove(any(), any(), any())).thenReturn("buddies");
        endpointCommand = routing + "/remove";
        this.mockMvc.perform(get(endpointCommand)
                .param("addressBookName", "Test AddressBook")
                .param("id", "1")).andDo(print()).andExpect(status().isOk());
    }
}
