package com.tcs.config;

import com.tcs.dto.RegisterRequest;
import com.tcs.entity.RoomType;
import com.tcs.entity.Role;
import com.tcs.dto.HotelRequest;
import com.tcs.dto.RoomRequest;
import com.tcs.service.AuthService;
import com.tcs.service.HotelService;
import com.tcs.service.RoomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(AuthService authService, HotelService hotelService, RoomService roomService) {
        return args -> {
            // Create admin and customer
            var admin = new RegisterRequest();
            admin.email = "admin@example.com"; admin.password = "Admin@123";
            admin.fullName = "Admin User"; admin.phone = "9999999999";
            authService.register(admin, Role.ADMIN);

            var cust = new RegisterRequest();
            cust.email = "customer@example.com"; cust.password = "Customer@123";
            cust.fullName = "Customer One"; cust.phone = "8888888888";
            authService.register(cust, Role.CUSTOMER);

            // Create a hotel
            var hreq = new HotelRequest();
            hreq.name = "Bengaluru Grand";
            hreq.address = "MG Road";
            hreq.city = "Bangalore";
            hreq.description = "Business hotel in city center";
            var hotel = hotelService.create(hreq);

            // Create rooms
            for (int i = 101; i <= 103; i++) {
                var r = new RoomRequest();
                r.hotelId = hotel.getId();
                r.number = String.valueOf(i);
                r.type = i == 103 ? RoomType.SUITE : RoomType.DOUBLE;
                r.capacity = (i == 103 ? 3 : 2);
                r.pricePerNight = i == 103 ? 5000.0 : 3000.0;
                r.outOfService = false;
                roomService.create(r);
            }
        };
    }
}
