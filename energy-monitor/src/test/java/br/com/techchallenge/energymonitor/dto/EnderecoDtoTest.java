// package br.com.techchallenge.energymonitor.dto;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.stream.Stream;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.Arguments;
// import org.junit.jupiter.params.provider.MethodSource;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import br.com.techchallenge.energymonitor.dominio.Endereco;
// import br.com.techchallenge.energymonitor.dominio.enums.Estado;
// import jakarta.validation.Validation;
// import jakarta.validation.Validator;
// import jakarta.validation.ValidatorFactory;

// class EnderecoDtoTest {

//     private static final String enderecoJson = """
//             {   
//                 "rua": "Av. Alm. Silvio de Noronha",
//                 "numero": 365,
//                 "bairro": "Centro",
//                 "cidade": "Rio de Janeiro",
//                 "estado": "Rio de Janeiro" } 
//             """;
    
//     private EnderecoDto enderecoDto;

//     @BeforeEach
//     void initTests() throws JsonMappingException, JsonProcessingException {
//         enderecoDto = new ObjectMapper().findAndRegisterModules().readValue(enderecoJson, EnderecoDto.class);
//     }

//     @Test
//     void whenJsonisValid_ThenReturnEnderecoDtoValid() throws JsonMappingException, JsonProcessingException {
//         EnderecoDto actual = new ObjectMapper().findAndRegisterModules().readValue(enderecoJson, EnderecoDto.class);

//         assertEquals("Av. Alm. Silvio de Noronha", actual.getRua(), "Rua inválida");
//         assertEquals("365", actual.getNumero(), "Número do endereço inválido");
//         assertEquals("Centro", actual.getBairro(), "Bairro inválido");
//         assertEquals("Rio de Janeiro", actual.getCidade(), "Cidade inválida");
//         assertEquals(Estado.OUTRO, actual.getEstado(), "Estado inválido");
//     }

//     @Test
//     void enderecoDto_toDomainTest() {
//         Endereco enderecoDomain = enderecoDto.toDomain();

//         assertEquals(enderecoDto.getRua(), enderecoDomain.getRua());
//         assertEquals(enderecoDto.getNumero(), enderecoDomain.getNumero());
//         assertEquals(enderecoDto.getBairro(), enderecoDomain.getBairro());
//         assertEquals(enderecoDto.getCidade(), enderecoDomain.getCidade());
//         assertEquals(enderecoDto.getEstado(), enderecoDomain.getEstado());
//     }

//     @ParameterizedTest
//     @MethodSource("providerForInvalidData")
//     void whenInvalidData_ThenShouldThrow(String rua, int numero, String bairro, String cidade, Estado estado) {

//         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//         Validator validator = factory.getValidator();

//         enderecoDto = new EnderecoDto(rua, numero, bairro, cidade, estado);
//         var validations = validator.validate(enderecoDto);

//         assertTrue(validations.size() > 0);
//     }

//     static Stream<Arguments> providerForInvalidData() {

//         return Stream.of(
//                 Arguments.of(null, numero, bairro, cidade, Estado.RJ),
//                 Arguments.of("Rua válida", null, bairro, cidade, Estado.RJ),
//                 Arguments.of("Rua válida", numero, null, cidade, Estado.RJ),
//                 Arguments.of("Rua válida", numero, bairro, null, Estado.RJ)
//         );
//     }
// }