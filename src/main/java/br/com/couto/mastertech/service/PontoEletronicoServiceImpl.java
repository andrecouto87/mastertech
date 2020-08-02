package br.com.couto.mastertech.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.couto.mastertech.entity.PontoEletronico;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ElectronicPointControlDTO;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.EletronicPointControlVO;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ToPunchTheClockVO;
import br.com.couto.mastertech.repository.PontoEletronicoRepository;
import br.com.couto.mastertech.entity.Usuario;
import br.com.couto.mastertech.util.DateUtil;

@Service
public class PontoEletronicoServiceImpl implements PontoEletronicoService {

    @Autowired
    private PontoEletronicoRepository electronicPointControlRepository;

    @Override
    public PontoEletronico save(PontoEletronico electronicPointControl) {
        return electronicPointControlRepository.saveAndFlush(electronicPointControl);
    }

	@Override
	public List<ElectronicPointControlDTO> findByUser(Long idUser) {
		
		Usuario user = new Usuario();
		user.setId(idUser);
		
		List<PontoEletronico> records = electronicPointControlRepository.findByUser(user);

		if (records != null && !records.isEmpty()) {			
			return fillElectronicPointControl(records);
		}
	
		return null;
	}
    
	private List<ElectronicPointControlDTO> fillElectronicPointControl(List<PontoEletronico> electronicPointControl) {
		Map<Usuario, Map<String, List<PontoEletronico>>> map = createMapElectronicPointControl(electronicPointControl);
		
		return fillElectronicPointControlDTO(map);
	}
	
	private List<ElectronicPointControlDTO> fillElectronicPointControlDTO(Map<Usuario, Map<String, List<PontoEletronico>>> map) {
		
		List<ElectronicPointControlDTO> dtos = new ArrayList<>();
		ElectronicPointControlDTO dto = null;
		
		for(Entry<Usuario, Map<String, List<PontoEletronico>>> entry : map.entrySet()) {
			dto = new ElectronicPointControlDTO();
			dto.setUsuario(entry.getKey());
			dto.setEletricPointControl(fillElectronicPointControlVO(entry.getValue()));
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	private List<EletronicPointControlVO> fillElectronicPointControlVO(Map<String, List<PontoEletronico>> map) {
		
		List<EletronicPointControlVO> vos = new ArrayList<>();
		EletronicPointControlVO vo = null;
		for(Entry<String, List<PontoEletronico>> entry : map.entrySet()) {
			vo = new EletronicPointControlVO(entry.getKey(), toPunchTheClock(entry.getValue()));
			vo.setWorkHours(calculoTotalHorasTrabalhadas(vo.getRecord()));
			vos.add(vo);	
		}
		
		return vos;
	}

	private List<ToPunchTheClockVO> toPunchTheClock(List<PontoEletronico> electronicPointControls) {
		List<ToPunchTheClockVO> vos = new ArrayList<>();
		ToPunchTheClockVO vo = null;
		for (PontoEletronico p : electronicPointControls) {
			vo = new ToPunchTheClockVO(DateUtil.formatTime(p.getPointRecordDate()), p.getPointRecordType());
			vos.add(vo);
		}
		return vos;
	}
	
	private LocalTime calculoTotalHorasTrabalhadas(List<ToPunchTheClockVO> punches) {
		
		List<Date> times = new ArrayList<>();
		Date t = null;
		for (ToPunchTheClockVO vo : punches) {
			t = DateUtil.parseTime(vo.getRecord());
			times.add(t);
		}
		
		Date dtEntradaA = times.get(0);
		Date dtSaidaA = times.get(1);
		Date dtEntradaP = times.get(2);
		Date dtSaidaP = times.get(3);
		
		long diffA = dtSaidaA.getTime() - dtEntradaA.getTime();
		long diffP = dtSaidaP.getTime() - dtEntradaP.getTime();
		
		long diffHours = (diffA/(60*60*1000) % 24) + (diffP/(60*60*1000) % 24);
		long diffMin = (diffA/(60*1000) % 60) + (diffP/(60*1000) % 60);
		
		return LocalTime.of(Long.valueOf(diffHours).intValue(), Long.valueOf(diffMin).intValue());
	}

	private Map<Usuario, Map<String, List<PontoEletronico>>> createMapElectronicPointControl(List<PontoEletronico> electronicPointControls) {
		Map<Usuario, Map<String, List<PontoEletronico>>> map = new HashMap();
		Map<String, List<PontoEletronico>> mapPointControlUser = null;
		
		Usuario user = null;
		for(PontoEletronico point : electronicPointControls) {
			user = point.getUser();
			
			if (map.get(point.getUser()) != null) {
				mapPointControlUser = map.get(point.getUser());
				fillPointControlUser(mapPointControlUser, point);							
			} else {
				mapPointControlUser = new HashMap<String, List<PontoEletronico>>();
				fillPointControlUser(mapPointControlUser, point);
			}
			map.put(user, mapPointControlUser);
		}
		
		return map;
	}

	private void fillPointControlUser(Map<String, List<PontoEletronico>> map, PontoEletronico point) {
		String pointDate = DateUtil.formatDate(point.getPointRecordDate());
		
		List<PontoEletronico> pointList = null;
		if (map.get(pointDate) != null) {
			pointList = map.get(pointDate);
			pointList.add(point);
		} else {
			pointList = new ArrayList();
			pointList.add(point);
		}
		 
		map.put(pointDate, pointList);	
	}
}