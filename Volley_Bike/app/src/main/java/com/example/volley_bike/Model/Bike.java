package com.example.volley_bike.Model;

import java.io.Serializable;

public class Bike implements Serializable {

        private long bikeModelID;
        private long brandID;
        private String modelName;
        private String createdAt;
        private String updatedAt;

        public Bike()
        {

        }

    public Bike(long bikemodel, long brandid, String modelname)
    {
       this.bikeModelID = bikemodel;
       this.brandID = brandid;
       this.modelName = modelname;

    }

    public long getBikeModelID() {
            return bikeModelID;
        }

        public void setBikeModelID(long bikeModelID) {
            this.bikeModelID = bikeModelID;
        }

        public long getBrandID() {
            return brandID;
        }

        public void setBrandID(long brandID) {
            this.brandID = brandID;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
