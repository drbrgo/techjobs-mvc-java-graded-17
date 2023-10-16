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
//        model.addAttribute(new Job());
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

   // @ResponseBody
    //i had a lot of trouble here due to small errors in the return statement and the configuration of the mapping route.
    //accidentally returning to /search/search/results, and then search/results (by using "/results").
    //Then i remembered that i needed to return the template name and path ("search")
    //and that the post mapping needed to match the post action of the template (/search/results)
    @PostMapping("results")
    public String displaySearchResults(Model model, String searchType, String searchTerm) {
        ArrayList<Job>jobs = new ArrayList<>();
//        if(searchTerm == "all" || searchTerm.isBlank()){
//            jobs = JobData.findAll();
//        }
        //why is this conditional redundant? -- b/c findbycolumnandvalue includes that conditional

        jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        String choice = columnChoices.values().toString().toLowerCase();

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", searchTerm);
        model.addAttribute("choice", choice);
        //trying to pass in a new variable to the model so that I can ensure the previous search field remains
        //selected after submitting the form. so far no luck.
        return "search";
        //return "Hello, duck.";
    }

}

