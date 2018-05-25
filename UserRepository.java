package surreyjson.jsondb.repository;




import surreyjson.jsondb.domain.User;
import org.springframework.data.repository.CrudRepository;


//import com.techptinmers.springmvcpostexample.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends CrudRepository<User, Long> {

}
