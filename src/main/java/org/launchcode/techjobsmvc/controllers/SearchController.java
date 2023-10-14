package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

   // @ResponseBody
    //i had a lot of trouble here due to small errors in the return statement and the configuration of the mapping route.
    //accidentally returning to /search/search/results, and then search/results (by using "/results").
    //Then i remembered that i needed to return the template name and path ("search")
    //and that the post mapping needed to match the template (/search/results)
    @PostMapping("results")
    public String displaySearchResults(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        ArrayList<Job>jobs = new ArrayList<>();
//        if(searchTerm == "all" || searchTerm.isBlank()){
//            jobs = JobData.findAll();
//        }
        //why is this conditional redundant? -- b/c findbycolumnandvalue includes that conditional

        jobs = JobData.findByColumnAndValue(searchType, searchTerm);

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", searchTerm);
        return "search";
        //return "Hello, duck.";
    }

}

