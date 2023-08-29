package br.com.techchallenge.energymonitor.repository.eletronico.mapping;

import br.com.techchallenge.energymonitor.dominio.eletronico.Eletronico;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EletronicoRowMapper implements RowMapper<Eletronico> {

    @Override
    public Eletronico mapRow(ResultSet rs, int rowNum) throws SQLException {
        Eletronico eletronico = new Eletronico();

        eletronico.setId(rs.getLong("id"));
        eletronico.setNome(rs.getString("nome"));
        eletronico.setModelo(rs.getString("modelo"));
        eletronico.setPotencia(rs.getInt("potencia"));

        return eletronico;
    }
}
