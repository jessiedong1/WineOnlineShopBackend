package com.wineshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.wineshop.model.Region;
import com.wineshop.model.Wine;


@CrossOrigin()
@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {

	Page<Wine> findById(@RequestParam("id") Long id, Pageable pageable);
	Page<Wine> findByTitleContaining(@RequestParam("name") String name, Pageable pageable);
	Page<Wine> findByRegionRegionContaining(@RequestParam("region") String region, Pageable pageable);
	Page<Wine> findByRegionCountry(@RequestParam("country") String country, Pageable pageable);
	Page<Wine> findByVarietyGrapeType(@RequestParam("grapetype") String grapetype, Pageable pageable);
	Page<Wine> findByVarietyWineType(@RequestParam("winetype") String winetype, Pageable pageable);
	
	@Query("select wi from Wine wi, Variety va where wi.variety=va.id and va.grapeType=:grapetype and va.wineType=:winetype")
	Page<Wine> findByVariety(@RequestParam("grapetype")String grapetype, @RequestParam("winetype")String winetype, Pageable pageable);
	
	

}
