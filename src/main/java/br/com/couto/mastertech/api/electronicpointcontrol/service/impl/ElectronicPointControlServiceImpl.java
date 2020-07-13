package br.com.couto.mastertech.api.electronicpointcontrol.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.couto.mastertech.api.electronicpointcontrol.model.ElectronicPointControl;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ElectronicPointControlDTO;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.EletronicPointControlVO;
import br.com.couto.mastertech.api.electronicpointcontrol.pojo.ToPunchTheClockVO;
import br.com.couto.mastertech.api.electronicpointcontrol.repository.ElectronicPointControlRepository;
import br.com.couto.mastertech.api.electronicpointcontrol.service.ElectronicPointControlService;
import br.com.couto.mastertech.api.user.model.User;
import br.com.couto.mastertech.util.DateUtil;

@Service
public class ElectronicPointControlServiceImpl implements ElectronicPointControlService {

    @Autowired
    private ElectronicPointControlRepository electronicPointControlRepository;

    @Override
    public ElectronicPointControl save(ElectronicPointControl electronicPointControl) {	
        return electronicPointControlRepository.saveAndFlush(electronicPointControl);
    }

	@Override
	public List<ElectronicPointControlDTO> findByUser(Long idUser) {
		
		User user = new User();
		user.setId(idUser);
		
		List<ElectronicPointControl> records = electronicPointControlRepository.findByUser(user);

		if (records != null && !records.isEmpty()) {			
			return fillElectronicPointControl(records);
		}
	
		return null;
	}
    
	private List<ElectronicPointControlDTO> fillElectronicPointControl(List<ElectronicPointControl> electronicPointControl) {
		Map<User, Map<String, List<ElectronicPointControl>>> map = createMapElectronicPointControl(electronicPointControl);
		
		return fillElectronicPointControlDTO(map);
	}
	
	private List<ElectronicPointControlDTO> fillElectronicPointControlDTO(Map<User, Map<String, List<ElectronicPointControl>>> map) {
		
		List<ElectronicPointControlDTO> dtos = new ArrayList<>();
		ElectronicPointControlDTO dto = null;
		
		for(Entry<User, Map<String, List<ElectronicPointControl>>> entry : map.entrySet()) {
			dto = new ElectronicPointControlDTO();
			dto.setUsuario(entry.getKey());
			dto.setEletricPointControl(fillElectronicPointControlVO(entry.getValue()));
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	private List<EletronicPointControlVO> fillElectronicPointControlVO(Map<String, List<ElectronicPointControl>> map) {
		
		List<EletronicPointControlVO> vos = new ArrayList<>();
		EletronicPointControlVO vo = null;
		for(Entry<String, List<ElectronicPointControl>> entry : map.entrySet()) {
			vo = new EletronicPointControlVO(entry.getKey(), toPunchTheClock(entry.getValue()));
			vo.setWorkHours(calculoTotalHorasTrabalhadas(vo.getRecord()));
			vos.add(vo);	
		}
		
		return vos;
	}

	private List<ToPunchTheClockVO> toPunchTheClock(List<ElectronicPointControl> electronicPointControls) {
		List<ToPunchTheClockVO> vos = new ArrayList<>();
		ToPunchTheClockVO vo = null;
		for (ElectronicPointControl p : electronicPointControls) {
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

	private Map<User, Map<String, List<ElectronicPointControl>>> createMapElectronicPointControl(List<ElectronicPointControl> electronicPointControls) {
		Map<User, Map<String, List<ElectronicPointControl>>> map = new HashMap();
		Map<String, List<ElectronicPointControl>> mapPointControlUser = null;
		
		User user = null;
		for(ElectronicPointControl point : electronicPointControls) {
			user = point.getUser();
			
			if (map.get(point.getUser()) != null) {
				mapPointControlUser = map.get(point.getUser());
				fillPointControlUser(mapPointControlUser, point);							
			} else {
				mapPointControlUser = new HashMap<String, List<ElectronicPointControl>>();					 
				fillPointControlUser(mapPointControlUser, point);
			}
			map.put(user, mapPointControlUser);
		}
		
		return map;
	}

	private void fillPointControlUser(Map<String, List<ElectronicPointControl>> map, ElectronicPointControl point) {
		String pointDate = DateUtil.formatDate(point.getPointRecordDate());
		
		List<ElectronicPointControl> pointList = null;
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