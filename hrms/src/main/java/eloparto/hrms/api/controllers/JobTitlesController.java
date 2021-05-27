
  package eloparto.hrms.api.controllers;
  
  import java.util.List;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RestController;
  
  import eloparto.hrms.business.abstracts.JobTitleService; import
  eloparto.hrms.core.utilities.results.DataResult; import
  eloparto.hrms.core.utilities.results.Result; import
  eloparto.hrms.core.utilities.results.SuccessDataResult; import
  eloparto.hrms.entities.concretes.JobTitle;
  
  @RestController
  
  @RequestMapping("/api/jobpositions") public class JobTitlesController {
  
  
  private JobTitleService jobTitleService;
  
  @Autowired public JobTitlesController( JobTitleService jobTitleService) {
  
  this.jobTitleService = jobTitleService; }
  
  @GetMapping("/getall") public DataResult<List<JobTitle>> getAll(){
  
  return new SuccessDataResult<List<JobTitle>>(this.jobTitleService.getAll(),
  null);
  
  }
  
  @PostMapping("/add") public Result add(@RequestBody JobTitle jobTitle) {
  
  return this.jobTitleService.add(jobTitle);
  
  } }
 