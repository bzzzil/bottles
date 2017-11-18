package io.bzzzil.bottles.database;

import java.io.Serializable;

public class Bottle implements Serializable {
    /**
     * Bottles: column "type"
     */
    public static final String COLUMN_TYPE = "type";

    /**
     * Bottles: column "country"
     */
    public static final String COLUMN_COUNTRY = "country";

    /**
     * Bottles: column "manufacturer"
     */
    public static final String COLUMN_MANUFACTURER = "manufacturer";

    /**
     * Bottles: column "title"
     */
    public static final String COLUMN_TITLE = "title";

    /**
     * Bottles: column "volume"
     */
    public static final String COLUMN_VOLUME = "volume";

    /**
     * Bottles: column "degree"
     */
    public static final String COLUMN_DEGREE = "degree";

    /**
     * Bottles: column "package"
     */
    public static final String COLUMN_PACKAGING = "packaging";

    /**
     * Bottles: column "incomeDate"
     */
    public static final String COLUMN_INCOME_DATE = "incomeDate";

    /**
     * Bottles: column "incomeSource"
     */
    public static final String COLUMN_INCOME_SOURCE = "incomeSource";

    /**
     * Bottles: column "price"
     */
    public static final String COLUMN_PRICE = "price";

    /**
     * Bottles: column "price_currency"
     */
    public static final String COLUMN_PRICE_CURRENCY = "price_currency";

    /**
     * Bottles: column "comments"
     */
    public static final String COLUMN_COMMENTS = "comments";

    private String type;
    private String country;
    private String manufacturer;
    private String title;
    private int volume;
    private float degree;
    private String packaging;
    private int incomeDate;
    private String incomeSource;
    private float price;
    private String priceCurrency;
    private String comments;

    public Bottle() {

    }

    public Bottle(String type, String country, String manufacturer, String title, int volume, float degree, String packaging, int income_date, String income_source, float price, String priceCurrency, String comments) {
        this.type = type;
        this.country = country;
        this.manufacturer = manufacturer;
        this.title = title;
        this.volume = volume;
        this.degree = degree;
        this.packaging = packaging;
        this.incomeDate = income_date;
        this.incomeSource = income_source;
        this.price = price;
        this.priceCurrency = priceCurrency;
        this.comments = comments;
    }

    public String getType() {
        return type;
    }

    public String getCountry() {
        return country;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getTitle() {
        return title;
    }

    public int getVolume() {
        return volume;
    }

    public String getVolumeAsString() {
        return String.valueOf(volume);
    }

    public float getDegree() {
        return degree;
    }

    public String getDegreeAsString() {
        return String.valueOf(degree);
    }

    public String getPackaging() {
        return packaging;
    }

    public int getIncomeDate() {
        return incomeDate;
    }

    public String getIncomeDateAsString() {
        return String.valueOf(incomeDate);
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public float getPrice() {
        return price;
    }

    public String getPriceAsString() {
        return String.valueOf(price);
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public String getComments() {
        return comments;
    }
}
