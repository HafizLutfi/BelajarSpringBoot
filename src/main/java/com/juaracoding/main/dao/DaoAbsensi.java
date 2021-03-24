package com.juaracoding.main.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.juaracoding.main.model.Absensi;
import com.juaracoding.main.model.AbsensiRowMapper;
import com.juaracoding.main.model.Biodata;



public class DaoAbsensi {
	@Autowired
	JdbcTemplate jdbc;
	
	public int insertAbsensi(Absensi absensi) {
		return jdbc.update("insert into absensi (id,nik,start_date,end_date) values ("+absensi.getId()+",'"+absensi.getNik()+"',"+absensi.getStart_date()+","+absensi.getEnd_date()+")");		
	}
	
	public List<Absensi> getAbsensi() {
		
		String sql = "Select * from absensi";
		
		List <Absensi> absensi =  jdbc.query(sql,new AbsensiRowMapper());
		
		return absensi;
		
		
	}
	
	public int updateBiodata(String nik, Biodata biodata) {
		return 0;

	}
	
	public int deleteBiodata(String nik, Biodata biodata) {
		return 0;
	}

	
	
}
