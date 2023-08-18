package br.com.techchallenge.energymonitor.repository.endereco.mapping;

import br.com.techchallenge.energymonitor.dominio.endereco.Endereco;
import br.com.techchallenge.energymonitor.dominio.enums.Estado;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EnderecoRowMapper implements RowMapper<Endereco> {


    @Override
    public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {
        Endereco endereco = new Endereco();

        endereco.setRua(rs.getString("rua"));
        endereco.setNumero(rs.getInt("numero"));
        String estado = rs.getString("estado");
        endereco.setEstado(estado != null ? Estado.valueOf(estado) : null);
        endereco.setCidade(rs.getString("cidade"));
        endereco.setId(rs.getLong("id"));
        endereco.setBairro(rs.getString("bairro"));

        return endereco;
    }
}
