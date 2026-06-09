package com.kasaflex.api.Repositories.station;

import com.kasaflex.api.Entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, String> {
}
