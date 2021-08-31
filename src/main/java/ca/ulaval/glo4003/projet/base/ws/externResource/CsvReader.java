package ca.ulaval.glo4003.projet.base.ws.externResource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CsvReader {
  private HashMap<String, Integer> linesHeaders = new HashMap<>();
  private HashMap<String, Integer> columnsHeaders = new HashMap<>();
  private String[][] contents;

  public CsvReader(String path) {
    readCsvFile(path);
  }

  public HashMap<String, Integer> getLinesHeaders() {
    return linesHeaders;
  }

  public HashMap<String, Integer> getColumnsHeaders() {
    return columnsHeaders;
  }

  public String[][] getContents() {
    return contents;
  }

  private void readCsvFile(String path) {
    BufferedReader csvReader = null;
    try {
      csvReader = new BufferedReader(new FileReader(path));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    List<String[]> fileLines = new ArrayList<>();
    while (true) {
      try {
        String bufferRow = csvReader.readLine();
        fileLines.add(bufferRow.split(","));
      } catch (NullPointerException | IOException e) {
        break;
      }
    }
    contents = new String[fileLines.size()-1][fileLines.get(0).length-1];
    for (int i = 1; i<fileLines.get(0).length;i++) {
      columnsHeaders.put(fileLines.get(0)[i].toUpperCase(), i-1);
    }
    for (int i = 1; i<fileLines.size();i++) {
      linesHeaders.put(fileLines.get(i)[0].toUpperCase(),i-1);
    }
    for (int i = 1; i<fileLines.size();i++){
      for (int j = 1; j<fileLines.get(i).length;j++) {
        contents[i-1][j-1] = fileLines.get(i)[j];
      }
    }
  }
}
