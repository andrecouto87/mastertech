package br.com.couto.mastertech.service.pontoeletronico;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import br.com.couto.mastertech.model.MarcacaoModel;
import br.com.couto.mastertech.model.PontoEletronicoModel;
import br.com.couto.mastertech.model.TipoMarcacao;
import br.com.couto.mastertech.repository.UsuarioRepository;
import br.com.couto.mastertech.util.DateUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.couto.mastertech.entity.Usuario;
import br.com.couto.mastertech.repository.PontoEletronicoRepository;
import br.com.couto.mastertech.entity.PontoEletronico;

@Service
public class PontoEletronicoServiceImpl implements PontoEletronicoService {

    @Autowired
    private PontoEletronicoRepository pontoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void register(Long idUsuario) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (!usuario.isPresent()) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        PontoEletronico pontoEletronico = new PontoEletronico();
        pontoEletronico.setUsuario(usuario.get());

        List<PontoEletronico> marcacoes = pontoRepository.findByUsuarioId(idUsuario)
                .stream().filter(item -> DateUtil.isToday(item.getData())).collect(Collectors.toList());

        if (marcacoes.size() == 0) {
            pontoEletronico.setTipoMarcacao(TipoMarcacao.ENTRADA);
        } else {
            if (marcacoes.get(marcacoes.size() - 1).getTipoMarcacao().equals(TipoMarcacao.ENTRADA)) {
                pontoEletronico.setTipoMarcacao(TipoMarcacao.SAIDA);
            } else {
                pontoEletronico.setTipoMarcacao(TipoMarcacao.ENTRADA);
            }
        }
        pontoRepository.save(pontoEletronico);
    }

    public List<PontoEletronicoModel> findAll(Long idUsuario) {
        Map<Date, List<PontoEletronico>> resultMap = pontoRepository.findByUsuarioId(idUsuario).stream()
                .collect(Collectors.groupingBy(PontoEletronico::getData));

        List<PontoEletronicoModel> result = new ArrayList<>();

        for (Map.Entry<Date, List<PontoEletronico>> entry : resultMap.entrySet()) {
            PontoEletronicoModel ponto = new PontoEletronicoModel();
            ponto.setData(entry.getKey());
            ponto.setMarcacoes(entry.getValue().stream().map(item -> {
                MarcacaoModel marcacao = new MarcacaoModel();
                marcacao.setHora(item.getHora());
                marcacao.setTipoMarcacao(item.getTipoMarcacao());
                return marcacao;
            }).collect(Collectors.toList()));

            ponto.setHorasTrabalhadas(getHorasTrabalhadas(ponto.getData(), ponto.getMarcacoes()));

            result.add(ponto);
        }
        return result;
    }

    private String getHorasTrabalhadas(Date data, List<MarcacaoModel> marcacoes) {
        long segundosTrabalhados = 0;
        for (int i = 0; i < marcacoes.size(); i++) {
            MarcacaoModel item = marcacoes.get(i);
            if (item.getTipoMarcacao().equals(TipoMarcacao.ENTRADA)) {
                try {
                    MarcacaoModel marcacaoSaida = marcacoes.get(i + 1);
                    if (marcacaoSaida.getTipoMarcacao().equals(TipoMarcacao.SAIDA)) {
                        long duration = marcacaoSaida.getHora().getTime() - item.getHora().getTime();
                        segundosTrabalhados += TimeUnit.MILLISECONDS.toSeconds(duration);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    long duration = DateUtil.getEndDate(data).getTime() - item.getHora().getTime();
                    segundosTrabalhados += TimeUnit.MILLISECONDS.toSeconds(duration);
                }
            }
        }

        return LocalTime.ofSecondOfDay(segundosTrabalhados).toString();
    }
}