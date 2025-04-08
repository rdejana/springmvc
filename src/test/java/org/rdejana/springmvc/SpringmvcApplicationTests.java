package org.rdejana.springmvc;

import org.junit.jupiter.api.Test;
import org.rdejana.springmvc.controller.ClubController;
import org.rdejana.springmvc.model.Club;
import org.rdejana.springmvc.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringmvcApplicationTests {
    @Autowired
    private ClubController clubController;
    @Autowired
    private ClubRepository clubRepository;

    @Test
    void contextLoads() {
        System.out.println(clubController);
        List<Club> clubs =  clubRepository.findAll();
        for (Club club : clubs) {
            System.out.println(club.getId());
        }


    }

    @Test
    void yetAnotherTest(){
        List<Club> clubs =  clubRepository.findAll();
        for (Club club : clubs) {
            System.out.println(club.getId() + " " + club.getTitle());
        }
    }

}
