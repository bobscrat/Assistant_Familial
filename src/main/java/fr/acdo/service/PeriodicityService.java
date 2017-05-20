package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Periodicity;
import fr.acdo.repository.PeriodicityRepository;

@Service
public class PeriodicityService {

	private PeriodicityRepository repo;

	@Autowired
	public PeriodicityService(PeriodicityRepository repo) {
		this.repo = repo;
	}

	public List<Periodicity> getAllPeriodicities() {
		return repo.findAll();
	}

	public Periodicity getPeriodicityById(Long id) {
		return repo.findOne(id);
	}

	public Periodicity savePeriodicity(Periodicity periodicity) {
		return repo.save(periodicity);
	}

	public void deletePeriodicity(Long id) {
		repo.delete(id);
	}

}
