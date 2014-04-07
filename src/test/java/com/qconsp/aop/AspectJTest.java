package com.qconsp.aop;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qconsp.BaseTest;

public class AspectJTest extends BaseTest {

	@Autowired
	ObjetoParaSerAspectado transfer;

	
	@Test
	public void testName() throws Exception {
		transfer.metodoParaSerAspectado();
	}
}
