package fr.bazin.find_stations.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {

    @JsonProperty("number")
    private int number;

    @JsonProperty("contractName")
    private String contractName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String adresse;

    @JsonProperty("banking")
    private boolean banking;

    @JsonProperty("status")
    private String status;

    @JsonProperty("mainStands")
    private MainStands mainStands;



    @Getter
    @Setter
    public static class MainStands {


        @JsonProperty("capacity")
        private int capacite;

        @JsonProperty("availabilities")
        private Availabilities availabilities;


        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Availabilities {

            @JsonProperty("bikes")
            private int bikes;

            @JsonProperty("stands")
            private int stands;

        }
    }



}
