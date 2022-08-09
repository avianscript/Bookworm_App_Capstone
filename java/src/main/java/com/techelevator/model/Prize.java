package com.techelevator.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prize {
    private int prizeValue;
    private String name;
    private String description;
    private int mileStone;
    private ArrayList userGroup = new ArrayList<>();
    private int maxPrizes;
    private LocalDate startDate;
    private LocalDate endDate;

    public Prize(int prizeValue, String name, String description, int mileStone, ArrayList userGroup, int maxPrizes, LocalDate startDate, LocalDate endDate) {
        this.prizeValue = prizeValue;
        this.
    }
}
