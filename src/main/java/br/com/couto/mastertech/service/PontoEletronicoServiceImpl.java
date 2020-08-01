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

import br.com.couto.mastertech.model.PontoEletronicoModel;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ElectronicPointControlDTO;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.EletronicPointControlVO;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ToPunchTheClockVO;
import br.com.couto.mastertech.repository.PontoEletronicoRepository;
import br.com.couto.mastertech.model.UsuarioModel;
import br.com.couto.mastertech.util.DateUtil;

@Service
public class PontoEletronicoServiceImpl implements PontoEletronicoService {

    @Autowired
    private PontoEletronicoRepository electronicPointControlRepository;

    @Override
    public PontoEletronicoModel save(PontoEletronicoModel electronicPointControl) {
        return electronicPointControlRepository.saveAndFlush(electronicPointControl);
    }

	@Override
	public List<ElectronicPointControlDTO> findByUser(Long idUser) {
		
		UsuarioModel user = new UsuarioModel();
		user.setId(idUser);
		
		List<PontoEletronicoModel> records = electronicPointControlRepository.findByUser(user);

		if (records != null && !records.isEmpty()) {			
			return fillElectronicPointControl(records);
		}
	
		return null;
	}
    
	private List<ElectronicPointControlDTO> fillElectronicPointControl(List<PontoEletronicoModel> electronicPointControl) {
		Map<UsuarioModel, Map<String, List<PontoEletronicoModel>>> map = createMapElectronicPointControl(electronicPointControl);
		
		return fillElectronicPointControlDTO(map);
	}
	
	private List<ElectronicPointControlDTO> fillElectronicPointControlDTO(Map<UsuarioModel, Map<String, List<PontoEletronicoModel>>> map) {
		
		List<ElectronicPointControlDTO> dtos = new ArrayList<>();
		ElectronicPointControlDTO dto = null;
		
		for(Entry<UsuarioModel, Map<String, List<PontoEletronicoModel>>> entry : map.entrySet()) {
			dto = new ElectronicPointControlDTO();
			dto.setUsuario(entry.getKey());
			dto.setEletricPointControl(fillElectronicPointControlVO(entry.getValue()));
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	private List<EletronicPointControlVO> fillElectronicPointControlVO(Map<String, List<PontoEletronicoModel>> map) {
		
		List<EletronicPointControlVO> vos = new ArrayList<>();
		EletronicPointControlVO vo = null;
		for(Entry<String, List<PontoEletronicoModel>> entry : map.entrySet()) {
			vo = new EletronicPointControlVO(entry.getKey(), toPunchTheClock(entry.getValue()));
			vo.setWorkHours(calculoTotalHorasTrabalhadas(vo.getRecord()));
			vos.add(vo);	
		}
		
		return vos;
	}

	private List<ToPunchTheClockVO> toPunchTheClock(List<PontoEletronicoModel> electronicPointControls) {
		List<ToPunchTheClockVO> vos = new ArrayList<>();
		ToPunchTheClockVO vo = null;
		for (PontoEletronicoModel p : electronicPointControls) {
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

	private Map<UsuarioModel, Map<String, List<PontoEletronicoModel>>> createMapElectronicPointControl(List<PontoEletronicoModel> electronicPointControls) {
		Map<UsuarioModel, Map<String, List<PontoEletronicoModel>>> map = new HashMap();
		Map<String, List<PontoEletronicoModel>> mapPointControlUser = null;
		
		UsuarioModel user = null;
		for(PontoEletronicoModel point : electronicPointControls) {
			user = point.getUser();
			
			if (map.get(point.getUser()) != null) {
				mapPointControlUser = map.get(point.getUser());
				fillPointControlUser(mapPointControlUser, point);							
			} else {
				mapPointControlUser = new HashMap<String, List<PontoEletronicoModel>>();
				fillPointControlUser(mapPointControlUser, point);
			}
			map.put(user, mapPointControlUser);
		}
		
		return map;
	}

	private void fillPointControlUser(Map<String, List<PontoEletronicoModel>> map, PontoEletronicoModel point) {
		String pointDate = DateUtil.formatDate(point.getPointRecordDate());
		
		List<PontoEletronicoModel> pointList = null;
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