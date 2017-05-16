package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Periodicity;
import fr.acdo.repository.PeriodicityRepository;

@Service
public class PeriodicityService {

	private PeriodicityRepository repoPeriod;

	@Autowired
	public PeriodicityService(PeriodicityRepository repoPeriod) {
		this.repoPeriod = repoPeriod;
	}

	public List<Periodicity> getAllPeriodicities() {
		return repoPeriod.findAll();
	}

	public Periodicity getPeriodicityById(Long id) {
		return repoPeriod.findOne(id);
	}

	public Periodicity savePeriodicity(Periodicity periodicity) {
		return repoPeriod.save(periodicity);
	}

	public void deletePeriodicity(Long id) {
		repoPeriod.delete(id);
	}

}
