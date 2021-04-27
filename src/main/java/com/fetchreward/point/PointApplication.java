package com.fetchreward.point;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PointApplication {

    public static void main(String[] args) {
        SpringApplication.run(PointApplication.class, args);
    }

}

//         { "payer": "DANNON", "points": 300, "timestamp": "2020-10-31T10:00:00Z" }
//         { "payer": "UNILEVER", "points": 200, "timestamp": "2020-10-31T11:00:00Z" }
//         { "payer": "DANNON", "points": -200, "timestamp": "2020-10-31T15:00:00Z" }
//         { "payer": "MILLER COORS", "points": 10000, "timestamp": "2020-11-01T14:00:00Z" }
//         { "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }

// Dannon: -300 -> 4700
// Unilever: -200 -> 4500
// Dannon: -100 -> 4700
//