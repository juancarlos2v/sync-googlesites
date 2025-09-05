package com.tecba.adsitios.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Locations {

    @JsonProperty("locations")
    public List<Location> locations;
    @JsonProperty("nextPageToken")
    private String nextPageToken;
    private Map<String, Object> rawData; // para guardar el JSON completo si querés

    @Data
    public static class Location{
        @JsonProperty("name")
        private String name; // "locations/4277855925007491046"
        @JsonProperty("title")
        private String title; // "Capilla Cementerio de Flores"
        @JsonProperty("categories")
        private Categories categories; // para obtener category.displayName
        @JsonProperty("storefrontAddress")
        private StorefrontAddress storefrontAddress; // para dirección, postalCode, province, country
    }

    @Data
    public static class Categories {
        @JsonProperty("primaryCategory")
        private PrimaryCategory primaryCategory;
    }

    @Data
    public static class PrimaryCategory {
        private String name; // "categories/gcid:place_of_worship"
        private String displayName; // "Lugar de culto"
    }

    @Data
    public static class StorefrontAddress {
        private String regionCode; // "AR"
        private String languageCode; // "es"
        private String postalCode; // "C1406"
        private String administrativeArea; // "Ciudad Autónoma de Buenos Aires"
        private String locality; // "Flores"
        private ArrayList<String> addressLines; // ["Avenida Varela 1700"]

    }

}
