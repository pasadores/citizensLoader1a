package dbupdate;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import model.User;
import persistence.UserFinder;
import persistence.util.Jpa;
import reportwriter.ReportWriter;

public class InsertP implements Insert {

	@Override
	public User save(User user) {
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();
		try {
			if (!UserFinder.findByDNI(user.getDNI()).isEmpty()
				|| UserFinder.findByEmail(user.getEmail()).isEmpty()){
				ReportWriter.getInstance().getWriteReport().log(Level.WARNING,
						"El usuario ya ha sido registrado anteriormente " + "debido a que aparecia en otra lista");
			}else {
				Jpa.getManager().persist(user);
				trx.commit();
			}
				trx.rollback();			
		} catch (PersistenceException ex) {
			ReportWriter.getInstance().getWriteReport().log(Level.WARNING, "Error de la BBDD");
			if (trx.isActive())
				trx.rollback();
		} finally {
			if (mapper.isOpen())
				mapper.close();
		}
		return user;
	}

	@Override
	public List<User> findByDNI(String dni) {
		return UserFinder.findByDNI(dni);
	}

	@Override
	public List<User> findByEmail(String email) {
		return UserFinder.findByEmail(email);
	}
}
