package springframework.library;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) throws SQLException {
		Console.main(args);
		disableWarning();
		SpringApplication.run(LibraryApplication.class, args);
	}

	public static void disableWarning() {
		System.err.close();
		System.setErr(System.out);
	}
}
