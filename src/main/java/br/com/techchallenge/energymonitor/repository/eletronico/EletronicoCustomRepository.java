package br.com.techchallenge.energymonitor.repository.eletronico;

import br.com.techchallenge.energymonitor.dominio.eletronico.Eletronico;
import br.com.techchallenge.energymonitor.dominio.eletronico.EletronicoFilter;
import br.com.techchallenge.energymonitor.repository.eletronico.mapping.EletronicoRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EletronicoCustomRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public EletronicoCustomRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Eletronico> findEletronicoFiltered(EletronicoFilter filter) {
        StringBuilder sql = new StringBuilder()
                .append("SELECT id, nome, modelo, potencia FROM eletronico")
                .append("WHERE 1 = 1");

        addCustomParameters(sql, filter);

        MapSqlParameterSource param = new MapSqlParameterSource();
        addCustomValues(param, filter);

        return jdbcTemplate.query(sql.toString(), param, new EletronicoRowMapper());
    }

    private void addCustomParameters(StringBuilder sql, EletronicoFilter filter) {

        if (filter.nome() != null && !filter.nome().isEmpty()) {
            sql.append(" AND nome LIKE :nome");
        }

        if (filter.modelo() != null && !filter.modelo().isEmpty()) {
            sql.append(" AND modelo LIKE :modelo");
        }
    }

    private void addCustomValues(MapSqlParameterSource param, EletronicoFilter filter) {

        if (filter.nome() != null && !filter.nome().isEmpty()) {
            param.addValue("nome", "%" + filter.nome() + "%");
        }

        if (filter.modelo() != null && !filter.modelo().isEmpty()) {
            param.addValue("modelo", "%" + filter.modelo() + "%");
        }
    }
}
