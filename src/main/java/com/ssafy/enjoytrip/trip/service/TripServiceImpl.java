package com.ssafy.enjoytrip.trip.service;

import com.ssafy.enjoytrip.trip.dao.TripDao;
import com.ssafy.enjoytrip.trip.dto.Trip;
import com.ssafy.enjoytrip.trip.dto.TripAttraction;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService{

    private final TripDao tripDao;

    public TripServiceImpl(TripDao tripDao) {
        this.tripDao = tripDao;
    }

    @Override
    public int makeTrip(Trip trip) {
        return tripDao.insertTrip(trip);
    }

    @Override
    public void makeTripAttractions(List<TripAttraction> request) {
        tripDao.insertTripAttraction(request);
    }
}
