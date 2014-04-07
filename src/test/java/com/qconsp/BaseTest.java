package com.qconsp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class BaseTest {

	protected List<String> getCeps() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("dez-mil-ceps.txt"));

		List<String> ceps = new ArrayList<String>(800000);
		String cep = null;
		while ((cep = br.readLine()) != null) {
			ceps.add(cep);
		}

		br.close();
		return ceps;
	}

}
