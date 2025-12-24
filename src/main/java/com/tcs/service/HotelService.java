package com.tcs.service;

import com.tcs.dto.AuthResponse;
import com.tcs.dto.LoginRequest;
import com.tcs.dto.RegisterRequest;
import com.tcs.entity.Role;
import com.tcs.entity.UserAccount;
import com.tcs.exception.BadRequestException;
import com.tcs.exception.NotFoundException;
import com.tcs.repository.UserRepository;
import com.tcs.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;



import com.tcs.dto.HotelRequest;
import com.tcs.entity.Hotel;
import com.tcs.exception.NotFoundException;
import com.tcs.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository repo;
    public HotelService(HotelRepository repo) { this.repo = repo; }

    public Hotel create(HotelRequest req) {
        Hotel h = new Hotel();
        h.setName(req.name); h.setAddress(req.address); h.setCity(req.city); h.setDescription(req.description);
        return repo.save(h);
    }

    public List<Hotel> list(String city) {
        return city == null ? repo.findAll() : repo.findByCityIgnoreCase(city);
    }

    public Hotel get(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Hotel not found"));
    }

    public Hotel update(Long id, HotelRequest req) {
        Hotel h = get(id);
        h.setName(req.name); h.setAddress(req.address); h.setCity(req.city); h.setDescription(req.description);
        return repo.save(h);
    }

    public void delete(Long id) { repo.deleteById(id); }
}

