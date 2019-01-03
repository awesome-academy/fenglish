package vn.framgia.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import vn.framgia.model.Level;
import vn.framgia.model.Question;
import vn.framgia.model.Subject;

@Component
public class ExcelApachePoiHelper {
	File convertMutipartFileToFile(MultipartFile multipartFile) {
		File convFile = new File(multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(convFile);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convFile;
	}

	public List<Question> getListQuestionFromExcelFile(MultipartFile multipartFile, Map<Integer, Subject> mapSubject,
			Map<Integer, Level> mapLevel) {
		List<Question> listQuestion = new ArrayList<Question>();
		try {
			FileInputStream inputStream = new FileInputStream(convertMutipartFileToFile(multipartFile));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet xssfSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = xssfSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				listQuestion.add(mapRowExcelToQuestion(currentRow, mapSubject, mapLevel));
			}
			workbook.close();
			return listQuestion;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Question mapRowExcelToQuestion(Row questionRow, Map<Integer, Subject> mapSubject,
			Map<Integer, Level> mapLevel) {
		Question question = new Question();
		question.setQuestion(questionRow.getCell(0).getStringCellValue());
		question.setTypeQuestion((int) questionRow.getCell(1).getNumericCellValue());
		question.setOption1(questionRow.getCell(2).getStringCellValue());
		question.setOption2(questionRow.getCell(3).getStringCellValue());
		question.setOption3(questionRow.getCell(4).getStringCellValue());
		question.setOption4(questionRow.getCell(5).getStringCellValue());
		question.setCorrectAnswer((int) questionRow.getCell(6).getNumericCellValue());
		question.setSubject(
				mapSubject == null ? null : mapSubject.get((int) questionRow.getCell(7).getNumericCellValue()));
		question.setLevel(mapLevel == null ? null : mapLevel.get((int) questionRow.getCell(8).getNumericCellValue()));
		question.setDeleted(false);
		return question;
	}

	public Map<Integer, String> checkExcelFileImport(MultipartFile multipartFile, Map<Integer, Subject> mapSubject,
			Map<Integer, Level> mapLevel) {
		Map<Integer, String> mapResult = new HashMap<>();
		try {
			FileInputStream inputStream = new FileInputStream(convertMutipartFileToFile(multipartFile));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet xssfSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = xssfSheet.iterator();
			iterator.next();
			int rowNum = 0;
			while (iterator.hasNext()) {
				rowNum++;
				Row currentRow = iterator.next();
				String rowError = checkRowExcel(currentRow, mapSubject, mapLevel);
				if (rowError != null) {
					mapResult.put(rowNum, rowError);
				}
			}
			workbook.close();
			return mapResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mapResult.put(0, "Loi file");
			return mapResult;
		}
	}

	private String checkRowExcel(Row questionRow, Map<Integer, Subject> mapSubject, Map<Integer, Level> mapLevel) {
		try {
			if (questionRow.getCell(0).getStringCellValue() == null)
				return "Question colum error";
			if (!mapSubject.containsKey((int) questionRow.getCell(7).getNumericCellValue()))
				return "Not exist subject";
			if (!mapLevel.containsKey((int) questionRow.getCell(8).getNumericCellValue()))
				return "Not exits level";
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			return "Exception";
		}
	}
}
