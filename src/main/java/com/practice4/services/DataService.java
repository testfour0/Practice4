package com.practice4.services;

import com.practice4.models.DataItem;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {
    private List<DataItem> dataItems = new ArrayList<>();

    /*@PostConstruct
    public void loadDataFromCsv(){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data/data.csv")))){
            dataItems = br.lines().skip(1)
                .map(line -> {
                    String[] fields = line.split(",");
                    return new DataItem(fields[0], fields[1], fields[2]);
            })
                    .collect(Collectors.toList());
            } catch (Exception e){
                e.printStackTrace();
        }
    }*/

    public void uploadData(MultipartFile file){
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            dataItems = br.lines().skip(1)
                    .map(line -> {
                        String[] fields = line.split(",");
                        return new DataItem(fields[0], fields[1], fields[2], fields[3], fields[4]);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<DataItem> getData(){
        return dataItems;
    }

    public List<DataItem> filterData(String column1Filter, String column2Filter, String column3Filter, String column4Filter, String column5Filter) {
        return dataItems.stream()
                .filter(item -> item.getColumn1().contains(column1Filter))
                .filter(item -> item.getColumn2().contains(column2Filter))
                .filter(item -> item.getColumn3().contains(column3Filter))
                .filter(item -> item.getColumn4().contains(column4Filter))
                .filter(item -> item.getColumn5().contains(column5Filter))
                .collect(Collectors.toList());
    }

    public void clearData(){
        dataItems.clear();
    }
}
