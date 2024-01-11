package ar.alex.biblioteca.api;

import ar.alex.biblioteca.api.controller.CategoriaController;
import ar.alex.biblioteca.api.dto.CategoriaDto;
import ar.alex.biblioteca.business.service.CategoriaService;
import ar.alex.biblioteca.data_access.entity.Categoria;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    private Categoria mockedCategoria;
    private String nameMockCategoria;
    private Integer idMockCategoria;
    @BeforeEach
    public void setUp() {
        idMockCategoria = 10; this.nameMockCategoria= "test";
        mockedCategoria = new Categoria(this.nameMockCategoria);
        mockedCategoria.setId(this.idMockCategoria);
    }

    @Test
    public void getCategoriaTest() throws Exception {
        Mockito.when(categoriaService.findById(10)).thenReturn(new Categoria("test"));
        ResultActions result  = mockMvc.perform(
                MockMvcRequestBuilders.get("/library/category/{id}",10)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("test"));
    }

    @Test
    public void addCategoriaTest() throws Exception {

        CategoriaDto categoriaDto = new CategoriaDto("addCategoriaTest");

        Gson gson = new Gson();
        String json = gson.toJson(categoriaDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/library/category")
                        .contentType(MediaType.APPLICATION_JSON).content(json)
                ).andExpect(status().isOk());
    }

    @Test
    public void findByCategoriaTest() {
        Mockito.when(categoriaService.findIdByNombre("test")).thenReturn(this.mockedCategoria);
        Categoria categoriaTest = categoriaService.findIdByNombre(this.nameMockCategoria);
        Assertions.assertEquals(this.nameMockCategoria, categoriaTest.getNombre());
        Assertions.assertEquals(this.idMockCategoria, categoriaTest.getId());
    }

}
