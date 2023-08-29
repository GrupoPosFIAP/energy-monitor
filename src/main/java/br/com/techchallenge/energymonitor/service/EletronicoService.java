package br.com.techchallenge.energymonitor.service;

import br.com.techchallenge.energymonitor.dominio.Domain;
import br.com.techchallenge.energymonitor.dominio.eletronico.Eletronico;
import br.com.techchallenge.energymonitor.dominio.eletronico.EletronicoFilter;
import br.com.techchallenge.energymonitor.dto.ConsumoDTO;
import br.com.techchallenge.energymonitor.dto.Dto;
import br.com.techchallenge.energymonitor.dto.EletronicoDto;
import br.com.techchallenge.energymonitor.repository.BaseRepository;
import br.com.techchallenge.energymonitor.repository.eletronico.EletronicoCustomRepository;
import br.com.techchallenge.energymonitor.repository.eletronico.EletronicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service("eletronicoService")
public class EletronicoService extends DataService<Eletronico> {

    @Autowired
    EletronicoRepository eletronicoRepository;

    @Autowired
    EletronicoCustomRepository customRepository;

    public EletronicoService(BaseRepository<Eletronico> repository) {
        super(repository);
    }

    public void createEletronico(Eletronico eletronico) {
        this.eletronicoRepository.save(eletronico);
    }


    public Eletronico findById(Long id) {
        return this.eletronicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Eletrônico não encontrado."));
    }


    public List<Eletronico> findAll() {
        return this.eletronicoRepository.findAll();
    }


    public void updateEletronico(Long id, EletronicoDto eletronicoDTO) {
        Eletronico eletronico = findById(id);

        eletronico.setNome(eletronicoDTO.getNome());
        eletronico.setModelo(eletronicoDTO.getModelo());
        eletronico.setPotencia(eletronicoDTO.getPotencia());

        this.eletronicoRepository.save(eletronico);
    }


    public List<Dto> findByFilter(EletronicoFilter filter) {
        return customRepository.findEletronicoFiltered(filter).stream().map(Domain::toDto).toList();
    }


    public void deleteEletronico(Long id) {
        Eletronico eletronico = findById(id);
        this.eletronicoRepository.delete(eletronico);
    }



    // CONSUMO
    public void saveConsumo(Long id, ConsumoDTO consumoDTO) {
        Eletronico eletronico = findById(id);
        eletronico.addConsumo(consumoDTO.toDomain());
        eletronicoRepository.save(eletronico);
    }

    public void updateConsumo(Long id, ConsumoDTO consumoDTO) {
        Eletronico eletronico = findById(id);
        eletronico.addConsumo(consumoDTO.toDomain());
        eletronicoRepository.save(eletronico);
    }
}
