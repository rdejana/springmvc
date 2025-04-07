package org.rdejana.springmvc.controller;

import jakarta.validation.Valid;
import org.rdejana.springmvc.dto.ClubDto;
import org.rdejana.springmvc.model.Club;
import org.rdejana.springmvc.repository.ClubRepository;
import org.rdejana.springmvc.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//useful validation link: https://www.baeldung.com/java-bean-validation-not-null-empty-blank


@Controller
public class ClubController {

    private final ClubRepository clubRepository;
    private ClubService clubService; //don't need to use Autowire

    public ClubController(ClubService clubService, ClubRepository clubRepository) {
        this.clubService = clubService;
        this.clubRepository = clubRepository;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);

        return "clubs-list";
    }

    @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam(value="query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createCloudForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(  @Valid @ModelAttribute("club") ClubDto clubDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs"; //cool
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClub(@PathVariable Long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("club", club);
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }


    @GetMapping("/clubs/{clubId}")
    public String clubDetails(@PathVariable("clubId") Long clubId,Model model) {
        ClubDto clubDto = clubService.findClubById(clubId);
        System.out.println(clubDto);
        model.addAttribute("club", clubDto);
        return "clubs-details";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
      clubService.deleteClub(clubId);

        return "redirect:/clubs";
    }

}
