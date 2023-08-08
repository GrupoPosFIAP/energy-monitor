package br.com.techchallenge.energymonitor.repository;

import br.com.techchallenge.energymonitor.dominio.Endereco;

public interface EnderecoRepository extends BaseRepository<Endereco> {

//    @Query("SELECT obj
//            FROM   Endereco obj
//            WHERE  obj.rua    = :rua
//            OR     obj.numero = :numero
//            OR     obj.bairro = :bairro
//            OR     obj.cidade = :cidade
//            OR     obj.estado = :estado" )
//    Page<Endereco> searchEndereco(String rua, int numero, String bairro, String cidade, String estado);

}
