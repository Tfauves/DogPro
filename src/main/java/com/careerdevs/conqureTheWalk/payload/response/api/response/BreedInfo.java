package com.careerdevs.conqureTheWalk.payload.response.api.response;


public class BreedInfo {
    private class Weight {
        private String imperial;
        private String metric;

        public Weight(){}

        public Weight(String imperial, String metric) {
            this.imperial = imperial;
            this.metric = metric;
        }

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }

    private class Height {
        private String imperial;
        private String metric;

        public Height() {}

        public Height(String imperial, String metric) {
            this.imperial = imperial;
            this.metric = metric;
        }

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }

    private Weight weight;
    private Height height;
    private int id;
    private String name;
    private String bred_for;
    private String breed_group;
    private String life_span;
    private String temperament;
    private String reference_image_id;

    public BreedInfo() {}

    public BreedInfo( Height height, int id, String name, String bred_for, String breed_group, String life_span, String temperament, String reference_image_id) {
        this.weight = weight;
        this.height = height;
        this.id = id;
        this.name = name;
        this.bred_for = bred_for;
        this.breed_group = breed_group;
        this.life_span = life_span;
        this.temperament = temperament;
        this.reference_image_id = reference_image_id;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public void setBreed_group(String breed_group) {
        this.breed_group = breed_group;
    }

    public String getLife_span() {
        return life_span;
    }

    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getReference_image_id() {
        return reference_image_id;
    }

    public void setReference_image_id(String reference_image_id) {
        this.reference_image_id = reference_image_id;
    }
}
