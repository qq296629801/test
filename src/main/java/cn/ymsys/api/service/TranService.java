package cn.ymsys.api.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

@Service
public class TranService {

	public void save(String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Record file = new Record();
		file.set("file_name", fileName);
		file.set("upload_date", sdf.format(new Date()));
		Db.save("files", file);
		file = findFileByName(fileName);
		Record trans = new Record();
		trans.set("file_id", file.getInt("id"));
		Db.save("trans", trans);
	}

	public Record findFileByName(String fileName) {
		return Db.findFirst("select * from files where file_name='" + fileName + "'");
	}

	public List<Record> getTrans() {
		String sql = "select s.*,f.file_name,f.id as fid from trans s LEFT JOIN files f on f.id=s.file_id";
		return Db.find(sql);
	}

	public void del(int id) {
		Db.deleteById("trans", id);
	}

	public void updateFile(int id) {
		Record record = new Record();
		record.set("id", id);
		record.set("state", 1);
		Db.update("files", record);
	}
}
