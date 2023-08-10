package br.com.techchallenge.energymonitor.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.techchallenge.energymonitor.dominio.Pessoa;
import br.com.techchallenge.energymonitor.dominio.enums.Genero;
import br.com.techchallenge.energymonitor.dominio.enums.Parentesco;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class PessoaDtoTest {

    private static final String pessoaJson = """
            {
                "nome": "Fulano de Testes",
                "dataNascimento": "01-01-1987",
                "genero": "MASCULINO"
            }
            """;
    private PessoaDto pessoaDto;

    @BeforeEach
    void initTests() throws JsonMappingException, JsonProcessingException {
        pessoaDto = new ObjectMapper().findAndRegisterModules().readValue(pessoaJson, PessoaDto.class);

    }

    @Test
    void whenJsonisValid_ThenReturnPessoaDtoValid() throws JsonMappingException, JsonProcessingException {
        PessoaDto actual = new ObjectMapper().findAndRegisterModules().readValue(pessoaJson, PessoaDto.class);

        assertEquals("Fulano de Testes", actual.getNome(), "nome inválido");
        assertEquals(LocalDate.parse("1987-01-01"), actual.getDataNascimento(), "data de nascimento inválida");
        assertEquals(Genero.MASCULINO, actual.getGenero(), "genero inválido");
    }

    @Test
    void pessoaDto_toDomainTest() {
        Pessoa pessoaDomain = pessoaDto.toDomain();

        assertEquals(pessoaDto.getNome(), pessoaDomain.getNome());
        assertEquals(pessoaDto.getDataNascimento(), pessoaDomain.getDataNascimento());
        assertEquals(pessoaDto.getGenero(), pessoaDomain.getGenero());
    }

    @ParameterizedTest
    @MethodSource("providerForInvalidData")
    void whenInvalidData_ThenShouldThrow(String nome, LocalDate dataNascimento, Genero genero,
            Parentesco parentesco) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        pessoaDto = new PessoaDto(0l, nome, dataNascimento, genero);
        var validations = validator.validate(pessoaDto);

        assertTrue(validations.size() > 0);
    }

    static Stream<Arguments> providerForInvalidData() {
        var data = LocalDate.parse("1987-01-01");

        return Stream.of(
                Arguments.of(null, data, Genero.MASCULINO, Parentesco.PAI),
                Arguments.of("Nome válido", null, Genero.MASCULINO, Parentesco.MAE));
    }
}