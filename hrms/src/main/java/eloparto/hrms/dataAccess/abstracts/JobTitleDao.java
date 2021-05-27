
  package eloparto.hrms.dataAccess.abstracts;
  
  import java.util.List;
  
  import org.springframework.data.jpa.repository.JpaRepository;

  import eloparto.hrms.entities.concretes.JobTitle; 

  
  public interface JobTitleDao extends JpaRepository<JobTitle, Integer> {
  
  List<JobTitle> findAllByTitleName(String title);
  
  }
 