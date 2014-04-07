package com.qconsp.cep;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qconsp.aop.RedisCached;

@Repository
public class CepDao {

	private JdbcTemplate jdbcTemplate;

	
	@Autowired
	public CepDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@RedisCached
	public Cep find(String cep) {
		Cep c = null;
		try {
			c = getCep(cep);
		} catch (Exception e) {
			throw new DaoException();
		}

		return c;
	}


	private Cep getCep(String cep) {
		return this.jdbcTemplate.queryForObject(
		        "select cep, endereco, logradouro, bairro, cidade, uf from endereco_completo where cep = ?",
		        buildCep(), cep);
	}
	
	private RowMapper<Cep> buildCep() {
		return new RowMapper<Cep>() {
		    public Cep mapRow(ResultSet rs, int rowNum) throws SQLException {
		        Cep cep = new Cep();
		        cep.setCep(rs.getString("cep"));
		        cep.setEndereco(rs.getString("endereco"));
		        cep.setLogradouro(rs.getString("logradouro"));
		        cep.setBairro(rs.getString("bairro"));
		        cep.setCidade(rs.getString("cidade"));
		        cep.setUf(rs.getString("uf"));
		        return cep;
		    }
		};
	}

}
