package geiffel.da4.issuetracker.projet;

import com.fasterxml.jackson.databind.ObjectMapper;
import geiffel.da4.issuetracker.exceptions.ResourceAlreadyExistsException;
import geiffel.da4.issuetracker.exceptions.ResourceNotFoundException;
import geiffel.da4.issuetracker.user.Fonction;
import geiffel.da4.issuetracker.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProjetControllerTest {

    @MockBean
    ProjetService projetService;

    private List<Projet> projets = new ArrayList<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        projets.add(new Projet(1L, "unNom1"));
        projets.add(new Projet(2L, "unNom2"));
        projets.add(new Projet(3L, "unNom3"));
        projets.add(new Projet(4L, "unNom4"));

        projetService = new ProjetLocalService(projets);
    }
    @Test
    void whenGettingAll_shouldReturn4ProjectsAndBe200() throws Exception{
        when(projetService.getAll()).thenReturn(projets);
        when(projetService.getById(4L)).thenReturn(projets.get(3));
        when(projetService.getById(20L)).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/projets")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()
        ).andExpect(jsonPath("$", hasSize(4))
        ).andDo(print());
    }
  /*  @Test
    void whenGettingUnexistingId_should404() throws Exception {
        mockMvc.perform(get("/projets/20")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()
        ).andDo(print());
    }

    @Test
    void whenCreatingNew_shouldReturnLink_andShouldBeStatusCreated() throws Exception {
        Projet projet1 = new Projet(89L,"nouveau");
        ArgumentCaptor<Projet> project_received = ArgumentCaptor.forClass(Projet.class);
        when(projetService.create(any())).thenReturn(projet1);

        mockMvc.perform(post("/projets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(projet1))
        ).andExpect(status().isCreated()
        ).andExpect(header().string("Location", "/projets/"+projet1.getId())
        ).andDo(print());

        verify(projetService).create(project_received.capture());
        assertEquals(projet1, project_received.getValue());
    }

    @Test
    void whenCreatingWithExistingId_should404() throws Exception {
        when(projetService.create(any())).thenThrow(ResourceAlreadyExistsException.class);
        mockMvc.perform(post("/projets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(this.projets.get(2)))
        ).andExpect(status().isConflict()
        ).andDo(print());
    }

    @Test
    void whenUpdating_shouldReceiveUserToUpdate_andReturnNoContent() throws Exception {
        Projet projet1 = projets.get(1);
        Projet updated_project = new Projet(projet1.getId(), "updated");
        ArgumentCaptor<User> project_received = ArgumentCaptor.forClass(User.class);

        mockMvc.perform(put("/projets/"+projet1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updated_project))
        ).andExpect(status().isNoContent());
    }

    @Test
    void whenDeletingExisting_shouldCallServiceWithCorrectId_andSendNoContent() throws Exception {
        Long id = 28L;

        mockMvc.perform(delete("/projets/"+id)
        ).andExpect(status().isNoContent()
        ).andDo(print());

        ArgumentCaptor<Long> id_received = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(projetService).delete(id_received.capture());
        assertEquals(id, id_received.getValue());
    }*/
}
