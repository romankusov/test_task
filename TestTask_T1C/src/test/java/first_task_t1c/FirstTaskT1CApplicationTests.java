package first_task_t1c;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@AutoConfigureMockMvc
@SpringBootTest
class FirstTaskT1CApplicationTests {

	private FirstTaskT1CApplication application;

	@Autowired
	FirstTaskT1CApplicationTests(FirstTaskT1CApplication application) {
		this.application = application;
	}


	@Test
	void contextLoads()
	{
		assertNotNull(application);
	}


}
