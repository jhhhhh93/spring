package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import kr.or.ddit.util.model.FileInfo;

public class FileUtil {

	public static FileInfo getFileInfo(String originalFileName) {
		String path = getPath(); 
		
		String uuidFileName = UUID.randomUUID().toString();
		String extName = getExtension(originalFileName);
		File file = new File(path + "\\" + uuidFileName + extName);
		
		FileInfo fileInfo = new FileInfo(file, extName, originalFileName);
		
		return fileInfo;
	}

	private static String getPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String yyyyMM = sdf.format(new Date());
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6);
		
		String path = "D:\\springUpload\\" + yyyy + "\\" + mm;
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdirs();
		}
		return path;
	}

	private static String getExtension(String originalFileName) {
		//확장자가 없을 경우 공백을 리턴
		//파일명에 .이 여러개 등자할 경우 가장 마지막에 있는 녀석이 확장자 구분자
		int index = originalFileName.lastIndexOf(".");
		if(index == -1) {
			return "";
		}
		String extName = originalFileName.substring(index);
		return extName;
	}
		
}
