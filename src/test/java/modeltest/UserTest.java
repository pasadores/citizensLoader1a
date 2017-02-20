package modeltest;

import static org.junit.Assert.*;

import java.util.Date;

import javax.validation.constraints.AssertFalse;

import model.User;

import org.junit.Test;

public class UserTest {

	@Test
	public void testEquals() {
		Date date = new Date(System.currentTimeMillis());
		User user1 = new User("Dani", "Duque", "email@gmail.com", date, "C\\Buenavida", "Español", "7777777R");
		User user2 = new User("Dani", "Duque", "email@gmail.com", date, "C\\Buenavida", "Español", "7777777R");
		User user3 = new User("Dani", "Duque", "email@gmail.com", date, "C\\Buenavida", "Español", "7787777R");
		
		assertEquals(true, user1.equals(user2));
		assertEquals(false, user2.equals(user3));
	}
	
	@Test
	public void testHashCode() {
		Date date = new Date(System.currentTimeMillis());
		User user1 = new User("Dani", "Duque", "email@gmail.com", date, "C\\Buenavida", "Español", "7777777R");
		User user2 = new User("Dani", "Duque", "email@gmail.com", date, "C\\Buenavida", "Español", "7777777R");
		User user3 = new User("Dani", "Duque", "email@gmail.com", date, "C\\Buenavida", "Español", "7787777R");
		
		assertEquals(user1.hashCode(), user2.hashCode());
		assertNotEquals(user2.hashCode(), user3.hashCode());
		
		System.out.println(user1.toString());
	}
	
	@Test
	public void testAll() {
		Date date = new Date(System.currentTimeMillis());
		User user1 = new User("Dani", "Duque", "email@gmail.com", date, "C\\Buenavida", "Español", "7777777R");
		
		String password = user1.getPassword();
		String userName = user1.getUsername();
		String toString = "User [id =null, nombre=Dani, apellidos=Duque, email=email@gmail.com, fechaNacimiento="+date+", direccionPostal=C\\Buenavida, nacionalidad=Español, DNI=7777777R]";
		
		assertEquals("Dani", user1.getNombre());
		assertEquals("Duque", user1.getApellidos());
		assertEquals("email@gmail.com", user1.getEmail());
		assertEquals(date, user1.getFechaNacimiento());
		assertEquals("C\\Buenavida", user1.getDireccionPostal());
		assertEquals("Español", user1.getNacionalidad());
		assertEquals("7777777R", user1.getDNI());
		assertEquals(password, user1.getPassword());
		assertEquals(userName, user1.getUsername());
		assertEquals(toString, user1.toString());
	}

}
