package com.application.psm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.application.psm.model.Sector;
import com.application.psm.repository.SectorRepository;
import com.application.psm.service.SectorService;

@Service
public class SectorServiceImp implements SectorService{
	@Autowired
	private SectorRepository sectorRepository;
	
	@Override
	public List<Sector> getAllSectors() {
		// TODO Auto-generated method stub
		return sectorRepository.findAll();
	}

	@Override
	public Sector saveSector(Sector sector) {
		// TODO Auto-generated method stub
		return sectorRepository.save(sector);
	}

	@Override
	public void deleteSector(Long id) {
		// TODO Auto-generated method stub
		sectorRepository.deleteById(id);
	}

	@Override
	public Sector editSector(Sector sector) {
		// TODO Auto-generated method stub
		return sectorRepository.save(sector);
	}

	@Override
	public Sector getSectorById(Long id) {
		// TODO Auto-generated method stub
		return sectorRepository.findById(id).get();
	}

}
