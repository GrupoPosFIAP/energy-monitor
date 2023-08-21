package br.com.techchallenge.energymonitor.repository.consumo;

import br.com.techchallenge.energymonitor.dominio.consumo.ConsumoUpdater;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.Instant;

@Repository
public class ConsumoCalculadoRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private Instant fimFuncionamento = Instant.now();

    public ConsumoCalculadoRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Instant consultaInicio(Long id, ConsumoUpdater updated) {

        StringBuilder sqlConsultaInicio = new StringBuilder().
                append(" SELECT inicioFuncionamento " +
                       " FROM  tb_consumo           " +
                       " WHERE id_consumo         = " + id +
                       " AND   id_eletronico      = " + id + " ;");

        return jdbcTemplate.query(sqlConsultaInicio.toString());
    }

    public Instant consultaPotencia(Long id, ConsumoUpdater updated) {

        StringBuilder sqlConsultaPotencia = new StringBuilder().
                append(" SELECT potencia        " +
                        " FROM  tb_eletronico   " +
                        " WHERE id_eletronico = " + id + " ;");

        return jdbcTemplate.query(sqlConsultaPotencia.toString());
    }

    public void atualizarConsumo(Long id, ConsumoUpdater updated) {

        var tempoConsumo = fimFuncionamento - updated.inicioFuncionamento();
        var energiaConsumo = tempoConsumo * potencia;

        StringBuilder sqlAtualiza = new StringBuilder().
                append(" UPDATE tb_consumo " +
                       " SET    fimFuncionamento = " + fimFuncionamento +
                       " ,      tb_consumo       = " + energiaConsumo   +
                       " WHERE  id_consumo       = " + id               +
                       " AND    id_eletronico    = " + id               + " ;");

        return jdbcTemplate.query(sqlAtualiza.toString());
    }
}
