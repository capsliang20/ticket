package com.line.ticket.log.hadoop.record;

import org.apache.hadoop.io.Text;

import java.util.Random;

public class RecordParser {
    private static final int MISSING_TEMPERATURE = 9999;

    private String year;

    private int airTemperature;

    private String quality;

    public void parse(String record) {
        year = record.substring(0, 4);
        String airTemperatureStr;
        if (record.charAt(4) == '+') {
            airTemperatureStr = record.substring(5, 8);
        } else {
            airTemperatureStr = record.substring(4, 8);
        }
        airTemperature = Integer.parseInt(airTemperatureStr);
        quality = record.substring(8, 9);
    }

    public void parse(Text record) {
        parse(record.toString());
    }

    public boolean isValidTemperature() {
        return airTemperature != MISSING_TEMPERATURE && quality.matches("[01459]");
    }

    public String getYear() {
        return year;
    }

    public int getAirTemperature() {
        return airTemperature;
    }

    public String getStationId() {
        return String.valueOf(new Random().nextInt(100));
    }

    public boolean isMalformedTemperature() {
        return airTemperature > 100;
    }

    public boolean isMissingTemperature() {
        return airTemperature == MISSING_TEMPERATURE;
    }

    public String getQuality(){
        return quality;
    }
}
