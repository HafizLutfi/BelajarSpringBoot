package com.juaracoding.main.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.main.model.Absensi;
import com.juaracoding.main.model.AbsensiRowMapper;

@RestController
@RequestMapping("/absensi")
public class AbsensiController {
	
	@Autowired
	JdbcTemplate jdbc;
	
	public List<Absensi> getAbsensi(String start_date, String end_date, Absensi absensi){
		
		String sql= "select * from Absensi WHERE YEAR(start_date) ='"+absensi.getStart_date()+"'AND YEAR(end_date)='"+absensi.getEnd_date()+"'";
		List<Absensi> absen = jdbc.query(sql, new AbsensiRowMapper());
		return absen;
	}
	

	public int insertAbsensi(Absensi absensi) {
		return jdbc.update("insert into absensi(id,nik,start_date,end_date) values ("+absensi.getId()+",'"+absensi.getNik()+"','"+absensi.getStart_date()+"','"+absensi.getEnd_date()+"')");

	}

	public int updateAbsensi(int id, Absensi absensi) {

		return jdbc.update("UPDATE absensi SET nik='"+absensi.getNik()+"',start_date="+absensi.getStart_date()+",end_date="+absensi.getEnd_date()+" WHERE id="+id+"");

	}

	public int deleteBiodata(int id) {
		return jdbc.update("DELETE FROM absensi WHERE id = '" + id + "';");
	}

	
	

	 @PostMapping("/")
	    public String add(@RequestBody Absensi absensi) {
		 

			if (this.insertAbsensi(absensi) == 1) {
				return "Insert data berhasil";
			} else {
				return "Insert data gagal";
			}
	    }
	 
	 
	 
	 @DeleteMapping("/{id}")
	    public void delete(@PathVariable int id) {
		 	deleteBiodata(id);
	 }
	 
	 
	 @GetMapping("/{start_date}{end_date}")
	    public List<Absensi> list(@PathVariable String start_date, String end_date,@RequestBody Absensi absensi) {
	        return getAbsensi(start_date,end_date, absensi);
	    }
	 
	 @PutMapping("/{nik}")
	    public ResponseEntity<?> update(@RequestBody Absensi absensi, @PathVariable int id) {
		 try {
	            updateAbsensi(id, absensi);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
		 
	 }
	

	
}
