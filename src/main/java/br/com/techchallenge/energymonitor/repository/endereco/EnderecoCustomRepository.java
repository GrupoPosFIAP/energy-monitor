package br.com.techchallenge.energymonitor.repository.endereco;

import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import br.com.techchallenge.energymonitor.dominio.endereco.EnderecoFilter;
import br.com.techchallenge.energymonitor.repository.endereco.mapping.EnderecoRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EnderecoCustomRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public EnderecoCustomRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Endereco> findEnderecoFiltered(EnderecoFilter filter) {

        StringBuilder ql = new StringBuilder()
                .append("SELECT id, rua, numero, bairro, cidade, estado FROM endereco ")
                .append("WHERE 1 = 1");

        addCustomParameters(ql, filter);

        MapSqlParameterSource param = new MapSqlParameterSource();
        addCustomValues(param, filter);



        return jdbcTemplate.query(ql.toString(), param, new EnderecoRowMapper());

    }

    private void addCustomParameters(StringBuilder ql, EnderecoFilter filter) {
        if(filter.bairro() != null && !filter.bairro().isEmpty()) {
            ql.append(" AND bairro LIKE :bairro");
        }
        if(filter.rua() != null && !filter.rua().isEmpty()) {
            ql.append(" AND rua LIKE :rua");
        }
        if(filter.cidade() != null && !filter.cidade().isEmpty()) {
            ql.append(" AND cidade LIKE :cidade");
        }
        if(filter.estado() != null) {
            ql.append(" AND estado LIKE :estado");
        }
    }

    private void addCustomValues(MapSqlParameterSource param, EnderecoFilter filter) {
        if(filter.rua() != null && !filter.rua().isEmpty()) {
            param.addValue("rua", "%"+filter.rua()+"%");
        }
        if(filter.estado() != null) {
            param.addValue("estado", "%"+filter.estado()+"%");
        }
        if(filter.cidade() != null &&  !filter.cidade().isEmpty() ) {
            param.addValue("cidade", "%"+filter.cidade()+"%");
        }
        if(filter.bairro() != null &&  !filter.bairro().isEmpty()) {
            param.addValue("bairro", "%"+filter.bairro()+"%");
        }
    }

}
