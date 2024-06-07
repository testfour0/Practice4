package com.practice4.services;

import com.practice4.models.DataItem;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataService {
    public List<DataItem> getData(){
        return Arrays.asList(
                new DataItem("Data 1", "Data 2", "Data 3"),
                new DataItem("Data 4", "Data 5", "Data 6"),
                new DataItem("Data 7", "Data 8", "Data 9")
        );
    }
}
