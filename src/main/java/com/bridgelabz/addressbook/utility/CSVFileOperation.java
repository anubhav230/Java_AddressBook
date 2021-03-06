package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.models.Person;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVFileOperation {

    public List<Person> loadDataFromFile(String CSV_FILE_PATH) {
        List<Person> book;
        try(Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader).withType(Person.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            book = csvToBean.parse();
            return book;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeFile(List<Person> book, String CSV_FILE_PATH) throws AddressBookException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH))) {
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(book);
        } catch (Exception e) {
            throw new AddressBookException(AddressBookException.ExceptionType.FILE_PROBLEM, "Invalid File");
        }
    }
}
