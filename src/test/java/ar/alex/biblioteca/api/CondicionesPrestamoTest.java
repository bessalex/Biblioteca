package ar.alex.biblioteca.api;

import ar.alex.biblioteca.api.controller.CondicionesPrestamoController;
import ar.alex.biblioteca.api.dto.CategoriaDto;
import ar.alex.biblioteca.business.mapper.CondicionesPrestamoBOMapperImpl;
import ar.alex.biblioteca.business.service.CategoriaService;
import ar.alex.biblioteca.business.service.CondicionesPrestamoService;
import ar.alex.biblioteca.data_access.CondicionPrestamoRepository;
import ar.alex.biblioteca.data_access.entity.Categoria;
import ar.alex.biblioteca.data_access.entity.CondicionPrestamoEntity;
import com.google.gson.Gson;
import jakarta.persistence.Column;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CondicionesPrestamoController.class)
public class CondicionesPrestamoTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CondicionPrestamoRepository condicionPrestamoRepository;
    @MockBean
    private CategoriaService categoriaService;
    @MockBean
    private CondicionesPrestamoBOMapperImpl condicionesPrestamoBOMapper;

    private CondicionesPrestamoService condicionesPrestamoService;
    private CondicionPrestamoEntity mockedCondicionPrestamoEntity;
    private int idMockedCP, idCategoriaMockedCP, numMaximoDiasPrestamoMockedCP, numMaximoRenovacionesMockedCP;
    private Integer idMockCategoria;
    private String nameMockCategoria;
    private Categoria mockedCategoria;
    @BeforeEach
    public void setUp() {
        this.idMockCategoria = 12;
        this.nameMockCategoria = "testCondicionPrestamo";
        this.mockedCategoria = new Categoria(this.nameMockCategoria);
        this.mockedCategoria.setId(this.idMockCategoria);

        this.idMockedCP = 11; this.idCategoriaMockedCP = this.idMockCategoria;
        this.numMaximoDiasPrestamoMockedCP = 2;
        this.numMaximoRenovacionesMockedCP = 1;
        this.mockedCondicionPrestamoEntity = new CondicionPrestamoEntity(this.idMockedCP,
                this.idMockCategoria,
                this.numMaximoDiasPrestamoMockedCP,
                this.numMaximoRenovacionesMockedCP);

        this.condicionesPrestamoService = new CondicionesPrestamoService(this.condicionPrestamoRepository,
                this.condicionesPrestamoBOMapper,
                this.categoriaService);
    }

    @AfterEach
    public void tearDown(){
        this.mockedCondicionPrestamoEntity = null;
        this.condicionesPrestamoService = null;
    }

    @Test
    public void getCondicionesPrestamoTest() throws Exception {
        Mockito.when(this.condicionPrestamoRepository.findById(this.idMockedCP))
                .thenReturn(Optional.ofNullable(this.mockedCondicionPrestamoEntity));
        Mockito.when(this.condicionesPrestamoService.findById(this.idMockedCP)).thenCallRealMethod();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/library/loan-condition/{id}",this.idMockedCP)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id_categoria").value(this.idMockCategoria))
                .andExpect(jsonPath("$.maximo_dias_prestamo").value(this.numMaximoDiasPrestamoMockedCP))
                .andExpect(jsonPath("$.maximo_renovaciones").value(this.numMaximoRenovacionesMockedCP));
    }
/*

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
*/

}
